package com.neosofttech.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neosofttech.model.UserMaster;

@Component
public class UserValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return UserMaster.class.isAssignableFrom(arg0);
	}

	public void validate(Object target, Errors errors) {
		UserMaster userMaster =(UserMaster) target;
		String password=userMaster.getPassword();
		String rpassword=userMaster.getRpassword();
		if(!password.equals(rpassword)){
			errors.rejectValue("rpassword", "UserPasswordMismatch");
		}
	}

}