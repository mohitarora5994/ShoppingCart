package com.neosofttech.service;

import java.util.List;

import com.neosofttech.model.Address;
import com.neosofttech.model.CancelOrderReasons;
import com.neosofttech.model.LoginForm;
import com.neosofttech.model.Order;
import com.neosofttech.model.UserMaster;

public interface UserMasterService {

	public String save(UserMaster userMaster);
	public List<UserMaster> getUserList();
	public void removeUser(int id);
	public UserMaster getUser(int id);
	public String updateUser(int id,String name,String username,String mobileno,String email);
	public UserMaster getUserByEmail(String email);
	public boolean Login(LoginForm login);
	public UserMaster findByUsername(String username);
	
	public void addAdress(int userId,String address);
	public List<Address> getAddress(int userid);
	
	public Order getOrderById(int id);
	List<Order> getOrderByUser(int userId);
	List<Order> getOrderByStatusNUser(int userId,String status);
	
	public String socialLogin(String name,String email,int roleId);
	String cancelOrder(int userId,int orderId,String reason);
	List<CancelOrderReasons> getCancelKReasons();
}