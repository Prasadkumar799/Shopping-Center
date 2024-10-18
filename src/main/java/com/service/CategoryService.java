package com.service;

import java.util.List;

import com.dao.CategoryDAOClasss;
import com.dto.CategoryRequest;
import com.dto.CategoryResponse;
import com.dto.ProductResponse;

public class CategoryService {
	
	private final CategoryDAOClasss categoryDAO = new CategoryDAOClasss();
	
	
	public boolean addCategory(CategoryRequest categoryRequest) {
		return categoryDAO.addCategory(categoryRequest);
	}
	public List<CategoryResponse> showCategories(){
		return categoryDAO.showCategories();
	}
	public boolean deleteCategory(long categoryID) {
		return categoryDAO.deleteCategory(categoryID);
	}
	
	public List<ProductResponse> selectCategoryById(long categoryID){
		return categoryDAO.selectCategoryById(categoryID);
	}
	
//	public static void main(String[] args) {
//		CategoryService cs=new CategoryService();
//		List<CategoryResponse> cates=cs.showCategories();
//		for(CategoryResponse cate:cates) {
//			System.out.println(cate);
//		}
//		List<ProductResponse> cates=cs.selectCategoryById(1);
//		for(ProductResponse cate:cates) {
//			System.out.println(cate);
//		}
//	}
}
