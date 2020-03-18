package com.hcl.order.service;

import java.util.List;
import java.util.Optional;

import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;

public interface OrderManager {
	
	public Optional<Order> createOrder(Order order, List<OrderItem> orderItems) throws Exception;
	
	public Optional<Order> changeOrderStatus(String orderId, String status);
	
	public Optional<List<OrderItem>> getAllIOrderItems(String orderId);

	public Optional<OrderItem> getDetailOfOrderItem(String orderId, String productId);
	
	public Optional<List<Order>> getOrderHistoryOfUser(String userId);
}
