package com.neosofttech.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neosofttech.model.LoginForm;
import com.neosofttech.model.Order;
import com.neosofttech.model.Payment;
import com.neosofttech.model.Products;
import com.neosofttech.model.Address;
import com.neosofttech.model.UserMaster;
import com.neosofttech.service.ProductsService;
import com.neosofttech.service.UserMasterService;
import com.neosofttech.utils.InvalidUserException;

@Controller
public class ViewController extends DefaultController 
{
	
	@Autowired
	ProductsService productsService;
	@Autowired
	UserMasterService userMasterService;
	
	/*Method To display Home Page.*/
	@RequestMapping(value={"/","home"},method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView openHome(@RequestParam(required=false) String message,ModelMap model)
	{
		List<Products> latestProducts = productsService.getLatestProduct();
		model.addAttribute("latestProducts", latestProducts);
		model.addAttribute("message", message);
		return new ModelAndView("home");
	}

	/*Method to display Login page.*/
	@SuppressWarnings("unused")
	@RequestMapping(value="login",method=RequestMethod.GET)
	public ModelAndView openLogin(ModelMap model,HttpSession session)
	{
		try {
		int userId = (Integer) session.getAttribute("userID");
		return new ModelAndView("redirect:home","message","logged in hai toh wapas q");
		}catch(NullPointerException e) {
			model.addAttribute("loginForm", new LoginForm());
			return new ModelAndView("login");	
		}
			
	}
	

	
	@RequestMapping(value={"register"},method=RequestMethod.GET)
	public ModelAndView openRegister(Map<String, Object> model)
	{
		model.put("userMaster", new UserMaster());
		String viewName="register";
		return new ModelAndView(viewName);
	}
	

	@RequestMapping(value="Order",method=RequestMethod.POST)
	public ModelAndView addToOrderinSession(
			@RequestParam("id[]") int[] id,
			@RequestParam("quan[]") int[] quan,
			@RequestParam("total") int total,
			@RequestParam("delivery[]") String[] delivery,
			ModelMap model,HttpSession session)
	{
		String username = (String) session.getAttribute("username");
		 if(username == null || username =="")
		 {
			 model.addAttribute("message", "Tu login karega ki nahi");
		 }else{
				session.setAttribute("id", id);
				session.setAttribute("quan", quan);
				session.setAttribute("total", total);	
				session.setAttribute("delivery", delivery);
		 }
		return null;
	}
	
	@RequestMapping(value="order",method=RequestMethod.GET)
	public ModelAndView openOrder(HttpSession session,ModelMap model)throws NullPointerException
	{
		try {
		int[] id=(int[]) session.getAttribute("id");
		int[] quan = (int[]) session.getAttribute("quan");
		String[] delivery = (String[]) session.getAttribute("delivery");
		int total = (Integer) session.getAttribute("total");
		ArrayList<Products> a =new ArrayList<Products>();
		for (int i = 0; i < id.length; i++) {
			int j = id[i];
			Products p =productsService.getProductsById(j);
			a.add(p);
		}
		session.setAttribute("InvoiceProducts", a);
		Object u = session.getAttribute("userID");
		 String userID = String.valueOf(u);
		 Integer userid = Integer.valueOf(userID);
		List<Address> add= userMasterService.getAddress(userid);
		List<Payment> payment = productsService.getPaymentList();
		model.addAttribute("payment", payment);
		model.addAttribute("Address", add);
		model.addAttribute("total", total);
		model.addAttribute("productList", a);
		model.addAttribute("quan", quan);
		model.addAttribute("delivery", delivery);
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}
		return new ModelAndView("order");
	}
	
	@RequestMapping(value="checkout",method=RequestMethod.POST)
	public @ResponseBody int checkout(@RequestParam("addressId") int addressId,
			@RequestParam("payment") String payment,HttpSession session)
	{
		//Order yaha save karte hai
		int[] productId=(int[]) session.getAttribute("id");
		int[] quan = (int[]) session.getAttribute("quan");
		int total = (Integer) session.getAttribute("total");
		int userId = (Integer) session.getAttribute("userID");
		int i=productsService.placeOrder(userId,addressId, productId, quan, total, payment);
		return i;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="user/Order/{orderId}")
	public ModelAndView Invoice(@PathVariable int orderId,
			ModelMap model,
			HttpSession session)throws NullPointerException,InvalidUserException
	{
	try {
		int id=(Integer) session.getAttribute("userID");
		Order o = userMasterService.getOrderById(orderId);
		if(o==null) throw new NullPointerException("Order hai hi nai aisa kuch");
		if(id !=o.getUser().getId()) throw new InvalidUserException("Dusre ka order hai");
		List p = productsService.getDistinctProducts(orderId);
		List<Products> pro= new ArrayList<Products>();
		List<Long> count =new ArrayList<Long>();
		for (Object object : p) {
			Products pr = productsService.getProductsById((Integer)object);
			List o2 = productsService.getCountOfProducts(orderId,(Integer)object );
			long s=  (Long) o2.get(0);
			count.add(s);
			pro.add(pr);
		}
		model.addAttribute("orderId", orderId);
		model.addAttribute("PaymentType", o.getPayment().getPaymentType());
		model.addAttribute("Address", o.getAddress());
		model.addAttribute("Quan", count);
		model.addAttribute("productList", pro);
		model.addAttribute("total", o.getTotalPrice());
		model.addAttribute("USERNAME", o.getUser().getName());
		model.addAttribute("MobileNo", o.getUser().getMobileno());
		model.addAttribute("Date", o.getCreationDate());
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}
		return new ModelAndView("invoice");
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="Order/{orderId}/downloadPDF",method=RequestMethod.GET)
	public ModelAndView pdfView(@PathVariable int orderId,
			ModelMap model,HttpSession session)throws NullPointerException,InvalidUserException
	{
	try {
		int id=(Integer) session.getAttribute("userID");
		
		Order o = userMasterService.getOrderById(orderId);
		if(o==null) throw new NullPointerException("Order hai hi nai aisa kuch");
		if(id !=o.getUser().getId()) throw new InvalidUserException("Dusre ka order hai");
		
		List p = productsService.getDistinctProducts(orderId);
		List<Products> pro= new ArrayList<Products>();
		List<Long> count =new ArrayList<Long>();
		for (Object object : p) {
			Products pr = productsService.getProductsById((Integer)object);
			List o2 = productsService.getCountOfProducts(orderId,(Integer)object );
			long s=  (Long) o2.get(0);
			count.add(s);
			pro.add(pr);
		}
		Map m = new HashMap();
		m.put("orderId", String.valueOf(orderId));
		m.put("PaymentType", o.getPayment().getPaymentType());
		m.put("Address", o.getAddress());
		m.put("Quan", count);
		m.put("pro", pro);
		m.put("total", o.getTotalPrice());
		m.put("USERNAME", o.getUser().getName());
		m.put("MobileNo", o.getUser().getMobileno());
		m.put("Date", o.getCreationDate());
		session.setAttribute("INVOICE", m);
	}catch(NullPointerException e) {
		throw new NullPointerException();
	}
		return new ModelAndView("pdfView");
	}			
	@RequestMapping(value="Order/{orderId}/Cancel")
	public ModelAndView cancelOrder(@PathVariable int orderId,
			ModelMap model,HttpSession session)throws NullPointerException,InvalidUserException
	{
		try {
			@SuppressWarnings("unused")
			int id=(Integer) session.getAttribute("userID");
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}
		return new ModelAndView("cancelOrder");
	}
	
}