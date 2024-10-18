package com.dto;

public class OrderDetailsRequest {
	
	private long orderID;
	private long productID;
	private int quantity;
	private double unitPrice;
	private long user_id;
	public OrderDetailsRequest(long orderID, long productID, int quantity, double unitPrice, long user_id) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.user_id = user_id;
	}
	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
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
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "OrderDetailsRequest [orderID=" + orderID + ", productID=" + productID + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", user_id=" + user_id + "]";
	}
	
	
	
	}