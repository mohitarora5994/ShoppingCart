package com.neosofttech.service;

import java.util.List;

import com.neosofttech.model.Products;
import com.neosofttech.model.Cart;
import com.neosofttech.model.Category;
import com.neosofttech.model.Order;
import com.neosofttech.model.OrderStatus;
import com.neosofttech.model.Payment;

public interface ProductsService {
	public List<Category> getProductTypeList();
	public List<Products> getProductList();
	public void addProductsType(Category type);
	public void addProducts(Products products);
	
	public List<Products> getProductbyType(String type);
	public Products getProductsById(int id);
	public void updateProduct(Products product);
	public void removeProduct(int id);
	public List<Products> productsfromwishlist(int userId);
	public String addWishlist(int userId,int productId);
	public void removeFromWishList(int userId,int productId);
	public List<Order> getOrder();
	public List<Order> getOrder(String status);
	public void updateOrder(int orderId,String status);
	public void addToCart(Cart cart);
	public void removeFromCart(int userid,int productid);
	public List<Products> getProductsfromCart(int userId);
	public List<OrderStatus> getOrderStatus();
	
	public List<Payment> getPaymentList();
	public int placeOrder(int userid,int addressId,int[] productId,
			int[] quan,int total,String payment);
	
	@SuppressWarnings("rawtypes")
	public List getDistinctProducts(int orderId);
	@SuppressWarnings("rawtypes")
	public List getCountOfProducts(int orderId,int productId);
	public List<String> getProductkaCompanyName(String productType);
	public List<Products> getProductbyName(String productName);
	public List<Products> getProductCompanyName(String productType,String company);
	
	List<Products> getLatestProduct();
	
	
}