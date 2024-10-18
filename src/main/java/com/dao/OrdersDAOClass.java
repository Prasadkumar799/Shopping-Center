package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;


import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Logger;

import com.dto.OrdersRequest;
import com.dto.OrdersResponse;
import com.dto.ProductResponse;
import com.utils.ConnectionFactory;

public class OrdersDAOClass implements OrdersDAO{
	private final static Logger logger=LoggerFactory.getLogger(OrdersDAOClass.class);
	CartDAOClass cartDAO=new CartDAOClass();
	@Override
	public OrdersResponse getOrderById(long order_id,long user_id) {
		String query ="select * from Orders where orderID=? and user_id=?";
		MDC.put("method", "getOrderById");
		logger.info("Retrieving Order details");
		MDC.put("orderId", String.valueOf(order_id));
		OrdersResponse order=null; 
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, order_id);
			stmt.setLong(2, user_id);
			ResultSet rs=stmt.executeQuery();
			if(!rs.next()) {
				logger.debug("No orders details present with this Order ID",order_id);
				return null;
			}
			if(rs.next()) {
				 order=new OrdersResponse(rs.getLong("orderID"),rs.getLong("user_id"),rs.getTimestamp("orderDate"),
						rs.getDouble("totalAmount"),rs.getString("Status"),rs.getString("shippingAddress"),rs.getString("billingAddress"));
			}
			logger.info("Order details Retrieve Successfully with Order Id",order_id);
			return order;
			
		}catch(SQLException e) {
			logger.error("SQL Exception while getOrderByID {} ",e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createOrder(OrdersRequest orderRequest) {
	    String query = "INSERT INTO Orders (user_id, totalamount, Status, shippingAddress, billingAddress) VALUES (?, ?, ?, ?, ?)";
	    MDC.put("method", "createOrder");
	    logger.info("Adding Order details");
	    
	    List<ProductResponse> allCartItems;
	    try {
	        allCartItems = cartDAO.cartItems(orderRequest.getUser_id());
	        
	        if (allCartItems == null || allCartItems.isEmpty()) {
	            logger.warn("Cart is empty. No order will be created.");
	            return false;
	        }
	        
	        try (Connection con = ConnectionFactory.getconnectionFactory().getConnection();
	             PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

	            con.setAutoCommit(false); 

	            stmt.setLong(1, orderRequest.getUser_id());
	            stmt.setDouble(2, cartDAO.totalAmount(orderRequest.getUser_id()));
	            stmt.setString(3, orderRequest.getStatus());
	            stmt.setString(4, orderRequest.getShippingAddress());
	            stmt.setString(5, orderRequest.getBillingAddress());

	            logger.info("Executing query: " + stmt.toString());

	            int result = stmt.executeUpdate();
	            if (result > 0) {
	                logger.info("Successfully Inserted Order Details");
	                
	                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                    if (generatedKeys.next()) {
	                        long orderID = generatedKeys.getLong(1);
	                        String odquery = "INSERT INTO OrderDetails (orderID, productID, quantity, price, user_id) VALUES (?, ?, ?, ?, ?)";

	                        try (PreparedStatement ps = con.prepareStatement(odquery)) {
	                            for (ProductResponse cart : allCartItems) {
	                                ps.setLong(1, orderID);
	                                ps.setLong(2, cart.getProductID());
	                                ps.setInt(3, cart.getProductQuantity());
	                                ps.setDouble(4, cart.getProductPrice());
	                                ps.setLong(5, orderRequest.getUser_id());
	                                ps.addBatch();
	                            }
	                            ps.executeBatch();
	                        }

	                        con.commit(); 
	                        cartDAO.clearCart(orderRequest.getUser_id());
	                        return true;
	                    }
	                }
	            } else {
	                logger.warn("No rows were inserted.");
	                con.rollback(); 
	            }
	        } catch (SQLException e) {
	            logger.error("SQL Exception while Adding Order: " + e.getMessage());
	            e.printStackTrace();
	        }
	    } catch (Exception e) {
	        logger.error("Exception while processing order: " + e.getMessage());
	        e.printStackTrace();
	    }

	    logger.info("Failed to create order.");
	    return false; 
	}


	@Override
	public boolean updateOrderStatus(long orderID, String status) {
	    String query = "UPDATE Orders SET Status = ? WHERE orderID = ?";
	    MDC.put("method", "updateOrder");
	    logger.info("Updating Order Details for orderID: {}", orderID);

	    try (Connection con = ConnectionFactory.getconnectionFactory().getConnection();
	         PreparedStatement stmt = con.prepareStatement(query)) {

	        stmt.setString(1, status);
	        stmt.setLong(2, orderID);

	        int result = stmt.executeUpdate();
	        if (result > 0) {
	            logger.info("Successfully Updated Order with orderID: {}", orderID);
	            return true;
	        } else {
	            logger.warn("No order found with orderID: {}", orderID);
	            return false;
	        }
	    } catch (SQLException e) {
	        logger.error("SQL Exception while Updating Order Details for orderID: {}", orderID, e);
	        e.printStackTrace();
	    }
	    return false;
	}


	@Override
	public boolean cancelOrder(long order_id,long user_id) {
		String query="delete from Orders where orderID=? and user_id=?";
		MDC.put("method", "");
		logger.info("");
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1,order_id);
			stmt.setLong(2, user_id);
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
	public List<OrdersResponse> getAllOrders(long user_id) {
		String query="select * from Orders where user_id=?";
		MDC.put("method", "getAllOrders");
		logger.info("");
		List<OrdersResponse> orders=new ArrayList();
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, user_id);
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()) {
				OrdersResponse order=new OrdersResponse(rs.getLong("orderID"),rs.getLong("user_id"),rs.getTimestamp("orderDate"),
						rs.getDouble("totalAmount"),rs.getString("Status"),rs.getString("shippingAddress"),rs.getString("billingAddress"));
				orders.add(order);
			}
			return orders;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	

}
