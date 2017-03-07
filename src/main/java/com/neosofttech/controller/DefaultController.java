package com.neosofttech.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.neosofttech.model.Category;
import com.neosofttech.service.ProductsService;

@Controller
public class DefaultController 
{
	@Autowired
	ProductsService productsService;
	
	@ModelAttribute
	public void displayMenu(ModelMap model,HttpSession session)
	{
		List<Category> productList=productsService.getProductTypeList();
		model.addAttribute("Category", productList);
		String username = (String) session.getAttribute("username");
		if(username == null || username ==""){}
		else{model.addAttribute("username", username);}
		
	}
}
