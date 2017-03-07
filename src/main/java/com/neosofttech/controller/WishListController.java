package com.neosofttech.controller;

import org.springframework.stereotype.Controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neosofttech.model.Products;

@Controller
public class WishListController extends DefaultController
{
	
	@RequestMapping(value="removeFromWishList",method=RequestMethod.POST)
	public ModelAndView removeFromWishList(ModelMap model,HttpSession session,
			HttpServletRequest req)
	{
		Object u = session.getAttribute("userID");		
		String userID = String.valueOf(u);	
		Integer userid = Integer.valueOf(userID);		
		String product = req.getParameter("id");
		Integer productid = Integer.valueOf(product);
		productsService.removeFromWishList(userid, productid);
		List<Products> p =productsService.productsfromwishlist(userid);
		 model.addAttribute("WishList", p);
		return new ModelAndView("redirect:wishlist");
	}

	@RequestMapping(value="wishlist",method=RequestMethod.POST)
	public @ResponseBody String addProductInWishList
		(@RequestParam("id")int productid,HttpSession session)
	{
		 String username = (String) session.getAttribute("username");
		 Object u = session.getAttribute("userID");
		 String userID = String.valueOf(u);
		 if(username == null || username =="")
		{
			return "Login kar le bhai pehle";
		}		 
		Integer userid = Integer.valueOf(userID);
		System.out.println("Addwishlist Function Called");
		String str = productsService.addWishlist(userid, productid);
		System.out.println("Addwishlist Function Called off"+str);
		return str;
	}
	
	@RequestMapping(value="wishlist",method=RequestMethod.GET)
	public ModelAndView openWishList(ModelMap model,HttpSession session)
	{
		 String username = (String) session.getAttribute("username");
		 if(username == null || username =="")
		 {
			model.addAttribute("message", "Login kar Bhai pehle");
			return new ModelAndView("wishlist");
		 }
		 Object u = session.getAttribute("userID");
		 String userID = String.valueOf(u);
		 Integer userid = Integer.valueOf(userID);
		 List<Products> p =productsService.productsfromwishlist(userid);
		 model.addAttribute("WishList", p);
		 return new ModelAndView("wishlist");
	}

}
