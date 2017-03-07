package com.neosofttech.Dao;

import java.util.List;

import com.neosofttech.model.Address;
import com.neosofttech.model.CancelOrderReasons;
import com.neosofttech.model.Order;
import com.neosofttech.model.UserMaster;
import com.neosofttech.model.UserRole;

public interface UserMasterDao {

	public int save(UserMaster userMaster);
	public List<UserMaster> getUserList();

	public UserRole getRole(String role);
	public UserRole getRoleById(int id);
	
	public void updateUser(UserMaster userMaster);
	public void removeUser(int id);
	
	public UserMaster findUserById(int id);
	public UserMaster findByEmail(String email);
	public UserMaster findByUsername(String username);
	
	public void addAdress(Address address);
	public List<Address> getAddress(int userid);
	public Address getAddressById(int id);
	
	public Order getOrderById(int id);
	public List<Order> getOrderByUser(int userId);
	public List<Order> getOrderByStatus(int userId, String status);
	List<CancelOrderReasons> getCancelKReasons();
}