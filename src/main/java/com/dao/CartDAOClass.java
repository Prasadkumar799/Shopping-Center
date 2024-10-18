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

import com.dto.CartResponse;
import com.dto.ProductResponse;
import com.utils.ConnectionFactory;

public class CartDAOClass implements CartDAO{
	private static final Logger logger=LoggerFactory.getLogger(CartDAOClass.class);
	
	ProductsDAOClass productDAO=new ProductsDAOClass();
	FavoritesDAOClass favDAO=new FavoritesDAOClass();
	@Override
	public boolean addToCart(long user_id, long productID, int quantity) {
	    MDC.put("method", "addToCart");
	    logger.info("Attempting to add productID: {} to cart for user_id: {}", productID, user_id);
	    
	    String query = "INSERT INTO Cart(productID, user_id, quantity, unitPrice, totalPrice) VALUES (?, ?, ?, ?, ?)";
	    if(getCartItemById(user_id,productID)!=null) {
	    	return true;
	    }else {
	    try (Connection con = ConnectionFactory.getconnectionFactory().getConnection();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	    	System.out.println(quantity);
	        double unitPrice = productDAO.getPriceByProductId(productID);
	        System.out.println(unitPrice);
	        double totalAmount = quantity * unitPrice;
	        System.out.println(totalAmount);

	        stmt.setLong(1, productID);
	        stmt.setLong(2, user_id);
	        stmt.setInt(3, quantity);
	        stmt.setDouble(4, unitPrice);
	        stmt.setDouble(5, totalAmount); 
	        int result = stmt.executeUpdate();
	        if (result > 0) {
	            logger.info("Successfully added productID: {} to cart for user_id: {}", productID, user_id);
	            return true;
	        } else {
	            logger.error("Failed to add productID: {} to cart for user_id: {}", productID, user_id);
	            return false;
	        }
	    } catch (SQLException e) {
	        logger.error("SQL Exception occurred while adding productID: {} to cart for user_id: {}", productID, user_id, e);
	        return false;
	    }
	    }
	}


	@Override
	public boolean deleteFromCart(long user_id, long productID) {
		String query="delete from Cart where user_id=? and productID=?";
		MDC.put("method", "deleteFromCart");
		MDC.put("userId", String.valueOf(user_id));
		MDC.put("productId", String.valueOf(productID));
		logger.info("Remove Product from Cart");
		try (Connection con =ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, user_id);
			stmt.setLong(2, productID);
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Product Removed from Cart");
				return true;
			}
			else {
				logger.error("Enter proper details to remove from cart");
				return false;
			}
		}catch(SQLException e) {
			logger.error("SQL Exception accured while removing from cart");
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean modifyQuantity(long user_id,long productID, int quantity) {
		if(quantity==0) {
			return deleteFromCart(user_id,productID);
		}
		MDC.put("method", "modifyQuantity");
		MDC.put("userId", String.valueOf(user_id));
		logger.info("Modify Quantity of Product on Cart");
		
		double unitPrice=productDAO.getPriceByProductId(productID);
		double totalPrice=quantity*unitPrice;
		
		String query="update Cart set quantity=?,unitPrice=?,totalPrice=? where user_id=? and productID=?";
		
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setInt(1, quantity);
			stmt.setDouble(2,unitPrice);
			stmt.setDouble(3, totalPrice);
			stmt.setLong(4, user_id);
			stmt.setLong(5, productID);
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("Modified Quantity to : ",quantity);
				return true;
			}
			else {
				logger.error("Quantity not Modified ");
				return false;
			}
		}catch(SQLException e) {
			logger.info("SQL Exception accurred while Quantity Modifying");
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<ProductResponse> cartItems(long user_id) {
		String query="select Products.productID,Products.productName,Products.productInfo,Products.productPrice,Products.imageURL,Cart.quantity from Products join Cart on Cart.productID=Products.productID where Cart.user_id=?";
		List<ProductResponse> cartItems=new ArrayList();
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, user_id);
			ResultSet result=stmt.executeQuery();
			while(result.next()) {
				long product_id=result.getLong("productID");
				String product_name=result.getString("productName");
				String product_info=result.getString("productInfo");
				double product_price=result.getDouble("productPrice");
				int quantity=result.getInt("quantity");

				String image_URL=result.getString("imageURL");
				ProductResponse product=new ProductResponse(product_id,product_name,product_info,product_price,
						quantity,image_URL);
				cartItems.add(product);
			
			}
			return cartItems;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CartResponse getCartItemById(long user_id, long productID) {
		String query="select * from Cart where user_id=? and productID=?";
		try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
				PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setLong(1, user_id);
			stmt.setLong(2, productID);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				long cartID=rs.getLong("cartID");
				long userID=rs.getLong("user_id");
				long productId=rs.getLong("productID");
				int quantity=rs.getInt("quantity");
				double unitPrice=rs.getDouble("unitPrice");
				double totalAmount=rs.getDouble("totalAmount");
				
				return new CartResponse(cartID,userID,productId,quantity,unitPrice,totalAmount);
			}
			else {
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean clearCart(long user_id) {
	    String query = "DELETE FROM Cart WHERE user_id=?";
	    try (Connection con = ConnectionFactory.getconnectionFactory().getConnection();
	         PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setLong(1, user_id);
	        int result = stmt.executeUpdate();
	        return result > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	
	public double totalAmount(long userId){
//		String query="select cartID,totalPrice from Cart where user_id=?";
//		Map<Long, Double> cartMap = new HashMap<>();
//        double totalAmount = 0.0;
//        try(Connection con=ConnectionFactory.getconnectionFactory().getConnection();
//				PreparedStatement stmt=con.prepareStatement(query)){
//        	stmt.setLong(1, userId);
//        	ResultSet rs=stmt.executeQuery();
//        	while(rs.next()) {
//        		cartMap.put(rs.getLong("cartID"),rs.getDouble("totalAmount"));
//        	}
//        	for(double amount:cartMap.values()) {
//        		totalAmount+=amount;
//        	}
//        	return totalAmount;
//        } catch (Exception e) {
//             e.getMessage();
//        }
//        return 0;
		try {
			List<ProductResponse> items=cartItems(userId);
			return items.stream()
            .mapToDouble(ProductResponse::getProductPrice)  
            .sum();
        } catch (Exception e) {
            System.out.println("No products In cart with user Id"+userId); 
        }
		return 0;
    }

	@Override
	public boolean moveToFavorites(long user_id, long productID) {
		MDC.put("method", "moveToFavorites");
		MDC.put("userId", String.valueOf(user_id));
		MDC.put("productId", String.valueOf(productID));
		logger.info("Item Move from Cart to Favorites {}:",productID);
		if(favDAO.addToFavorites(user_id, productID)) {
			logger.debug("Item Moved to Favorites",productID);
			return deleteFromCart(user_id,productID);
		}
		return false;
	}
}
