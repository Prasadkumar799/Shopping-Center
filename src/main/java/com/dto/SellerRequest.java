package com.dto;

public class SellerRequest {
	private String sellerName;
	private String sellerEmail;
	private String sellerPassword;
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public String getSellerPassword() {
		return sellerPassword;
	}
	public void setSellerPassword(String sellerPassword) {
		this.sellerPassword = sellerPassword;
	}
	public SellerRequest(String sellerName) {
		super();
		this.sellerName = sellerName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public SellerRequest(String sellerName, String sellerEmail, String sellerPassword) {
		super();
		this.sellerName = sellerName;
		this.sellerEmail = sellerEmail;
		this.sellerPassword = sellerPassword;
	}
	public SellerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
