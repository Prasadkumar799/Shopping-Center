package com.service;

import java.util.List;

import com.dao.FavoritesDAOClass;
import com.dto.FavoritesRequest;
import com.dto.ProductResponse;

public class FavoritesService {
	
	private final FavoritesDAOClass favoritesDAO = new FavoritesDAOClass();
	
	public ProductResponse getFavoriteById(long user_id,long productID){
		return favoritesDAO.getFavoriteById(user_id,productID);
	}
	public boolean addToFavorites(long user_id,long productID) {
		return favoritesDAO.addToFavorites(user_id,productID);
	}
	public boolean removeFromFavorite(long user_id,long productID) {
		return favoritesDAO.removeFromFavorite(user_id, productID);
	}
	
	public boolean moveToCart(long user_id,long productID) {
		return favoritesDAO.moveToCart(user_id, productID);
	}
	
	public List<ProductResponse> getAllFavorites(long user_id){
		return favoritesDAO.getAllFavorites(user_id);
	}

}
