package com.dto;

import java.sql.Timestamp;

public class ReviewRequest {
	private long productID;
	private long user_id;
	private int rating;
	private String reviewText;
//	private Timestamp reviewDate;
	public ReviewRequest(long productID, long user_id, int rating, String reviewText) {
		super();
		this.productID = productID;
		this.user_id = user_id;
		this.rating = rating;
		this.reviewText = reviewText;
//		this.reviewDate = reviewDate;
	}
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
//	public Timestamp getReviewDate() {
//		return reviewDate;
//	}
//	public void setReviewDate(Timestamp reviewDate) {
//		this.reviewDate = reviewDate;
//	}
//	@Override
//	public String toString() {
//		return "ReviewRequest [productID=" + productID + ", user_id=" + user_id + ", rating=" + rating + ", reviewText="
//				+ reviewText + ", reviewDate=" + reviewDate + "]";
//	}
	
	
}
