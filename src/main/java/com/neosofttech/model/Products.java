package com.neosofttech.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
@Data
@Entity
@Table(name="Products")
public class Products 
{

	@Id
	@GeneratedValue
	private int id;
	@Column(name="name")
	@NotEmpty(message="Cannot be empty")
	private String modelName;
	@NotEmpty(message="Cannot be empty")
	private String company;
	@NotEmpty(message="Cannot be empty")
	private String price;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Lob
	@NotEmpty(message="Cannot be empty")
	private String url;	
	
	private boolean available;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Category category;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL)
	private Collection<WishList> wishList;
}