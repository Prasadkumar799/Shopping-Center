package com.entity;

public class Cart {
	private long cartID ;
	private long productID;
	private long user_id;
	private int quantity;
	private double unitPrice;
	private double totalPrice;
	public Cart(long cartID, long productID, long user_id, int quantity,double unitPrice,double totalPrice) {
		super();
		this.cartID = cartID;
		this.productID = productID;
		this.user_id = user_id;
		this.quantity = quantity;
		this.unitPrice=unitPrice;
		this.totalPrice=totalPrice;
	}
	public long getCartID() {
		return cartID;
	}
	public void setCartID(long cartID) {
		this.cartID = cartID;
	}
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + ", productID=" + productID + ", user_id=" + user_id + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + "]";
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
