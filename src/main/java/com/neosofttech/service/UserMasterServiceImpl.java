package com.neosofttech.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosofttech.Dao.ProductsDao;
import com.neosofttech.Dao.UserMasterDao;
import com.neosofttech.model.Address;
import com.neosofttech.model.CancelOrderReasons;
import com.neosofttech.model.LoginForm;
import com.neosofttech.model.Order;
import com.neosofttech.model.OrderStatus;
import com.neosofttech.model.UserCancelledOrders;
import com.neosofttech.model.UserMaster;
import com.neosofttech.model.UserRole;

@Service
@Transactional
public class UserMasterServiceImpl implements UserMasterService 
{

	@Autowired
	UserMasterDao userMasterDao;
	
	@Autowired
	ProductsDao productsDao;
	
	final static Logger logger=Logger.getLogger(UserMasterServiceImpl.class);

	
	public boolean Login(LoginForm login) 
	{
		UserMaster u =userMasterDao.findByUsername(login.getUsername());
		if(u!=null && u.isAvailable())
		{
			if(u.getPassword().equals(login.getPassword()))
			{
				logger.info("Password Matched");
				return true;
			}
			else 
			{
				logger.info("Password didnt Match");
				return false;
			}			
		}
		else
		{		
			logger.info("UserName do not exist");
			return false;
		}
	}
	public String socialLogin(String name, String email,int roleId) {
		UserMaster user = userMasterDao.findByEmail(email);
		if(user!=null)
		{
			return "email exits";
		}
		UserMaster u = new UserMaster();
		UserRole role =userMasterDao.getRoleById(roleId);
		u.setCreationDate(new Date());
		u.setAvailable(true);
		u.setEmail(email);
		u.setName(name);
		u.setRole(role);
		u.setUsername(email);
		int st =userMasterDao.save(u);
		return String.valueOf(st);
	}
	
	
	public String save(UserMaster userMaster) 
	{
		UserMaster username = userMasterDao.findByUsername(userMaster.getUsername());
		if(username!=null){
			return "username exits";
		}else{
			UserMaster email =userMasterDao.findByEmail(userMaster.getEmail());
			if(email!=null){
				return "email exits";
			}
		}		
		UserRole role =userMasterDao.getRoleById(3);
		userMaster.setRole(role);
		userMaster.setAvailable(true);
		userMaster.setCreationDate(new Date());
		int st=  userMasterDao.save(userMaster);
		return Integer.toString(st);
	}

	
	public List<UserMaster> getUserList() 
	{
		return userMasterDao.getUserList();
	}

	
	public void removeUser(int id) 
	{
		UserMaster u = userMasterDao.findUserById(id);
		u.setAvailable(false);
		userMasterDao.updateUser(u);		
	}

	
	
	public UserMaster getUser(int id) 
	{
		return userMasterDao.findUserById(id);
	}

	
	
	public String updateUser(int id,String name,String username,String mobileno,String email) 
	{
		UserMaster u = userMasterDao.findUserById(id);
		u.setMobileno(mobileno);
		u.setName(name);
		UserMaster u1=userMasterDao.findByEmail(email);
		UserMaster u2=userMasterDao.findByUsername(username);
		if(u1==null || u1.getId()==id) 
		{
			u.setEmail(email);
		}else {
			return "Email Exists";
		}
		if(u2==null || u2.getId()==id) 
		{
			u.setUsername(username);
		}else {
			return "UserName Exists";
		}
		userMasterDao.updateUser(u);
		return "Successfully Update";
	}


	public UserMaster getUserByEmail(String email) {
		
		return (UserMaster) userMasterDao.findByEmail(email);
	}


	
	public UserMaster findByUsername(String username) {
		return (UserMaster) userMasterDao.findByUsername(username);
	}


	public void addAdress(int userId, String address) {
		UserMaster user = userMasterDao.findUserById(userId);
		Address add= new Address();
		add.setAddress(address);
		add.setUser(user);
		userMasterDao.addAdress(add);		
	}



	public List<Address> getAddress(int userid) {
		 List<Address> add = null;
		 add=userMasterDao.getAddress(userid);
		 return add;
	}



	public Order getOrderById(int id) {
		return userMasterDao.getOrderById(id);
		 }
	public List<Order> getOrderByUser(int userId) {
		return userMasterDao.getOrderByUser(userId);
	}
	public List<Order> getOrderByStatusNUser(int userId, String status) {
		
		return userMasterDao.getOrderByStatus(userId, status);
	}
	public String cancelOrder(int userId, int orderId,String reason) {
		Order o = userMasterDao.getOrderById(orderId);
		if(o.getUser().getId()==userId) 
		{
			UserMaster user = userMasterDao.findUserById(userId);
			OrderStatus st = productsDao.getOrderStatusByStatus("Cancelled");
			o.setStatus(st);
			UserCancelledOrders uco = new UserCancelledOrders();
			uco.setUser(user);
			uco.setReason(reason);
			uco.setOrder(o);
			productsDao.saveCancelledOrder(uco);
			productsDao.updateOrder(o);
			return "orderCancelled";
		}
		return "Kuch Gadbad Hai";
	}
	public List<CancelOrderReasons> getCancelKReasons() {
		return userMasterDao.getCancelKReasons();
	}





	

}