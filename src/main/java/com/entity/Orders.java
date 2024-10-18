package com.entity;

import java.sql.Timestamp;

public class Orders {
	
	private long orderID;
	private long user_id;
	private Timestamp orderDate;
	private double totalAmount;
	private String Status;
	private String shippingAddress;
	private String billingAddress;
	
	public Orders(long orderID, long user_id, Timestamp orderDate, double totalAmount, String status,
			String shippingAddress, String billingAddress) {
		super();
		this.orderID = orderID;
		this.user_id = user_id;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.Status = status;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
	}
	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	@Override
	public String toString() {
		return "Orders [orderID=" + orderID + ", user_id=" + user_id + ", orderDate=" + orderDate + ", totalAmount="
				+ totalAmount + ", Status=" + Status + ", shippingAddress=" + shippingAddress + ", billingAddress="
				+ billingAddress + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
