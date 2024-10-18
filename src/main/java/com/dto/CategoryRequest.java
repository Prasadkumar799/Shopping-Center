package com.dto;

public class CategoryRequest {
	private String categoryName;

	public CategoryRequest(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CategoryRequest [categoryName=" + categoryName + "]";
	}
	
	
}
