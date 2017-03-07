package com.neosofttech.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neosofttech.model.Cart;
import com.neosofttech.model.Products;
import com.neosofttech.model.UserMaster;
import com.neosofttech.service.ProductsService;
import com.neosofttech.service.UserMasterService;

@Controller
public class CartController extends DefaultController
{
	@Autowired
	ProductsService productsService;
	@Autowired
	UserMasterService userMasterService;
	
	@RequestMapping(value="MyCart")
	public ModelAndView openMyCart(HttpSession session,ModelMap model)
	{
		String username = (String) session.getAttribute("username");
		 if(username == null || username =="")
		 {
			 //bina login kia cart display karne wala code dalna hai yaha
			 return new ModelAndView("cart");
		 }
		Object u = session.getAttribute("userID");
		String userID = String.valueOf(u);
		Integer userid = Integer.valueOf(userID);
		List<Products> cart = productsService.getProductsfromCart(userid);
		if(cart!=null){
			model.addAttribute("Cart", cart);
		}else{
			model.addAttribute("message","No items to display");
		}
		return new ModelAndView("cart");	
	}
	
	@RequestMapping(value="Cart",method=RequestMethod.POST)
	public @ResponseBody String AddtoCart(@RequestParam("id")int productid,HttpSession session,ModelMap model)
	{
		String username = (String) session.getAttribute("username");
		 if(username == null || username =="")
		 {
			 //login nahi kia hai isliye cart table mai save nai hoga
			 //bina login kia cart mai dalne ka code yaha
			 return "Login kar pehle";
		 }else{
				Object u = session.getAttribute("userID");
				String userID = String.valueOf(u);
				Integer userid = Integer.valueOf(userID);
				List<Products> pr = productsService.getProductsfromCart(userid);
				if(pr!=null){
					for (Products products : pr) {
						if(productid==products.getId()){
							return "Already added to cart";
						}
					}
				}
				Products p = productsService.getProductsById(productid);
				UserMaster user = userMasterService.getUser(userid);
				Cart cart = new Cart();
				cart.setProduct(p);
				cart.setUser(user);
				productsService.addToCart(cart);
				pr = productsService.getProductsfromCart(userid);
				model.addAttribute("Cart",pr);
				return "added to cart";
			
		 }

		
	}
	
	@RequestMapping(value="RemoveFromCart",method=RequestMethod.POST)
	public @ResponseBody String RemoveFromCart(@RequestParam("id")int productid,HttpSession session,ModelMap model)
	{
		List<Products> cart = new ArrayList<Products>();
		String username = (String) session.getAttribute("username");
			 if(username == null || username =="")
			 {
				 model.addAttribute("message", "Tu login karega ki nahi");
			 }else{
				 Object u = session.getAttribute("userID");
				 String userID = String.valueOf(u);
				 Integer userid = Integer.valueOf(userID);
				 productsService.removeFromCart(userid,productid);
				 cart = productsService.getProductsfromCart(userid);
				 if(cart!=null){
						model.addAttribute("Cart", cart);
					}else{
						model.addAttribute("message","No items to display");
					}
			}
		return "Removed from cart";
	}
}
