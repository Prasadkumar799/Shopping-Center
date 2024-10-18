package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dto.ProductResponse;
import com.dto.SellerRequest;
import com.dto.SellerResponse;
import com.utils.ConnectionFactory;

public class SellerDAOClass implements SellerDAO{

	@Override
	public SellerResponse getsellerById(long sellerID) {
		String query="select * from Sellers where sellerID=?";
		SellerResponse seller=null;
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, sellerID);
			ResultSet result=stmt.executeQuery();
			if(result.next()) {
				seller=new SellerResponse(result.getLong("sellerID"),result.getString("sellerName"),result.getString("sellerEmail"),
						result.getString("sellerPassword"));
			}
			return seller;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addSeller(SellerRequest addseller) {
		String query="insert into Sellers (sellerName,sellerEmail,sellerPassword) values(?,?,?)";
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setString(1, addseller.getSellerName());
			stmt.setString(2, addseller.getSellerEmail());
			stmt.setString(3, addseller.getSellerPassword());
			int result=stmt.executeUpdate();
			if(result>0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<SellerResponse> getAllSeller() {
		String query="select * from Sellers";
		List<SellerResponse> sellers=new ArrayList();
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				Statement stmt=con.createStatement()){
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				SellerResponse seller=new SellerResponse(rs.getLong("sellerID"),rs.getString("sellerName"),
						rs.getString("sellerEmail"),rs.getString("sellerPassword"));
				sellers.add(seller);
			}
			return sellers;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String deleteSeller(long sellerID) {
		String query="delete from Sellers where sellerID=?";
		
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, sellerID);
			int result=stmt.executeUpdate();
			if(result>0) {
				return "seller deleted";
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SellerResponse getSeller(String email, String password) {
		String query="select * from Sellers where sellerEmail=? and sellerPassword=?";
		SellerResponse seller=null;
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				seller=new SellerResponse(rs.getLong("sellerID"),rs.getString("sellerName"),
						rs.getString("sellerEmail"),rs.getString("sellerPassword"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return seller;
	}

//	@Override
//	public List<ProductResponse> getProductBySellerId(long sellerID) {
//		String query="Select * from Products where sellerID=?";
//		List<ProductResponse> products=new ArrayList();
//		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
//				PreparedStatement stmt=con.prepareStatement(query)){
//			stmt.setLong(1, sellerID);
//			ResultSet result=stmt.executeQuery();
//			while(result.next()) {
//				long product_id=result.getLong("productID");
//				String product_name=result.getString("productName");
//				String product_info=result.getString("productInfo");
//				double product_price=result.getDouble("productPrice");
//				int product_quantity=result.getInt("productQuantity");
//				long category_id=result.getLong("categoryID");
//				long seller_id=result.getLong("sellerID");
//				String image_URL=result.getString("imageURL");
//				ProductResponse product=new ProductResponse(product_id,product_name,product_info,product_price,
//						product_quantity,category_id,seller_id,image_URL);
//				products.add(product);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}


}
