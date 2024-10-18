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

import com.dto.OrderDetailsRequest;
import com.dto.OrderDetailsResponse;
import com.utils.ConnectionFactory;


public class OrderDetailsDAOClass implements OrderDetailsDAO{
	private static final Logger logger=LoggerFactory.getLogger(OrderDetailsDAOClass.class);
	
	@Override
	public List<OrderDetailsResponse> getOrderdetailsByUserId(long user_id) {
		String query="select * from OrderDetails where user_id=?";
		MDC.put("method", "getOrderDetailsByUserId");
		MDC.put("UserId", String.valueOf(user_id));
		logger.info("Retrieving Order Details");
		List<OrderDetailsResponse> details=new ArrayList();
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, user_id);
			ResultSet rs=stmt.executeQuery();
			if(!rs.next()) {
				logger.debug("No Order Placed");
				return null;
			}
			do {
	            OrderDetailsResponse detail = new OrderDetailsResponse(
	                rs.getLong("orderDetailID"),
	                rs.getLong("orderID"),
	                rs.getLong("productID"),
	                rs.getInt("quantity"),
	                rs.getDouble("price"),
	                rs.getLong("user_id")
	            );
	            details.add(detail);
	        } while (rs.next());
			logger.info("Order Details of user",user_id);
			
		}catch(SQLException e) {
			logger.info("SQL Exception accured while Retrieving Order Details");
			e.printStackTrace();
		}
		return details;
	}

	
	public String addDetails(OrderDetailsRequest request) {
		String query="insert into OrderDetails (orderID,productID,quantity,unitPrice,user_id) values(?,?,?,?,?)";
		MDC.put("method", "addDetails");
		MDC.put("orderId", String.valueOf(request.getOrderID()));
		logger.info("Add Order details");
		
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, request.getOrderID());
			stmt.setLong(2, request.getProductID());
			stmt.setInt(3, request.getQuantity());
			stmt.setDouble(4, request.getUnitPrice());
			stmt.setLong(5, request.getUser_id());
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Successfully Added Order Details");
				return "Successfully Inserted";
			}else {
				logger.debug("Invalid Order details to add ");
				return "Not inserted";
			}
			
		}catch(SQLException e) {
			logger.info("SQL Exception accured While inserting Oreder Details");
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean clearOrderdetailsByOrderID(int orderID) {
		String query="delete from OrderDetails where orderID=?";
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, orderID);
			int result=stmt.executeUpdate();
			return result>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	

	

}
