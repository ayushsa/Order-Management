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

import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;
import com.hcl.order.responseEntity.Response;
import com.hcl.order.service.OrderManagerImpl;
import com.hcl.order.utilities.RandomIdentifierGenerator;
import com.hcl.order.utilities.Utills;

@RestController
public class OrderController {

	@Autowired
	OrderManagerImpl orderservice;
	@Autowired
	RandomIdentifierGenerator orderNoGenrator;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/order/{userId}")
	public ResponseEntity<?> createOrder(@RequestBody(required = true) Order order,
			@PathVariable("userId") String userId) throws Exception {
		Response response = new Response();

		Order formattedOrder = new Order(Utills.unique(), userId, "Preparing", order.getOrderTotal(),
				order.getPaymentMode(), order.getPaymentCurrency(), order.getShippingAdderess());
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrders(formattedOrder);

		}

		Optional<Order> orderResponse = orderservice.createOrder(formattedOrder, orderItems);
		logger.debug(orderResponse.toString());
		response.setStatus("201");
		response.setMessage("success");
		List<Order> data = new ArrayList<Order>();
		data.add(orderResponse.get());
		response.setData(data);

		return new ResponseEntity<Response>(response, HttpStatus.CREATED);

	}

	@PutMapping("/order/{orderId}/{orderStatus}")
	public ResponseEntity<Response> updateOrder(@PathVariable("orderId") String orderId,
			@PathVariable("orderStatus") String orderStatus) {
		Response response = new Response();
		Optional<Order> order = orderservice.changeOrderStatus(orderId, orderStatus);

		response.setStatus("200");
		response.setMessage("success");
		List<Order> data = new ArrayList<Order>();
		data.add(order.get());
		response.setData(data);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Response> getAllItemsOfOrder(@PathVariable("orderId") String orderId) {
		Response response = new Response();

		Optional<List<OrderItem>> orderItemsList = orderservice.getAllIOrderItems(orderId);
		response.setStatus("200");
		response.setMessage("success");
		response.setData(orderItemsList.get());

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/order/user/{userId}")
	public ResponseEntity<Response> getOrderHistoryOfUser(@PathVariable("userId") String userId) {
		Response response = new Response();

		Optional<List<Order>> ordersList = orderservice.getOrderHistoryOfUser(userId);
		response.setStatus("200");
		response.setMessage("success");
		response.setData(ordersList.get());

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

//	@GetMapping("/test")
//	public String testCart() throws Exception {
//		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR");
//		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
//		listOfOrderItems.add(new OrderItem("123", "Product name1", "Product Description1", order));
//		listOfOrderItems.add(new OrderItem("124", "Product name2", "Product Description2", order));
//		order.setOrderItems(listOfOrderItems);
//
//		orderservice.createOrder(order, listOfOrderItems);
//
//		return "cart controller";
//
//	}
//
//	@GetMapping("/update")
//	public String updateOrder() {
//		orderservice.cancleOrder("1");
//		return "cancle cart controller";
//	}
//
//	@GetMapping("/allorder")
//	public List<OrderItem> getAllItemsOrder() {
//		return orderservice.getAllIOrderItems("1").get();
//
//	}
//
//	@GetMapping("/orderhistory")
//	public List<Order> getOrderHistory() {
//		return orderservice.getOrderHistoryOfUser("u_ajeet").get();
//
//	}
}
