package com.dto;

public class FavoritesRequest {
	
	private long userID;
	private long productID;
	
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public FavoritesRequest(long userID, long productID) {
		super();
		this.userID = userID;
		this.productID = productID;
	}

	@Override
	public String toString() {
		return "FavoritesRequest [userID=" + userID + ", productID=" + productID + "]";
	}
	
	
}
