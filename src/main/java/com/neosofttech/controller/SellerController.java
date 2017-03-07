package com.neosofttech.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neosofttech.model.Seller;
import com.neosofttech.service.SellerService;
import com.neosofttech.utils.SellerValidator;

@Controller
public class SellerController 
{
	@Autowired
	SellerValidator sellerValidator;
	
	@Autowired
	SellerService sellerService;

	@RequestMapping(value="seller",method=RequestMethod.GET)
	public ModelAndView displaySeller(Principal principal,ModelMap model,HttpSession session) 
	{
		model.addAttribute("username", principal.getName());
		session.setAttribute("username", principal.getName());
		return new ModelAndView("seller");
	}
	
	
	
	
	@RequestMapping(value="seller/addSellerProducts",method=RequestMethod.GET)
	public ModelAndView addSellerProducts(ModelMap model,HttpSession session) 
	{
		return new ModelAndView("addSellerProducts");
	}
	
	@RequestMapping(value="seller/Sellerinfo",method=RequestMethod.GET)
	public ModelAndView goSellerInfo(ModelMap model,HttpSession session) 
	{
		return new ModelAndView("sellerInfo");
	}
	
	@RequestMapping(value="seller/products",method=RequestMethod.GET)
	public ModelAndView viewSellerProducts(ModelMap model,HttpSession session) 
	{
		return new ModelAndView("viewSellerProducts");
	}	
	
	@RequestMapping(value="admin/AddSeller",method=RequestMethod.POST)
	public ModelAndView saveSeller(@Valid @ModelAttribute("seller") Seller seller,
			BindingResult result,ModelMap model) 
	{
		sellerValidator.validate(seller,result);
		if (result.hasErrors()) {
			return new ModelAndView("addSeller");
		}
		
		String id =sellerService.saveSeller(seller);
		if (id.equalsIgnoreCase("username exits") || id.equalsIgnoreCase("email exits")) {
			model.addAttribute("error", id);
			return new ModelAndView("addSeller");
		} else {

			return new ModelAndView("redirect:/admin","message","Seller Saved Successfully");
		}
	}
	
}
