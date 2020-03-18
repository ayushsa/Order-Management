package com.hcl.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.order.dao.OrderDAOImpl;
import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;
import com.hcl.order.exception.OrderNotFoundException;

@Service
public class OrderManagerImpl implements OrderManager {

	@Autowired
	OrderDAOImpl orderRepository;

	@Override
	public Optional<Order> createOrder(Order order, List<OrderItem> orderItems) throws Exception {
		Optional<Order> savedorder = orderRepository.saveOrder(order, orderItems);
		if (savedorder.isPresent()) {
			return orderRepository.saveOrder(order, orderItems);
		}else {
			throw new Exception(); 
		}
		
	}

	@Override
	public Optional<Order> changeOrderStatus(String orderId, String orderstatus) {
		Optional<Order> order = orderRepository.getOrderById(orderId);
		if (order.isPresent()) {
			order.get().setOrderStatus(orderstatus);
			orderRepository.updateOrder(order.get());
			return order;
		}else {
			throw new OrderNotFoundException("order not found"); 
		}
		
		
	}

	@Override
	public Optional<List<OrderItem>> getAllIOrderItems(String orderId) {
		Optional<Order> order = orderRepository.getOrderById(orderId);
		if (order.isPresent()) {
			return Optional.of(order.get().getOrderItems());
		}else {
			throw new OrderNotFoundException("order not found"); 
		}
		
		
	}

	@Override
	public Optional<OrderItem> getDetailOfOrderItem(String orderId, String productId) {
		Optional<Order> order = orderRepository.getOrderById(orderId);
		if (order.isPresent()) {
			OrderItem foundOrderItem = null;
			for (OrderItem orderItem : order.get().getOrderItems()) {
				if (orderItem.getProduct_id().equals(productId)) {
					foundOrderItem = orderItem;
				}
			}
			return Optional.of(foundOrderItem);
		}else {
			throw new OrderNotFoundException("order not found"); 
		}
		
	}

	@Override
	public Optional<List<Order>> getOrderHistoryOfUser(String userId) {
		Optional<List<Order>> orderHistory = orderRepository.getAllOrderByUserId(userId);
		if (orderHistory.isPresent()) {
			return orderHistory;
		}else {
			throw new OrderNotFoundException("order not found"); 
		}
		
		
	}

}
