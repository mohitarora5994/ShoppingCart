package com.neosofttech.model;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data 
@Entity 
@Table(name="UserMaster")
public class UserMaster 
{
	@Id 
	@GeneratedValue
	private int id;
	
	@NotEmpty(message="Naam dal")
	private String name;
	
	@NotEmpty(message="UserName Bachi")
	private String username;
	
	@Size(min=4,max=16,message="Acha password dal")
	private String password;
	
	@Pattern(regexp="\\d{10}",message="mobile no hi daal")
	private String mobileno;
	
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	        message="Email id bhul gaya kya??")
	private String email;
	
	@Transient
	private String rpassword;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	private boolean available;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	private UserRole role;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<WishList> wishList;
	
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Address> address;
	
	public UserMaster(String username, String password, List<UserRole> role) {
		super();
		this.username = username;
		this.password = password;
		this.role = (UserRole) role;
	}

	public UserMaster() {
		super();
	}
	
	
	
}