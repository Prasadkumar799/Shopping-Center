package com.service;

import java.util.List;

import com.dao.OrderDetailsDAOClass;
import com.dto.OrderDetailsRequest;
import com.dto.OrderDetailsResponse;

public class OrderDetailsService {
	
	private final OrderDetailsDAOClass orderDetails = new OrderDetailsDAOClass();
	
	public List<OrderDetailsResponse> getOrderdetailsByUserId(long user_id) {
		return orderDetails.getOrderdetailsByUserId(user_id);
	}
	public String addDetails(OrderDetailsRequest request) {
		return orderDetails.addDetails(request);
	}
	public boolean clearOrderdetailsByOrderID(int orderID) {
		return orderDetails.clearOrderdetailsByOrderID(orderID);
	}
	
	public static void main(String[] args) {
		OrderDetailsDAOClass ods=new OrderDetailsDAOClass();
		List<OrderDetailsResponse> od=ods.getOrderdetailsByUserId(4);
		for(OrderDetailsResponse details:od) {
			System.out.println(details);
		}
	}
	
}
