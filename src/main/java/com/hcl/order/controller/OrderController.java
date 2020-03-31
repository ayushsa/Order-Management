package com.hcl.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.order.configuration.AppProperties;
import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;
import com.hcl.order.exception.ProductNotFoundException;
import com.hcl.order.responseEntity.Response;
import com.hcl.order.service.OrderManager;
import com.hcl.order.utilities.Utills;

/**
 * @author ajeet Controller for order service
 */
@RestController
public class OrderController {

	@Autowired
	AppProperties appProperties;
	@Autowired
	OrderManager orderManager;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * method for creating order 
	 * @param order Order object
	 * @param userId id of user 
	 * @return response entity
	 * @throws Exception
	 */
	@PostMapping("/order/{userId}")
	public ResponseEntity<?> createOrder(@RequestBody(required = true) Order order,
			@PathVariable("userId") String userId) throws Exception {
		Response response = new Response();

		/* No HardCoded Status, Read from Properties Files */
		Order formattedOrder = new Order(Utills.unique(), userId, appProperties.getOrderStatusPreparing(),
				order.getOrderTotal(), order.getPaymentMode(), order.getPaymentCurrency(), order.getShippingAdderess());
		List<OrderItem> orderItems = order.getOrderItems();

		for (OrderItem orderItem : orderItems) {
			orderItem.setOrders(formattedOrder);

		}

		Optional<Order> orderResponse = orderManager.createOrder(formattedOrder, orderItems);
		logger.debug(orderResponse.toString());

		/* Read from HttpStatus e.g. HttpStatus.BAD_REQUEST */
		response.setStatus(HttpStatus.CREATED);
		response.setMessage(appProperties.getSuccessStatus());
		List<Order> data = new ArrayList<Order>();
		data.add(orderResponse.get());
		response.setData(data);

		return new ResponseEntity<Response>(response, HttpStatus.CREATED);

	}

	
	
	/**
	 * method for updating the order status
	 * @param orderId 
	 * @param orderStatus
	 * @return
	 */
	@PutMapping("/order/{orderId}/{orderStatus}")
	public ResponseEntity<Response> updateOrder(@PathVariable("orderId") String orderId,
			@PathVariable("orderStatus") String orderStatus) {
		Response response = new Response();

		/* Service function can be renamed to updateOrderStatus */
		Optional<Order> order = orderManager.updateOrderStatus(orderId, orderStatus);

		/* Same Comment as Above */
		response.setStatus(HttpStatus.OK);
		response.setMessage(appProperties.getSuccessStatus());
		List<Order> data = new ArrayList<Order>();
		data.add(order.get());
		response.setData(data);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/order/{orderId}")
	/* Controller function can be renamed to getAllIOrderItems */
	public ResponseEntity<Response> getAllIOrderItems(@PathVariable("orderId") String orderId) {
		Response response = new Response();

		Optional<List<OrderItem>> orderItemsList = orderManager.getAllIOrderItems(orderId);
		response.setStatus(HttpStatus.OK);
		response.setMessage(appProperties.getSuccessStatus());
		response.setData(orderItemsList.get());

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/order/user/{userId}")
	public ResponseEntity<Response> getOrderHistoryOfUser(@PathVariable("userId") String userId) {
		Response response = new Response();

		Optional<List<Order>> ordersList = orderManager.getOrderHistoryOfUser(userId);
		response.setStatus(HttpStatus.OK);
		response.setMessage(appProperties.getSuccessStatus());
		response.setData(ordersList.get());

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/order/{orderId}/{productId}")
	public ResponseEntity<Response> getItemDetailOfOrder(@PathVariable("orderId") String orderId,
			@PathVariable("productId") String productId) throws ProductNotFoundException {
		Response response = new Response();

		Optional<OrderItem> orderItem = orderManager.getDetailOfOrderItem(orderId, productId);
		response.setStatus(HttpStatus.OK);
		response.setMessage(appProperties.getSuccessStatus());
		List<OrderItem> data = new ArrayList<OrderItem>();
		data.add(orderItem.get());
		response.setData(data);

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

}
