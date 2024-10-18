package com.entity;

public class Sellers {
	private long sellerID;
	private String sellerName;
	private String sellerEmail;
	private String sellerPassword;
	public Sellers(long sellerID, String sellerName,String sellerEmail,String sellerPassword) {
		super();
		this.sellerID = sellerID;
		this.sellerName = sellerName;
		this.sellerEmail=sellerEmail;
		this.sellerPassword=sellerPassword;
	}
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
	public long getSellerID() {
		return sellerID;
	}
	public void setSellerID(long sellerID) {
		this.sellerID = sellerID;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	
	
	
 
}
