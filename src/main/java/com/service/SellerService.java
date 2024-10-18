package com.service;

import java.util.List;

import com.dao.ProductsDAOClass;
import com.dao.SellerDAOClass;
import com.dto.ProductResponse;
import com.dto.SellerRequest;
import com.dto.SellerResponse;

public class SellerService {
	private final SellerDAOClass sellerDAO = new SellerDAOClass();
	private final ProductsDAOClass productDAO = new ProductsDAOClass();

	public SellerResponse getsellerById(long sellerID) {
		return sellerDAO.getsellerById(sellerID);
	}
	public boolean addSeller(SellerRequest addseller) {
		return sellerDAO.addSeller(addseller);
	}
	public List<SellerResponse> getAllSeller(){
		return sellerDAO.getAllSeller();
	}
	public String deleteSeller(long sellerID) {
		return sellerDAO.deleteSeller(sellerID);
	}
	public SellerResponse getSeller(String email, String password) {
		return sellerDAO.getSeller(email, password);
	}
	public List<ProductResponse> getProductBySellerId(long sellerID) {
		return productDAO.getProductBySellerId(sellerID);
	}
	
	
	public static void main(String[] args) {
		SellerService ss=new SellerService();
    	SellerRequest seller=new SellerRequest("Pavan sai","pavan@gmail.com","1234");

		System.out.println(ss.addSeller(seller));
	
	}
}
