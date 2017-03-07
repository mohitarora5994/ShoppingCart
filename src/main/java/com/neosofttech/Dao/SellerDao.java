package com.neosofttech.Dao;

import com.neosofttech.model.Seller;

public interface SellerDao 
{
public int saveSeller(Seller seller);
public void updateSeller(Seller seller);
public Seller findSellerByUserName(String username);
public Seller findSellerByEmail(String email);
}
