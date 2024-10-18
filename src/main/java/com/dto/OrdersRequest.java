package com.dto;

import java.sql.Timestamp;

public class OrdersRequest {
	
	private long user_id;
	
	private double totalAmount;
	private String Status;
	private String shippingAddress;
	private String billingAddress;
	
	public OrdersRequest(long user_id, String status, String shippingAddress, String billingAddress) {
		super();
		this.user_id = user_id;
		Status = status;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
	}

	public OrdersRequest(long user_id,  double totalAmount, String status, String shippingAddress,
			String billingAddress) {
		super();
		this.user_id = user_id;
		this.totalAmount = totalAmount;
		Status = status;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
	}

	public OrdersRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
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
		return "OrdersRequest [user_id=" + user_id  + ", totalAmount=" + totalAmount
				+ ", Status=" + Status + ", shippingAddress=" + shippingAddress + ", billingAddress=" + billingAddress
				+ "]";
	}
	
	
	
}
