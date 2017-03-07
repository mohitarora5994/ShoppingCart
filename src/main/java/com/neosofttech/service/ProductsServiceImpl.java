package com.neosofttech.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosofttech.Dao.ProductsDao;
import com.neosofttech.Dao.UserMasterDao;
import com.neosofttech.model.Products;
import com.neosofttech.model.Address;
import com.neosofttech.model.Cart;
import com.neosofttech.model.Category;
import com.neosofttech.model.Order;
import com.neosofttech.model.OrderStatus;
import com.neosofttech.model.Payment;
import com.neosofttech.model.UserMaster;
import com.neosofttech.model.WishList;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	ProductsDao productsDao;
	@Autowired
	UserMasterDao userMasterDao;
	
	public List<Category> getProductTypeList() {
		return productsDao.getProductTypeList();
	}
	
	public List<Products> getProductList() {
		return productsDao.getProductList();
	}
	
	public void addProductsType(Category type) {
		productsDao.addProductsType(type);
	}
	
	public void addProducts(Products products) {
	
		products.setCreationDate(new Date());
		products.setAvailable(true);
		productsDao.addProducts(products);
		
	}
	public List<Products> getProductbyType(String type) {
		
		return (List<Products>) productsDao.getProductbyType(type);
	}

	public Products getProductsById(int id) {
		Products p=productsDao.getProductById(id);
		return p;
	}

	public void updateProduct(Products product) {
		productsDao.update(product);		
	}

	public void removeProduct(int id) {
		
		Products p = productsDao.getProductById(id);
		p.setAvailable(false);
		productsDao.update(p);		
	}

	public String addWishlist(int userId,int productId) 
	{
		
		WishList wish = new WishList();
		List<WishList> wishList = productsDao.searchInWishList(userId);
		for (WishList userList : wishList) 
		{
			int i = userList.getProduct().getId();
			if(i==productId){
				return "Pehle se hai WishList mai";
			}
		}
		UserMaster u = userMasterDao.findUserById(userId);
		Products p= productsDao.getProductById(productId);
		wish.setProduct(p);
		wish.setUser(u);
		productsDao.addWishlist(wish);	
		return "DAla abhi wishlist mai";
	}

	public List<Products> productsfromwishlist(int userId) {
		List<WishList> wish= productsDao.searchInWishList(userId);
		List<Products> pro=new ArrayList<Products>();		
		for (WishList wishList: wish) 
		{
			Products p = wishList.getProduct();
			pro.add(p);	
			
		}
		return pro;
	}

	public void removeFromWishList(int userId, int productId) 
	{		
		WishList wish = new WishList();
		wish = productsDao.getProductfromWishList(userId, productId);
		productsDao.removefromWishList(wish);
		
	}

	public void addToCart(Cart cart) 
	{
		productsDao.addToCart(cart);
		
	}

	public void removeFromCart(int userid,int productid) 
	{
		List<Cart> carts=productsDao.getProductsfromCart(userid);
		for (Cart cart : carts) {
			if(productid==cart.getProduct().getId()){
				System.out.println(cart.getProduct().getId());
				productsDao.removeFromCart(cart);
				}
		}		
	}

	public List<Products> getProductsfromCart(int userId) {
		List<Cart> cart = productsDao.getProductsfromCart(userId);
		List<Products> p = new ArrayList<Products>();
		if(cart!=null){
			for (Cart cart2 : cart) {
				p.add(cart2.getProduct());					
			}
		 return p;
	}
		return null;

}

	public List<Payment> getPaymentList() {
		
		return productsDao.getPaymentList();
	}

	public int placeOrder(int userid,int addressId,int[] productId, int[] quan,int total,
			String payment) {
		Order o =new Order();
		UserMaster user = userMasterDao.findUserById(userid);
		Address address = userMasterDao.getAddressById(addressId);
		Payment pay =productsDao.getPaymentByType(payment);
		ArrayList<Products> a =new ArrayList<Products>();
		for (int i = 0; i < productId.length; i++) {
			Products p =productsDao.getProductById(productId[i]);
			for(int k=quan[i];k>0;k--){
			a.add(p);}
		}
		OrderStatus s = new OrderStatus();
		s.setId(2);
		s.setStatus("Pending");
		o.setStatus(s);
		o.setPayment(pay);
		o.setCreationDate(new Date());
		o.setAddress(address.getAddress());
		o.setUser(user);
		o.setProduct(a);
		o.setTotalPrice(total);
		return productsDao.saveOrder(o);
	}

	public List<?> getDistinctProducts(int orderId) {
		return productsDao.getDistinctProducts(orderId);
		
	}

	public List<?> getCountOfProducts(int orderId, int productId) {
		return productsDao.getCountOfProducts(orderId, productId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getProductkaCompanyName(String productType) {
		Category cat = productsDao.getCategorybyType(productType);
		return productsDao.getProductkaCompanyName(cat.getCategory_id());
	}

	public List<Products> getProductCompanyName(String productType, String company) {
		Category cat = productsDao.getCategorybyType(productType);
		return productsDao.getProductsByCompany(cat.getCategory_id(), company);
	}

	public List<Products> getProductbyName(String productName) {
	return	productsDao.getProductsByName(productName);
	}

	public List<Order> getOrder() {
		return productsDao.getOrder();
	}
	public List<Order> getOrder(String status) {
		return productsDao.getOrder(status);
	}

	public List<OrderStatus> getOrderStatus() {
		
		return productsDao.getOrderStatus();
	}

	public void updateOrder(int orderId, String status) {
		Order o = userMasterDao.getOrderById(orderId);
		OrderStatus st = productsDao.getOrderStatusByStatus(status);
		o.setStatus(st);
		productsDao.updateOrder(o);		
	}

	public List<Products> getLatestProduct() {
		List<Products> p = productsDao.getLatestProducts();

		return p;
	}
}