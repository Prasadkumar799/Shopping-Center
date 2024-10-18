package com.service;

import java.util.List;

import com.dao.OrdersDAOClass;
import com.dto.OrdersRequest;
import com.dto.OrdersResponse;

public class OrdersService {
	private final OrdersDAOClass orderDAO = new OrdersDAOClass();
	
	public OrdersResponse getOrderById(long order_id,long user_id) {
		return orderDAO.getOrderById(order_id,user_id);
	}
	public boolean createOrder(OrdersRequest orderRequest) {
		return orderDAO.createOrder(orderRequest);
	}
	 public boolean updateOrderStatus(long orderID ,String status) {
		return orderDAO.updateOrderStatus(orderID, status);
	}
	public boolean cancelOrder(long order_id,long user_id) {
		return orderDAO.cancelOrder(order_id,user_id);
	}
	public List<OrdersResponse> getAllOrders(long user_id){
		return orderDAO.getAllOrders(user_id);
	}
//	public static void main(String[] args) {
//		OrdersService os=new OrdersService();
//		CartDAOClass cartDAO=new CartDAOClass();
//
//	System.out.println(os.createOrder(new OrdersRequest(4,"pending","cvbjh","fghjhg")));
//	}
}
