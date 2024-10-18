package com.dao;

import java.util.List;

import com.dto.CartResponse;
import com.dto.ProductResponse;

public interface CartDAO {
	boolean  addToCart(long user_id,long productID,int quantity);
	boolean  deleteFromCart(long user_id,long productID);
	List<ProductResponse> cartItems(long user_id);
	boolean modifyQuantity(long cartID,long productID,int quantity);
	CartResponse getCartItemById(long user_id,long productID);
	boolean clearCart(long user_id);
	boolean moveToFavorites(long user_id, long productID);
}



