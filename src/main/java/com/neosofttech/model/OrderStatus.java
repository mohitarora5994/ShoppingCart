package com.neosofttech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class OrderStatus 
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="Status")
	private String status;
	/*Status can be delivered pending canceled*/
}
