package com.hcl.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.order.dao.OrderDAOImpl;
import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;

@SpringBootTest
class OrderManagerImplTest {

	@Autowired
	OrderManagerImpl orderService;

	@InjectMocks
	OrderManagerImpl mockedOrderService;

	@Mock
	OrderDAOImpl orderRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	void test_create_order() throws Exception {
		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		listOfOrderItems.add(new OrderItem("123", "Product name1", "Product Description1","280.00","3" ,order));
		listOfOrderItems.add(new OrderItem("124", "Product name2", "Product Description2","280.00","3", order));
		order.setOrderItems(listOfOrderItems);

		Optional<Order> createdorder = orderService.createOrder(order, listOfOrderItems);
		assertEquals(createdorder.get().getOrderRefrenceNo(), "ORD1");
	}

	@Test
	void test_cancle_order() {
		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		when(orderRepository.getOrderById("1")).thenReturn(Optional.of(order));
		Optional<Order> updatedorder = mockedOrderService.changeOrderStatus("1","delivered");
		assertEquals("delivered", updatedorder.get().getOrderStatus());
	}

	@Test
	void test_get_all_order_items() {
		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		listOfOrderItems.add(new OrderItem("123", "Product name1", "Product Description1","280.00","3", order));
		listOfOrderItems.add(new OrderItem("124", "Product name2", "Product Description2","280.00","3", order));
		order.setOrderItems(listOfOrderItems);

		when(orderRepository.getOrderById("1")).thenReturn(Optional.of(order));
		Optional<List<OrderItem>> orderItems = mockedOrderService.getAllIOrderItems("1");
		assertEquals(2, orderItems.get().size());
	}
	
	@Test
	void test_get_Detail_Of_OrderItem() {
		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		listOfOrderItems.add(new OrderItem("123", "Product name1", "Product Description1","280.00","3", order));
		listOfOrderItems.add(new OrderItem("124", "Product name2", "Product Description2","280.00","3", order));
		order.setOrderItems(listOfOrderItems);
		
		when(orderRepository.getOrderById("1")).thenReturn(Optional.of(order));
		Optional<OrderItem> orderItem = mockedOrderService.getDetailOfOrderItem("1","123");
		assertEquals("Product name1", orderItem.get().getProductName());
	}
	
	
	@Test
	void test_get_Order_history() {
		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		listOfOrderItems.add(new OrderItem("123", "Product name1", "Product Description1","280.00","3", order));
		listOfOrderItems.add(new OrderItem("124", "Product name2", "Product Description2","280.00","3", order));
		order.setOrderItems(listOfOrderItems);
		
		Order order2 = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		List<OrderItem> listOfOrderItems2 = new ArrayList<OrderItem>();
		listOfOrderItems2.add(new OrderItem("123", "Product name1", "Product Description1","280.00","3", order));
		listOfOrderItems2.add(new OrderItem("124", "Product name2", "Product Description2","280.00","3", order));
		order2.setOrderItems(listOfOrderItems2);
		
		List<Order> listOfOrders = new ArrayList<>();
		listOfOrders.add(order);
		listOfOrders.add(order2);
		
		
		when(orderRepository.getAllOrderByUserId("u_ajeet")).thenReturn(Optional.of(listOfOrders));
		Optional<List<Order>> orderHistory = mockedOrderService.getOrderHistoryOfUser("u_ajeet");
		assertEquals(2, orderHistory.get().size());
	}
	
	@Test
	void test_get_Order_history_when_order_history_not_present() {
		/*
		 * List<Order> orderHistoryEmpty;
		 * 
		 * doThrow(new OrderNotFoundException("Add operation not implemented")).
		 * when(orderRepository.getAllOrderByUserId("u_ajeet")).thenReturn(Optional.of(
		 * new ArrayList<Order>()));;
		 */
		
		//when(orderRepository.getAllOrderByUserId("u_ajeet")).thenReturn(new Optional<List<Order>>)));     
		//Optional<List<Order>> orderHistory = mockedOrderService.getOrderHistoryOfUser("u_ajeet");
		//assertEquals(2, orderHistory.get().size());
	}

}
