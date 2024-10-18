package com.dao;

import java.util.List;

import com.dto.ProductRequest;
import com.dto.ProductResponse;

public interface ProductsDAO {
	public ProductResponse getProductById(long productID);
	public boolean createProduct(ProductRequest productRequest);
	public boolean updateProduct(ProductResponse productResponse);
	public boolean deleteProduct(long productID);
	public List<ProductResponse> getAllProducts();
	public List<ProductResponse> getProductsByCategoryId(long categoryID);
	public List<ProductResponse> getProductBySellerId(long sellerID);
	double getPriceByProductId(long productID);
}
