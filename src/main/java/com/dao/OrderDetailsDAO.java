package com.dao;

import java.util.List;

import com.dto.OrderDetailsResponse;

public interface OrderDetailsDAO {
	List<OrderDetailsResponse> getOrderdetailsByUserId(long user_id);
	boolean clearOrderdetailsByOrderID(int orderID);
}
