package com.neosofttech.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neosofttech.model.Seller;
@Component
public class SellerValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		
		return Seller.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Seller seller = (Seller) target;
		String password = seller.getPassword();
		String rpassword = seller.getRpassword();
		if(!password.equals(rpassword)){
			errors.rejectValue("rpassword", "UserPasswordMismatch");
		}
	}

}
