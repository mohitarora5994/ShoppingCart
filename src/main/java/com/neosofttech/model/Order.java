package com.neosofttech.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Data
@Entity
@Table(name="Order_List")
public class Order 
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="price")
	private int totalPrice;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Collection<Products> product;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserMaster user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate; 
	
	@ManyToOne
	@JoinColumn(name="status")
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name="paymentType")
	private Payment payment;
	
	@Lob
	private String address;
}
