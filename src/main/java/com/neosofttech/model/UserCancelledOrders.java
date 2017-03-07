package com.neosofttech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity @Data
public class UserCancelledOrders 
{
	@Id
	@GeneratedValue
	private int id;
	
	private String reason;
	@OneToOne
	@JoinColumn(name="orderId")
	private Order order;
	
	@OneToOne
	@JoinColumn(name="userId")
	private UserMaster user;
}
