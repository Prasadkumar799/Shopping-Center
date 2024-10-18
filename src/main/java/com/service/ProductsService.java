package com.service;

import java.util.List;

import com.dao.ProductsDAOClass;
import com.dto.ProductRequest;
import com.dto.ProductResponse;

public class ProductsService {
	private final ProductsDAOClass productDAO = new ProductsDAOClass();
	
	public ProductResponse getProductById(long product_id) {
		return productDAO.getProductById(product_id);
	}
	public boolean createProduct(ProductRequest productRequest) {
		return productDAO.createProduct(productRequest);
	}
	public boolean updateProduct(ProductResponse productResponse) {
		return productDAO.updateProduct(productResponse);
	}
	public boolean deleteProduct(long product_id) {
		return productDAO.deleteProduct(product_id);
	}
	public List<ProductResponse> getAllProducts(){
		return productDAO.getAllProducts();
	}
	public List<ProductResponse> getProductsByCategoryId(long categoryId) {
        return productDAO.getProductsByCategoryId(categoryId);
    }
	public List<ProductResponse> getProductBySellerId(long sellerID){
		return productDAO.getProductBySellerId(sellerID);
	}
	public 	double getPriceByProductId(long productID) {
		return productDAO.getPriceByProductId(productID);
	}


}
