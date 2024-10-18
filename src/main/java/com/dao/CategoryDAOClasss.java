package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.MDC;

import com.dto.CategoryRequest;
import com.dto.CategoryResponse;
import com.dto.ProductResponse;
import com.utils.ConnectionFactory;

public class CategoryDAOClasss implements CategoryDAO {
	private final static Logger logger=LoggerFactory.getLogger(CategoryDAOClasss.class);
	
	@Override
	public boolean addCategory(CategoryRequest categoryRequest) {
		String query ="insert into Categories (categoryName) values(?)";
		MDC.put("method","addCategory");
		logger.info("insert  Category For Products");
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setString(1, categoryRequest.getCategoryName());
			int  result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Successfully Inserted Category : ",categoryRequest.getCategoryName());
				return true;
			}else {
				logger.info("Not Inserted",categoryRequest.getCategoryName());
				return false;
			}	
		}catch(SQLException e) {
			logger.error("SQL exception accurred while inserting Category",e);
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<CategoryResponse> showCategories() {
	    String query = "SELECT * FROM Categories";
	    MDC.put("method", "showCategories");
	    logger.info("Fetching all categories from the database.");
	    List<CategoryResponse> allCategories = new ArrayList<>();

	    try (Connection con = ConnectionFactory.getconnectionFactory().getConnection();
	         Statement stmt = con.createStatement()) {
	        ResultSet result = stmt.executeQuery(query);
	        while (result.next()) {
	            CategoryResponse category = new CategoryResponse(
	                result.getLong("CategoryID"),
	                result.getString("CategoryName")
	            );
	            allCategories.add(category);
	        }   
	        logger.debug("Categories retrieved: " + allCategories.size());
	        return allCategories;

	    } catch (SQLException e) {
	        logger.error("SQL Exception while retrieving categories", e);
	        logger.warn("Returning an empty list due to SQL exception.");
	        return new ArrayList<>();
	    }
	}


	@Override
	public boolean deleteCategory(long categoryID) {
		String query="delete from Categories where categoryID=?";
		MDC.put("method", "deleteCategory");
		MDC.put("categoryId", String.valueOf(categoryID));
		logger.info("Delete Category From Database");
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Category deleted ");
				return true;
			}else {
				logger.error("Enter proper Category ID to delete");
			}
		}catch(SQLException e) {
			logger.error("SQL Exception accurred while Deleting Category",e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ProductResponse> selectCategoryById(long categoryID) {
		String query="select * from Products join Categories on Products.categoryID=Categories.categoryID where Categories.categoryID=?";
		List<ProductResponse> products=new ArrayList();
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, categoryID);
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
                products.add(product);
			}
			
			return products;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
