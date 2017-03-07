package com.neosofttech.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data 
public class Seller 
{

	@Id @GeneratedValue
	private int id;
	
	@NotEmpty(message="Naam dal")
	private String name;
	
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	        message="Email id bhul gaya kya??")
	private String email;
	
	@NotEmpty(message="UserName SELLER")
	private String username;
	
	@Size(min=4,max=16,message="Acha password dal")
	private String password;
	
	@Transient
	private String rpassword;
	
	@Lob
	private String address;
	
	@Pattern(regexp="\\d{10}",message="mobile no hi daal")
	private String mobileno;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	private boolean active;
}
