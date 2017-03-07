package com.neosofttech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity @Data
public class SellerProducts 
{

	@Id @GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name="sellerId")
	private Seller seller;
	@ManyToOne 
	@JoinColumn(name="productId")
	private Products product;
	
	private int quantity;
}
