package com.entity;

public class Categories {
	private long categoryID;
	private String categoryName;
	
	public Categories(long categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Categories [categoryID=" + categoryID + ", categoryName=" + categoryName + "]";
	}
	
	
	
}
