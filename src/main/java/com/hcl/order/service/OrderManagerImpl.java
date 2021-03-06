package com.hcl.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.order.dao.OrderDAO;
import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;
import com.hcl.order.exception.OrderNotFoundException;
import com.hcl.order.exception.ProductNotFoundException;

@Service
public class OrderManagerImpl implements OrderManager {

	@Autowired
	OrderDAO orderDAO; 

	@Override
	public Optional<Order> createOrder(Order order, List<OrderItem> orderItems) throws Exception {
		Optional<Order> savedorder = orderDAO.saveOrder(order, orderItems);
		return savedorder;
		
	}

	@Override
	public Optional<Order> updateOrderStatus(String orderId, String orderstatus) {
		Optional<Order> order = orderDAO.getOrderById(orderId);
		if (order.isPresent()) {
			order.get().setOrderStatus(orderstatus);
			orderDAO.updateOrder(order.get());
			return order;
		}else {
			throw new OrderNotFoundException("order not found"); 
		}
		
		
	}

	@Override
	public Optional<List<OrderItem>> getAllIOrderItems(String orderId) {
		Optional<Order> order = orderDAO.getOrderById(orderId);
		if (order.isPresent()) {
			return Optional.of(order.get().getOrderItems());
		}else {
			throw new OrderNotFoundException("order not found"); 
		}
		
		
	}  

	@Override
	public Optional<OrderItem> getDetailOfOrderItem(String orderId, String productId) throws ProductNotFoundException {
		Optional<Order> order = orderDAO.getOrderById(orderId);
		if (order.isPresent()) {
			OrderItem foundOrderItem = null;
			for (OrderItem orderItem : order.get().getOrderItems()) {
				if (orderItem.getProduct_id().equals(productId)) {
					foundOrderItem = orderItem;
				}
			}
			if(foundOrderItem!=null) {
				return Optional.of(foundOrderItem);
			}else {
				throw new ProductNotFoundException("product not found"); 
			}
			
			
		}else {
			throw new OrderNotFoundException("order not found"); 
		}
		
	}

	@Override
	public Optional<List<Order>> getOrderHistoryOfUser(String userId) {
		Optional<List<Order>> orderHistory = orderDAO.getAllOrderByUserId(userId);
		if (orderHistory.isPresent()) {
			return orderHistory;
		}else {
			throw new OrderNotFoundException("order not found"); 
		}
		
		
	}

}
