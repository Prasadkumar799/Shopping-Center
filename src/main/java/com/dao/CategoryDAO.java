package com.dao;

import java.util.List;

import com.dto.CategoryRequest;
import com.dto.CategoryResponse;
import com.dto.ProductResponse;

public interface CategoryDAO {
	public boolean addCategory(CategoryRequest categoryRequest);
	public List<CategoryResponse> showCategories();
	public boolean deleteCategory(long categoryID);
	List<ProductResponse> selectCategoryById(long categoryID);
}
