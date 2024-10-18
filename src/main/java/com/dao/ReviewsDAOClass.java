package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dto.ReviewRequest;
import com.dto.ReviewResponse;
import com.utils.ConnectionFactory;

public class ReviewsDAOClass implements ReviewsDAO{

	@Override
	public boolean addReview(ReviewRequest reviewRequest) {
		String query="insert into Reviews(productID,user_id,rating,reviewText)values(?,?,?,?)";
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, reviewRequest.getProductID());
			stmt.setLong(2, reviewRequest.getUser_id());
			stmt.setInt(3, reviewRequest.getRating());
			stmt.setString(4, reviewRequest.getReviewText());
			int result=stmt.executeUpdate();
			if(result>0) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteReview(long user_id, long productID) {
		String query="delete  from Reviews where user_id=?,productID=?";
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, user_id);
			stmt.setLong(2, productID);
			int result=stmt.executeUpdate();
			if(result>0) {
				return true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateReview(long user_id, long productID, int rating, String reviewText) {
		String query="update Reviews set rating=?,reviewText=? where user_id=? and productID=?";
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setInt(1, rating);
			stmt.setString(2, reviewText);
			stmt.setLong(3, user_id);
			stmt.setLong(4, productID);
			int result=stmt.executeUpdate();
			if(result>0) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ReviewResponse> showReviews( long productID) {
		String query="Select * from Reviews where  productID=?";
		List<ReviewResponse> reviews=new ArrayList();
		try(Connection con =ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, productID);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				ReviewResponse review=new ReviewResponse(rs.getLong("reviewID"),rs.getLong("productID"),rs.getLong("user_id"),
						rs.getInt("rating"),rs.getString("reviewText"),rs.getTimestamp("reviewDate"));
				reviews.add(review);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reviews;
	}

}
