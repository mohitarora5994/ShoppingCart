package com.neosofttech.Dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neosofttech.model.Seller;
@Repository
@Transactional
public class SellerDaoImpl implements SellerDao
{
	@Autowired
	SessionFactory sessionFactory;
	
	public int saveSeller(Seller seller) {
		return (Integer) sessionFactory
		.getCurrentSession()
		.save(seller);
	}

	public void updateSeller(Seller seller) {
		sessionFactory
		.getCurrentSession()
		.update(seller);
		
	}

	@SuppressWarnings("deprecation")
	public Seller findSellerByUserName(String username) {
		
		
		Seller s =  (Seller) sessionFactory
				.getCurrentSession()
				.createCriteria(Seller.class)
				.add(Restrictions.eq("username", username))
				.uniqueResult();
		
		return s;
	}

	@SuppressWarnings("deprecation")
	public Seller findSellerByEmail(String email) {
		return (Seller) sessionFactory
				.getCurrentSession()
				.createCriteria(Seller.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
	}

}
