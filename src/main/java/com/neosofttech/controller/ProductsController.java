package com.neosofttech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neosofttech.model.Products;
import com.neosofttech.service.ProductsService;

@Controller
public class ProductsController extends DefaultController
{
	@Autowired
	ProductsService productsService;
	
	@RequestMapping(value="Products")
	public ModelAndView viewByProductType(
			@RequestParam(value="Type") String productType,
			@RequestParam(value="Name",required=false) String productName,
			@RequestParam(value="company",required=false) String productCompany,
			ModelMap model)
	{	
		List<Products> products=productsService.getProductbyType(productType);
		
		List<String> p = productsService.getProductkaCompanyName(productType);
		if(productCompany!=null && productCompany!="") {
		products = productsService.getProductCompanyName(productType, productCompany);
		}else if(productName!=null) {
			products = productsService.getProductbyName(productName);
		}
		model.addAttribute("productList", products);
		model.addAttribute("productCompanyList", p);
		model.addAttribute("Type", productType);
		return new ModelAndView("products");
	}
	
	@RequestMapping(value="product/{productId}")
	public ModelAndView viewProduct(@PathVariable int productId,ModelMap model){
		Products p = productsService.getProductsById(productId);
		model.addAttribute("Product",p);
		return new ModelAndView("product");
	}	
}
