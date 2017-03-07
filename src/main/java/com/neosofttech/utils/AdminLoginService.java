package com.neosofttech.utils;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.neosofttech.Dao.SellerDao;
import com.neosofttech.Dao.UserMasterDao;
import com.neosofttech.model.Seller;
import com.neosofttech.model.UserMaster;

@Component("adminLoginService")
public class AdminLoginService implements UserDetailsService
{
	@Autowired
	UserMasterDao userMasterDao;
	
	@Autowired
	SellerDao sellerDao;
	
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException 
	{
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		UserMaster user = userMasterDao.findByUsername(username);
		if(user==null) {
			Seller seller = sellerDao.findSellerByUserName(username);
			if(seller!=null) {
				authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
				return new User(seller.getUsername(), seller.getPassword(), authorities);
			}
			
		}
		
		authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getRole().toUpperCase()));
		return new User(user.getUsername(), user.getPassword(),authorities);		
	}

}
