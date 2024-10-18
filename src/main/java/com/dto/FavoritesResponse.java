package com.dto;

public class FavoritesResponse {
	private long favoriteID;
	private long userID;
	private long productID;
	
	public FavoritesResponse(long favoriteID, long userID, long productID) {
		super();
		this.favoriteID = favoriteID;
		this.userID = userID;
		this.productID = productID;
	}

	public long getFavoriteID() {
		return favoriteID;
	}

	public void setFavoriteID(long favoriteID) {
		this.favoriteID = favoriteID;
	}

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

	@Override
	public String toString() {
		return "FavoritesResponse [favoriteID=" + favoriteID + ", userID=" + userID + ", productID=" + productID + "]";
	}
	
	
}
