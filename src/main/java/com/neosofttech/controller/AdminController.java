package com.neosofttech.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neosofttech.model.Products;
import com.neosofttech.model.Seller;
import com.neosofttech.model.Category;
import com.neosofttech.model.Order;
import com.neosofttech.model.OrderStatus;
import com.neosofttech.model.UserMaster;
import com.neosofttech.service.ProductsService;
import com.neosofttech.service.UserMasterService;

@Controller
public class AdminController {
	
	@Autowired
	ProductsService productsService;
	
	@Autowired
	UserMasterService userMasterService;
	
	final static Logger logger=Logger.getLogger(AdminController.class);
	
	@RequestMapping(value="loginAdmin",method=RequestMethod.GET)
	public ModelAndView adminLogin() {
		String viewName="loginAdmin";
		return new ModelAndView(viewName);
	}
	
	@RequestMapping(value="admin/addSeller",method=RequestMethod.GET)
	public ModelAndView addSeller(Map<String, Object> model) 
	{
		String viewName="addSeller";
		model.put("seller", new Seller());
		return new ModelAndView(viewName);
	}
	
	/*Method to display admin page.*/
	@RequestMapping(value={"admin","admin/home"},method=RequestMethod.GET)
	public ModelAndView openAdmin(@RequestParam(required=false) String message,
			Principal principal,ModelMap model,HttpSession session)
	{
		UserMaster u =userMasterService.findByUsername(principal.getName());
		model.addAttribute("username", principal.getName());
		model.addAttribute("userID",u.getId());
		session.setAttribute("username", principal.getName());
		session.setAttribute("userID",u.getId());
		String viewName="admin";
		model.addAttribute("message", message);
		return new ModelAndView(viewName);
	}
	
	/*Method to display add-Products form.*/
	@RequestMapping(value="admin/AddProducts",method=RequestMethod.GET)
	public ModelAndView viewAddProducts(ModelMap model)
	{
		List<Products> products=productsService.getProductList();
		model.addAttribute("productList",products);
		model.addAttribute("product",new Products());
		String viewName="addProducts";
		return new ModelAndView(viewName);
	}
	
	/*Method to add Product in database*/
	@RequestMapping(value="admin/AddProduct",method=RequestMethod.POST)
	public ModelAndView addProducts(ModelMap model,
			@Valid @ModelAttribute("product") Products product,
			BindingResult result)
	{
		
		if(result.hasErrors())
		{
			return new ModelAndView("addProducts");
		}
		productsService.addProducts(product);
		List<Products> products=productsService.getProductList();
		model.addAttribute("productList",products);
		model.addAttribute("product",new Products());
		return new ModelAndView("redirect:AddProducts");
	}
	
	/*Method to Add Category in database.*/
	@RequestMapping(value="admin/AddProductType",method=RequestMethod.POST)
	public ModelAndView addCategory(ModelMap model,HttpServletRequest req)
	{
		String viewName="redirect:AddProductType";
		String type=req.getParameter("type");
		Category pt=new Category();
		pt.setCategoryName(type);
		productsService.addProductsType(pt);
		logger.info("New Category Saved with name="+type);
		List<Category> productList=productsService.getProductTypeList();
		model.addAttribute("Category", productList);
		return new ModelAndView(viewName);
		
	}
	
	/*Method to display Add-Category form.*/
	@RequestMapping(value="admin/AddProductType",method=RequestMethod.GET)
	public ModelAndView viewAddProductsType()
	{
		String viewName="addProductType";
		return new ModelAndView(viewName);
	}

	/*Method to view Users*/
	@RequestMapping(value="admin/Users",method=RequestMethod.GET)
	public ModelAndView viewUsers(ModelMap model)
	{
		List<UserMaster> userMaster= userMasterService.getUserList();
		model.addAttribute("userList", userMaster);
		return new ModelAndView("Users");
	}
	
	@RequestMapping(value="admin/updateProduct/{id}", method=RequestMethod.GET)
	public ModelAndView updateProduct(@PathVariable int id,ModelMap model)
	{
		Products p= productsService.getProductsById(id);
		model.addAttribute("product", p);
		List<Category> productList=productsService.getProductTypeList();
		model.addAttribute("Category", productList);
		return new ModelAndView("updateProduct");
	}
	
	@RequestMapping(value="admin/deleteProduct/{id}", method=RequestMethod.GET)
	public ModelAndView removeProduct(@PathVariable int id,ModelMap model)
	{
		
		productsService.removeProduct(id);
		List<Products> products=productsService.getProductList();
		model.addAttribute("productList",products);
		model.addAttribute("product",new Products());
		return new ModelAndView("addProducts");
	}

	@RequestMapping(value="admin/updateProduct",method=RequestMethod.POST)
	public ModelAndView UpdateProduct(ModelMap model,
			@Valid @ModelAttribute("product") Products product,
			BindingResult result)
	{
		if(result.hasErrors()){
		return new ModelAndView("updateProduct");
		}
		productsService.updateProduct(product);
		List<Products> products=productsService.getProductList();
		model.addAttribute("productList",products);
		model.addAttribute("product",new Products());
		return new ModelAndView("redirect:AddProducts");
	}

	@RequestMapping(value="admin/Orders",method=RequestMethod.GET)
	public ModelAndView getOrder(
			@RequestParam(value="status",required=false) String status,
			@RequestParam(value="id",required=false) String id,
			ModelMap model)
	{
		List<Order> o = new ArrayList<Order>();
		if(status!=null) {
			System.out.println("Status is "+status);
			o=productsService.getOrder(status);
		}else if(id!=null) {
			try {
				int i = Integer.valueOf(id);
				Order o1=userMasterService.getOrderById(i);
				o.add(o1);
				List<OrderStatus> st = productsService.getOrderStatus();
				model.addAttribute("allOrder", o);
				model.addAttribute("allStatus", st);
				return new ModelAndView("updateOrder");
			}catch(Exception e) {
				model.addAttribute("message","Invalid Number");
			}

		}else {
			o=productsService.getOrder();
		}
		
		model.addAttribute("allOrder", o);
		return new ModelAndView("Order");
	}
	@RequestMapping(value="admin/UpdateOrder")
	public ModelAndView updateOrder(
			@RequestParam(value="status") String status,
			@RequestParam(value="orderId") int id) 
	{
		productsService.updateOrder(id, status);
		return new ModelAndView("redirect:Orders");
	}
	
}