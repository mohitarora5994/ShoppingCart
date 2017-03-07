package com.neosofttech.Dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neosofttech.model.Address;
import com.neosofttech.model.CancelOrderReasons;
import com.neosofttech.model.Order;
import com.neosofttech.model.UserMaster;
import com.neosofttech.model.UserRole;
@Repository
public class UserMasterDaoImpl implements UserMasterDao 
{
	@Autowired
	SessionFactory sessionFactory;

	final static Logger logger=Logger.getLogger(UserMasterDaoImpl.class);
	
	/*method to save a new user in database.*/
	public int save(UserMaster userMaster) 
	{
		int id=0;
		try{
			id= (Integer) sessionFactory.getCurrentSession().save(userMaster);
			logger.info("New User Saved with username "+userMaster.getName());
		}catch(Exception e){
			logger.error(e);
		}
		return id;
	}

	/*method to get list of user from database.*/
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<UserMaster> getUserList() 
	{
		List<UserMaster> user = null;
		try{
			user = sessionFactory
					.getCurrentSession()
					.createCriteria(UserMaster.class)
					.list();
		}catch(Exception e){
			logger.error(e);
		}
		return user;
	}

	public void updateUser(UserMaster userMaster) 
	{
		sessionFactory.getCurrentSession().update(userMaster);
		logger.info("User updated successfully");
		
	}

	/*method to remove user from database.*/
	public void removeUser(int id) 
	{
		UserMaster u = sessionFactory
				.getCurrentSession()
				.get(UserMaster.class, id);
		if(null!=u)
			sessionFactory.getCurrentSession().delete(u);	
		
	}

	/*Method to find user by Id;*/
	@Transactional
	public UserMaster findUserById(int id) 
	{
		return (UserMaster) sessionFactory
				.getCurrentSession()
				.get(UserMaster.class, id);
		}

	@SuppressWarnings({ "deprecation" })
	public UserMaster findByEmail(String email) 
	{
		
		UserMaster o= (UserMaster) sessionFactory.getCurrentSession()
				.createCriteria(UserMaster.class)
				.add(Restrictions.eqOrIsNull("email", email))
				.uniqueResult();
		if(o==null){
			return null;
		}
			return o;
	}

	@SuppressWarnings({ "deprecation" })
	@Transactional
	public UserMaster findByUsername(String username) 
	{
		
		try {
		UserMaster user=(UserMaster) sessionFactory.getCurrentSession()
					.createCriteria(UserMaster.class)
					.add(Restrictions
					.eqOrIsNull("username", username))
					.uniqueResult();
		if (user==null){
			return null;
		}
		return user;
		}catch(Exception e) {
							e.printStackTrace();
							}
		return null;
	}

	public void addAdress(Address address) {
		sessionFactory.getCurrentSession().save(address);
		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Address> getAddress(int userid) {
		List<Address> add =null;
		try{
		add = sessionFactory.getCurrentSession()
		.createCriteria(Address.class)
		.createAlias("user", "userMaster")
		.add(Restrictions.eq("userMaster.id", userid))
		.list();
		}catch(Exception e)
		{System.out.println("Address fetch nai ho raha hai");}
		return add;
	}

	public Address getAddressById(int id) {
		return  sessionFactory.getCurrentSession().get(Address.class, id);
		
	}

	public Order getOrderById(int id) {
		return  sessionFactory.getCurrentSession().get(Order.class, id);
	}

	@SuppressWarnings("deprecation")
	public UserRole getRole(String role) {
		return (UserRole) sessionFactory
				.getCurrentSession()
				.createCriteria(UserRole.class)
				.add(Restrictions.eq("role", role))
				.uniqueResult();
	}

	public UserRole getRoleById(int id) {
		return (UserRole)sessionFactory.getCurrentSession().get(UserRole.class, id);
		
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Order> getOrderByUser(int userId) {
		return sessionFactory
		.getCurrentSession()
		.createCriteria(Order.class)
		.createAlias("user", "u")
		.add(Restrictions.eq("u.id", userId))
		.list();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Order> getOrderByStatus(int userId, String status) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(Order.class)
				.createAlias("user", "u")
				.createAlias("status", "s")
				.add(Restrictions.eq("u.id", userId))
				.add(Restrictions.eq("s.status", status))
				.list();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<CancelOrderReasons> getCancelKReasons() {
		
		return sessionFactory
				.getCurrentSession()
				.createCriteria(CancelOrderReasons.class)
				.list();
	}

	
	

}