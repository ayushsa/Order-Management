package com.hcl.order.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;
import com.hcl.order.repository.OrderItemsRepository;
import com.hcl.order.repository.OrderRepository;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemsRepository orderItemRepository;

	@Override
	public Optional<Order> saveOrder(Order order, List<OrderItem> orderItems) {
		orderRepository.save(order);
		orderItemRepository.saveAll(orderItems);
		return Optional.of(order);
	}

	@Override
	public Optional<Order> updateOrder(Order order) {
		orderRepository.save(order);
		return Optional.of(order);
	}

	@Override
	public Optional<Order> getOrderById(String orderId) {
		Optional<Order> order = orderRepository.findById(Long.parseLong(orderId));
		return order;
	}

	@Override
	public Optional<List<Order>> getAllOrderByUserId(String userId) {
		Optional<List<Order>> listOfAllOrder = orderRepository.findAllByUserId(userId);
		return listOfAllOrder;
	}

}
