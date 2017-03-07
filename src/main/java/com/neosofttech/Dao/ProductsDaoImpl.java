package com.neosofttech.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.neosofttech.model.Products;
import com.neosofttech.model.UserCancelledOrders;
import com.neosofttech.model.Cart;
import com.neosofttech.model.Category;
import com.neosofttech.model.Order;
import com.neosofttech.model.OrderStatus;
import com.neosofttech.model.Payment;
import com.neosofttech.model.WishList;

@Repository
public class ProductsDaoImpl implements ProductsDao {

	@Autowired
	SessionFactory sessionFactory;
	
	/*Method to get Category from database.*/
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Category> getProductTypeList() 
	{
		return (List<Category>) sessionFactory
				.getCurrentSession()
				.createCriteria(Category.class)
				.list();
	}

	/*Method to get List of products from database.*/
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Products> getProductList() 
	{
		List<Products> products =  sessionFactory
				.getCurrentSession()
				.createCriteria(Products.class)
				.list();
		return products;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Products> getProductbyType(String type) {
		List<Products> p =null;
		try{
		p=	sessionFactory
			.getCurrentSession()
			.createCriteria(Products.class)
			.createAlias("category", "category")
			.add(Restrictions.eqOrIsNull("category.categoryName", type))
			.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}
	
	
	public void addProductsType(Category type) {
		
		sessionFactory.getCurrentSession().persist(type);
	}

	
	public void addProducts(Products products) {
		
		sessionFactory.getCurrentSession().save(products);
	}

	
	public Products getProductById(int id) {
		Products p =null;
		try{
		 p= sessionFactory
		.getCurrentSession()
		.get(Products.class,id);
		}catch(Exception e){
			e.printStackTrace();
		}
		 return p;
	}

	
	public void update(Products product) {
		sessionFactory.getCurrentSession().update(product);
		
	}

	
	public void removeProduct(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Products p= session
				.get(Products.class,id);
		sessionFactory.getCurrentSession().delete(p);
	}


	public void addWishlist(WishList wish)
	{
		try{
			Session session = sessionFactory.getCurrentSession();
			session.save(wish);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<WishList> searchInWishList(int userId) {
		List<WishList> list = null;
		
	Session session = sessionFactory.getCurrentSession();
	try{	
	list =  (List<WishList>) session
		.createCriteria(WishList.class).createAlias("user", "userMaster")
		.createAlias("product", "product")
		.add(Restrictions.eq("userMaster.id", userId))
		.list();
	
	}catch (Exception e) {
		e.printStackTrace();
	}
	return list;
	}
	
	

	
	public void removefromWishList(WishList wish) 
	{
		System.out.println("Dao mai aa gaya");
		Session session = sessionFactory.getCurrentSession();
		session.delete(wish);
		System.out.println("nikal diya usko ");
	}

	@SuppressWarnings("deprecation")
	public WishList getProductfromWishList(int userId, int productid) {
		WishList wish = null;
		try{
			 wish = (WishList) sessionFactory
					.getCurrentSession()
					.createCriteria(WishList.class)
					.createAlias("user", "userMaster")
					.createAlias("product", "product")
					.add(Restrictions.eq("userMaster.id", userId))
					.add(Restrictions.eq("product.id", productid))
					.uniqueResult();	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return wish;
	}

	public void addToCart(Cart cart) 
	{
		try{
			sessionFactory.getCurrentSession().save(cart);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void removeFromCart(Cart cart) 
	{
		try{
			sessionFactory.getCurrentSession().delete(cart);
		}catch(Exception e){
			e.printStackTrace();
			}
		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Cart> getProductsfromCart(int userId) {
		List<Cart> cart=null;
		try{
			cart =(List<Cart>) sessionFactory.getCurrentSession()
			.createCriteria(Cart.class)
			.createAlias("user", "userMaster")
			.createAlias("product", "product")
			.add(Restrictions.eq("userMaster.id", userId))
			.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cart;
	}

	public int saveOrder(Order order) {
		return (Integer) sessionFactory.getCurrentSession().save(order);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Order> getOrder() {
		return sessionFactory.getCurrentSession().createCriteria(Order.class).list();
		 
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Order> getOrder(String status) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(Order.class)
				.createAlias("status", "s")
				.add(Restrictions.eq("s.status", status))
				.list();
		 
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Payment> getPaymentList() {
		return sessionFactory.getCurrentSession().createCriteria(Payment.class).list();
		
	}

	@SuppressWarnings("deprecation")
	public Payment getPaymentByType(String Payment) {
		return (Payment) sessionFactory
				.getCurrentSession()
				.createCriteria(Payment.class)
				.add(Restrictions.eq("paymentType", Payment)).uniqueResult();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public List getDistinctProducts(int orderId) {
		
		List p =null;
		try{
			p = sessionFactory
		.getCurrentSession()
		.createCriteria(Order.class)
		.createAlias("product", "p")
		.add(Restrictions.eq("id", orderId))
		.setProjection(Projections.groupProperty("p.id"))
		.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public List getCountOfProducts(int orderId,int productId){
		List p =null;
		try{
		p=sessionFactory
		.getCurrentSession()
		.createCriteria(Order.class)
		.createAlias("product", "p")
		.add(Restrictions.eq("id", orderId))
		.add(Restrictions.eq("p.id", productId))
		.setProjection(Projections.rowCount())
		.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<String> getProductkaCompanyName(int categoryId) {
	return	sessionFactory
		.getCurrentSession()
		.createCriteria(Products.class)
		.createAlias("category", "c")
		.add(Restrictions.eq("c.category_id",categoryId))
		.setProjection(Projections.distinct(Projections.property("company")))
		.list();
	}

	@SuppressWarnings("deprecation")
	public Category getCategorybyType(String productType) {
		return (Category) sessionFactory
		.getCurrentSession()
		.createCriteria(Category.class)
		.add(Restrictions.eq("categoryName",productType))
		.uniqueResult();
		}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Products> getProductsByCompany(int categoryId, String company) {
		List<Products> p= null;
		try {
			p= (List<Products>)
		sessionFactory
		.getCurrentSession()
		.createCriteria(Products.class)
		.createAlias("category", "c")
		.add(Restrictions.eq("c.category_id", categoryId))
		.add(Restrictions.eqOrIsNull("company", company))
		.list();
			return p;
		}catch(Exception e) {
			return null;
		}
		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Products> getProductsByName(String name) {
		return sessionFactory
		.getCurrentSession()
		.createCriteria(Products.class)
		.add(Restrictions.eqOrIsNull("modelName", name))
		.list();
		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<OrderStatus> getOrderStatus() {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(OrderStatus.class)
				.list();
		 
	}

	public void updateOrder(Order order) {
		sessionFactory
		.getCurrentSession()
		.update(order);
	}

	@SuppressWarnings("deprecation")
	public OrderStatus getOrderStatusByStatus(String status) {
		return (OrderStatus) sessionFactory
		.getCurrentSession()
		.createCriteria(OrderStatus.class)
		.add(Restrictions.eq("status", status))
		.uniqueResult();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Products> getLatestProducts() {
		return 
		sessionFactory
		.getCurrentSession()
		.createCriteria(Products.class)
		.addOrder(org.hibernate.criterion.Order.desc("creationDate"))
		.setMaxResults(5)
		.list();
	}

	public void saveCancelledOrder(UserCancelledOrders uco) {
		sessionFactory
		.getCurrentSession()
		.save(uco);
		
	}
	
	
}