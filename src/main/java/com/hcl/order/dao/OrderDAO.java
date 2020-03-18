package com.hcl.order.dao;

import java.util.List;
import java.util.Optional;

import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;

public interface OrderDAO {

	public Optional<Order> saveOrder(Order order, List<OrderItem> orderItems);

	public Optional<Order> updateOrder(Order order);

	public Optional<Order> getOrderById(String orderId);

	public Optional<List<Order>> getAllOrderByUserId(String userId);

}
