package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.dto.ProductRequest;
import com.dto.ProductResponse;
import com.utils.ConnectionFactory;

public class ProductsDAOClass implements ProductsDAO{
	 private static final Logger logger = LoggerFactory.getLogger(ProductsDAOClass.class);
	@Override
	public ProductResponse getProductById(long product_id) {
		MDC.put("method", "getProductById");
		MDC.put("productId", String.valueOf(product_id));
		logger.info("Fetching Products Details from Database with ID : ",product_id);
		String query ="select * from Products where productID=?";
		ProductResponse productResponse=null;
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setLong(1, product_id);
			ResultSet result=stmt.executeQuery();
			if(result.next()) {
				
				long productid=result.getLong("productID");
				String product_name=result.getString("productName");
				String product_info=result.getString("productInfo");
				double product_price=result.getDouble("productPrice");
				int product_quantity=result.getInt("productQuantity");
				long category_id=result.getLong("categoryID");
				long seller_id =result.getLong("sellerID");
				String image_URL=result.getString("imageURL");
				productResponse=new ProductResponse(productid,product_name,product_info,product_price,
						product_quantity,category_id,seller_id,image_URL);
				System.out.println(productResponse.toString());
			}
			logger.info("Fetched Product details Successfully with ID :",product_id);
			return productResponse;
		}
		catch(SQLException e) {
			logger.error("SQL Error accurred while getting Product by ID : ",e);
			
		}
		return null;
	}

	@Override
	public boolean createProduct(ProductRequest productRequest) {
		String query="insert into Products (productName,productInfo,productPrice,productQuantity,categoryID,sellerID,imageURL) values(?,?,?,?,?,?,?)";
		MDC.put("method", "createProduct");
		logger.info("Inserting  Product Details into Database");
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection()){
			
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setString(1, productRequest.getProductName());
			stmt.setString(2, productRequest.getProductInfo());
			stmt.setDouble(3, productRequest.getProductPrice());
			stmt.setInt(4, productRequest.getProductQuantity());
			stmt.setLong(5, productRequest.getCategoryID());
			stmt.setLong(6, productRequest.getSellerID());
			stmt.setString(7, productRequest.getImageURL());
			
			int result=stmt.executeUpdate();
			con.close();
			if(result>0) {
				logger.info("Product Inserted Successfully",productRequest.getProductName());
				return true;
			}else {
				logger.error("Enter Propoer details about Product to Insert into Database");
			}
			
			
		}catch(SQLException e) {
			logger.error("SQL Error accurred while inserting Product details");
	        e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateProduct(ProductResponse productResponse) {
		String query="update Products set productName=?,productInfo=?,productPrice=?,productQuantity=?,categoryID=?,sellerID=?,imageURL=? where productID=?";
		MDC.put("method", "updateProduct");
		MDC.put("productId", String.valueOf(productResponse.getProductID()));
		MDC.put("productName", productResponse.getProductName());
		logger.info("Updating Product Details by ProductID :",productResponse.getProductID());
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection()){
			
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setString(1, productResponse.getProductName());
			stmt.setString(2, productResponse.getProductInfo());
			stmt.setDouble(3, productResponse.getProductPrice());
			stmt.setInt(4, productResponse.getProductQuantity());
			stmt.setLong(5, productResponse.getCategoryID());
			stmt.setLong(6, productResponse.getSellerID());
			stmt.setString(7, productResponse.getImageURL());
			stmt.setLong(8, productResponse.getProductID());
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Product details Updated Successfully");
			return true;
			}
			else {
				logger.error("No such Product Existed ");
			}
			
		} 
		catch(SQLException e) {
			logger.error("SQL Exception accurred while Updating Product Details",e);
	        e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProduct(long product_id) {
		String query="delete from Products where productID=?";
		MDC.put("method", "deleteProduct");
		MDC.put("productId", String.valueOf(product_id));
		logger.info("Deleting Product By Id",product_id);
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setLong(1, product_id);
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Product deleted Successfully");
				return true;
			}else {
				logger.error("No such Producted Existed");
			}
		}
		catch(SQLException e) {
			logger.error("SQL Exception accurred While delete Product");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		String query="select * from Products";
		MDC.put("method", "getAllProducts");
		logger.info("Retrieve all Product Details");
		List<ProductResponse> products=new ArrayList();
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection()){
			Statement stmt=con.createStatement();
			ResultSet result=stmt.executeQuery(query);
			while(result.next()) {
				long product_id=result.getLong("productID");
				String product_name=result.getString("productName");
				String product_info=result.getString("productInfo");
				double product_price=result.getDouble("productPrice");
				int product_quantity=result.getInt("productQuantity");
				long category_id=result.getLong("categoryID");
				long seller_id=result.getLong("sellerID");
				String image_URL=result.getString("imageURL");
				ProductResponse product=new ProductResponse(product_id,product_name,product_info,product_price,
						product_quantity,category_id,seller_id,image_URL);
				products.add(product);
			}
			con.close();
			logger.info("Successfully Retrieve All Products Details : ",products.size());
			return products;
		}
		catch(SQLException e) {
			logger.error("SQL Exception accurred while retrieving all Products details");
	        e.printStackTrace();
		}
		
		return null;
	}
	@Override
	 public List<ProductResponse> getProductsByCategoryId(long categoryId) {
	        String query = "SELECT * FROM Products WHERE CategoryID = ?";
	        List<ProductResponse> products = new ArrayList<>();

	        try (Connection con = ConnectionFactory.getconnectionFactory().getConnection();
	             PreparedStatement stmt = con.prepareStatement(query)) {

	            stmt.setLong(1, categoryId);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
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
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	

	@Override
	public List<ProductResponse> getProductBySellerId(long sellerID) {
		String query="select * from Products where sellerID=?";
		List<ProductResponse> products=new ArrayList();
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, sellerID);
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

	@Override
	public double getPriceByProductId(long productID) {
		String query="select productPrice from Products where productID=?";
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, productID);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getDouble("productPrice");
			}else {
				logger.error("No such Product Exist with Id {}",productID);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
