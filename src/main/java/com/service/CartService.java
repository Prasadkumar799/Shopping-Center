package com.service;

import java.util.List;

import com.dao.CartDAOClass;
import com.dto.CartRequest;
import com.dto.CartResponse;
import com.dto.ProductResponse;

public class CartService {
	private final CartDAOClass cartDAO = new CartDAOClass();
	
	public boolean  addToCart(long user_id,long productID,int quantity) {
		return cartDAO.addToCart(user_id, productID, quantity);
	}
	public boolean  deleteFromCart(long user_id,long productID) {
		return cartDAO.deleteFromCart(user_id, productID);
		
	}
	public boolean modifyQuantity(long user_id,long productID, int quantity) {
		return cartDAO.modifyQuantity(user_id,productID, quantity);
	}
	public  List<ProductResponse> cartItems(long user_id) {
		return cartDAO.cartItems(user_id);
	}
	
	public CartResponse getCartItemById(long user_id,long productID) {
		return cartDAO.getCartItemById(user_id, productID);
	}
	public boolean clearCart(long user_id) {
		return cartDAO.clearCart(user_id);
	}
	public double totalAmount(long userId) {
		return cartDAO.totalAmount(userId);
	}
	
//	public static void main(String[] args) {
//		CartService cs=new CartService();
//		System.out.println(cs.deleteFromCart(4, 23));
//	List<ProductResponse> items=cs.cartItems(4);
//	for(ProductResponse item:items) {
//		System.out.println(item);
//	}
//	}
		
	
}
