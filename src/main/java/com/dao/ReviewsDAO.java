package com.dao;

import java.util.List;

import com.dto.ReviewRequest;
import com.dto.ReviewResponse;

public interface ReviewsDAO {
	boolean addReview(ReviewRequest reviewRequest);
	boolean deleteReview(long user_id,long productID);
	boolean updateReview(long user_id,long productID,int rating,String reviewText);
	List<ReviewResponse> showReviews(long productID);
	
}
