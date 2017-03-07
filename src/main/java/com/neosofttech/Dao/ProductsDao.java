package com.neosofttech.Dao;

import java.util.List;

import com.neosofttech.model.Products;
import com.neosofttech.model.UserCancelledOrders;
import com.neosofttech.model.Cart;
import com.neosofttech.model.Category;
import com.neosofttech.model.Order;
import com.neosofttech.model.OrderStatus;
import com.neosofttech.model.Payment;
import com.neosofttech.model.WishList;

public interface ProductsDao 
{
	public List<Products> getProductList();
	public List<Products> getProductbyType(String type);
	
	public List<Category> getProductTypeList();
	public void addProductsType(Category type);
	public void addProducts(Products products);
	
	public Products getProductById(int id);
	public void removeProduct(int id);
	public void update(Products product);
	
	public void addWishlist(WishList wish);
	public List<WishList> searchInWishList(int userId);
	public WishList getProductfromWishList(int userId,int productid);
	public void removefromWishList(WishList wish);
	
	public void addToCart(Cart cart);
	public void removeFromCart(Cart cart);
	public List<Cart> getProductsfromCart(int userId);
	
	public int saveOrder(Order order);
	public List<Order> getOrder();
	public List<Order> getOrder(String status);
	public List<OrderStatus> getOrderStatus();
	public void updateOrder(Order order);
	public OrderStatus getOrderStatusByStatus(String status);
	
	public List<Payment> getPaymentList();
	public Payment getPaymentByType(String Payment);
	
	
	@SuppressWarnings("rawtypes")
	public List getDistinctProducts(int orderId); 
	@SuppressWarnings({ "rawtypes" })
	public List getCountOfProducts(int orderId,int productId);
	
	Category getCategorybyType(String productType);
	List<String> getProductkaCompanyName(int categoryId);
	List<Products> getProductsByCompany(int categoryId,String company);
	List<Products> getProductsByName(String name);
	
	List<Products> getLatestProducts();
	
	void saveCancelledOrder(UserCancelledOrders uco);
	
}