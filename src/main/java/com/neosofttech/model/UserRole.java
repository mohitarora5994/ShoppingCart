package com.neosofttech.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
@Entity @Data
public class UserRole 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int roleid;
	
	private String role;
	/*roles can be admin dba user*/
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="role")
	private List<UserMaster> userRoles;
	
}