package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.dto.ProductResponse;
import com.utils.ConnectionFactory;

public class FavoritesDAOClass implements FavoritesDAO {
	private final static Logger logger=LoggerFactory.getLogger(FavoritesDAOClass.class);
	@Override
	public  ProductResponse getFavoriteById(long user_id,long productID) {
		String query ="select * from Products join Favorites on Products.productID=Favorites.productID where Favorites.user_id=? and Favorites.productID=?";
		MDC.put("method", "getFavoriteById");
		MDC.put("userId", String.valueOf(user_id));
		MDC.put("productId", String.valueOf(productID));
		logger.info("Show Favorite Items By ID",productID);
		
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt =con.prepareStatement(query)){
			stmt.setLong(1, user_id);
			stmt.setLong(2, productID);
			ResultSet result=stmt.executeQuery();
			if(!result.next()) {
				logger.debug("No favorite Items");
				return null;
			}
			else {
				logger.info("Successfully Retrieved Favorite Items");
				return new ProductResponse(result.getLong("productID"),result.getString("productName"),result.getString("productInfo"),result.getDouble("productPrice"),
						result.getInt("productQuantity"),result.getLong("categoryID"),result.getLong("sellerID"),result.getString("imageURL"));
	
			}
			
			
		}catch(SQLException e) {
			logger.error("SQL Exception accured while Retrieving Favorite Items");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addToFavorites(long user_id, long productID) {
	    String query = "INSERT INTO Favorites (user_id, productID) VALUES (?, ?)";
	    MDC.put("method", "addToFavorites");
	    MDC.put("productId", String.valueOf(productID));
	    MDC.put("userId", String.valueOf(user_id));
	    
	    try {
	        ProductResponse favpro = getFavoriteById(user_id, productID);
	        if (favpro != null) {
	            logger.info("Product already in Favorites. User ID: {}, Product ID: {}", user_id, productID);
	            return true;
	        }

	        try (Connection con = ConnectionFactory.getconnectionFactory().getConnection();
	             PreparedStatement stmt = con.prepareStatement(query)) {
	             
	            stmt.setLong(1, user_id);
	            stmt.setLong(2, productID);
	            int result = stmt.executeUpdate();
	            if (result > 0) {
	                logger.info("Successfully added to Favorites. User ID: {}, Product ID: {}", user_id, productID);
	                return true;
	            } else {
	                logger.warn("Failed to add to Favorites. No rows affected. User ID: {}, Product ID: {}", user_id, productID);
	                return false;
	            }

	        } catch (SQLException e) {
	            logger.error("SQL Exception occurred while adding item to Favorites. User ID: {}, Product ID: {}", user_id, productID, e);
	            return false;
	        }

	    } finally {
	        MDC.clear();  
	    }
	}


	@Override
	public boolean removeFromFavorite(long user_id, long productID) {
		String query="delete from Favorites where user_id=? and productID=?";
		MDC.put("method", "deleteFavorite");
		MDC.put("productId",String.valueOf(productID));
		MDC.put("userId", String.valueOf(user_id));
		logger.info("Remove Item from Favorites",productID);
		try(Connection con =ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, user_id);
			stmt.setLong(2, productID);
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Product removed From Favorites");
				return true;
			}else{
				logger.info("Enter valid IDs to Remove");
				return false;
			}
		}catch(SQLException e) {
			logger.error("SQL Exception accurred while Removing item from Favorites");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean moveToCart(long user_id, long productID) {
		String query="insert into Cart (user_id,productID) values(?,?)";
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
	public List<ProductResponse> getAllFavorites(long user_id) {
		String query="select * from Products join Favorites on Favorites.productID=Products.productID where user_id=?";
		List<ProductResponse> favpro=new ArrayList();
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1,user_id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				ProductResponse product = new ProductResponse(
                        rs.getLong("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductInfo"),
                        rs.getDouble("ProductPrice"),
                        rs.getInt("ProductQuantity"),
                        rs.getLong("CategoryID"),
                        rs.getLong("SellerID"),
                        rs.getString("ImageURL")
                );
                favpro.add(product);
			}
			return favpro;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
