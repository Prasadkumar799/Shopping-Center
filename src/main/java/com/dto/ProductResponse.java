package com.dto;

public class ProductResponse {
	private long productID;
	private String productName;
	private String productInfo;
	private double productPrice;
	private int productQuantity;
	private long categoryID;
	private long sellerID;
	private String imageURL;
	
	public ProductResponse(long productID, String productName, String productInfo, double productPrice,
			int productQuantity, long categoryID, long sellerID,String imageURL) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productInfo = productInfo;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.categoryID = categoryID;
		this.sellerID = sellerID;
		this.imageURL=imageURL;
	}
	
	public ProductResponse(long productID,String productName,String productInfo,double productPrice,
			int quantity,String imageURL) {
		this.productID = productID;
		this.productName = productName;
		this.productInfo = productInfo;
		this.productPrice = productPrice;
		this.productQuantity = quantity;
		this.imageURL=imageURL;
	}
	public ProductResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public long getSellerID() {
		return sellerID;
	}

	public void setSellerID(long sellerID) {
		this.sellerID = sellerID;
	}
	

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "ProductResponse [productID=" + productID + ", productName=" + productName + ", productInfo="
				+ productInfo + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity
				+ ", categoryID=" + categoryID + ", sellerID=" + sellerID + ", imageURL=" + imageURL + "]";
	}

	
	
	
	
}
