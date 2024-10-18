package com.dao;

import java.util.List;

import com.dto.OrdersRequest;
import com.dto.OrdersResponse;

public interface OrdersDAO {
	OrdersResponse getOrderById(long order_id,long user_id);
	boolean createOrder(OrdersRequest orderRequest);
	boolean updateOrderStatus(long orderID,String status);
	boolean cancelOrder(long order_id,long user_id);
	List<OrdersResponse> getAllOrders(long user_id);
	
}
