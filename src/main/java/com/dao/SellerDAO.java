package com.dao;

import java.util.List;

import com.dto.ProductResponse;
import com.dto.SellerRequest;
import com.dto.SellerResponse;

public interface SellerDAO {
	SellerResponse getsellerById(long sellerID);
	boolean addSeller(SellerRequest addseller);
	List<SellerResponse> getAllSeller();
	String deleteSeller(long sellerID);
	SellerResponse getSeller(String email,String password);
}
