package com.service;

import java.util.List;

import com.dao.ReviewsDAOClass;
import com.dto.ReviewRequest;
import com.dto.ReviewResponse;

public class ReviewsService {
	private final ReviewsDAOClass reviewDAO = new ReviewsDAOClass();
	
	public boolean addReview(ReviewRequest reviewRequest) {
		return  reviewDAO.addReview(reviewRequest);
	}
	public boolean deleteReview(long user_id,long productID) {
		return reviewDAO.deleteReview(user_id, productID);
	}
	public List<ReviewResponse> showReviews( long productID) {
		return reviewDAO.showReviews(productID);
	}
//	public static void main(String[] args) {
//		ReviewsService rs=new ReviewsService();
//        ReviewRequest review=new ReviewRequest(19,4,5,"most impressive dual-camera system on iPhone. Capture stunning photos in low light and bright light.");
//		System.out.println(rs.addReview(review));
//		List<ReviewResponse> reviews=rs.showReviews(4);
//		for(ReviewResponse rev:reviews) {
//			System.out.println(rev);
//		}
//	}
}
