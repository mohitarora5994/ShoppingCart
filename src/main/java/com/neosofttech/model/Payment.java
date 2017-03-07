package com.neosofttech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Payment 
{
	@Id
	@GeneratedValue
	private int id;
	
	private String paymentType;	
	/*paymentType include 
	1)cash on delivery
	2)Debit card
	3)Credit card
	4)online banking
	*/
}
