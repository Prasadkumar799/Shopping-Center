package com.dao;

import java.util.List;

import com.dto.ProductResponse;

public interface FavoritesDAO {
	ProductResponse getFavoriteById(long user_id,long productID);
	boolean addToFavorites(long user_id,long productID);
	boolean removeFromFavorite(long user_id,long productID);
	boolean moveToCart(long user_id,long productID);
	List<ProductResponse> getAllFavorites(long user_id);
}
