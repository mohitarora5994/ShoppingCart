package com.neosofttech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue
	private int id;
	
	@Lob
	private String address;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserMaster user;
}
