package com.neosofttech.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class LoginForm 
{
	@NotEmpty (message="Please Enter Your UserName")
	private String username;
	@NotEmpty(message="Enter Your password")
	private String password;
}