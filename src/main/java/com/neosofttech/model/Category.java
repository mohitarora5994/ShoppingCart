package com.neosofttech.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Data
@Entity
@Table(name="Category")
public class Category 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int category_id;
	
	@Column(name="name")
	private String categoryName;
	
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.EAGER,
			mappedBy="category")
	@Fetch(FetchMode.SELECT)
	private List<Products> products;
}