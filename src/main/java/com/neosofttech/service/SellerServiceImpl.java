package com.neosofttech.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosofttech.Dao.SellerDao;
import com.neosofttech.Dao.UserMasterDao;
import com.neosofttech.model.Seller;
import com.neosofttech.model.UserMaster;

@Service
@Transactional
public class SellerServiceImpl implements SellerService
{
	@Autowired
	SellerDao sellerDao;

	@Autowired
	UserMasterDao userMasterDao;
	
	public String saveSeller(Seller seller) 
	{
		Seller s1 = sellerDao.findSellerByEmail(seller.getEmail());
		if(s1!=null) {
			return "Email Exists";
		}else {
			Seller s2= sellerDao.findSellerByUserName(seller.getUsername());
			if(s2!=null) {
				return "UserName Exists";
			}else {
				UserMaster user  = userMasterDao.findByUsername(seller.getUsername());
				if(user!=null) {
					return "UserName Exists";
				}
			}
			seller.setActive(true);
			seller.setCreationDate(new Date());
			sellerDao.saveSeller(seller);
		}
		return "Successfully saved";
	}

}
