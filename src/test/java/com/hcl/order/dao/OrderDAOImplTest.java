package com.hcl.order.dao;

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
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;
import com.hcl.order.repository.OrderItemsRepository;
import com.hcl.order.repository.OrderRepository;

@SpringBootTest
class OrderDAOImplTest {

	@InjectMocks
	OrderDAOImpl mockedrepository;

	@Mock
	OrderRepository orderRepository;

	@Mock
	OrderItemsRepository orderItemRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	void test_save_order() {
		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		listOfOrderItems.add(new OrderItem("123", "Product name1", "Product Description1", "280.00","3",order));
		listOfOrderItems.add(new OrderItem("124", "Product name2", "Product Description2","280.00","3", order));
		order.setOrderItems(listOfOrderItems);

		
		when(orderRepository.save(order)).thenReturn(order);
		Optional<Order> createdorder = mockedrepository.saveOrder(order, listOfOrderItems);
		assertEquals(createdorder.get().getOrderRefrenceNo(), "ORD1");
	}
	
	@Test
	void test_update_order() {
		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		listOfOrderItems.add(new OrderItem("123", "Product name1", "Product Description1","280.00","3", order));
		listOfOrderItems.add(new OrderItem("124", "Product name2", "Product Description2", "280.00","3",order));
		order.setOrderItems(listOfOrderItems);

		
		when(orderRepository.save(order)).thenReturn(order);
		Optional<Order> createdorder = mockedrepository.updateOrder(order);
		assertEquals(createdorder.get().getOrderRefrenceNo(), "ORD1");
	}
	
	
	@Test
	void test_get_order_by_orderId() {
		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		listOfOrderItems.add(new OrderItem("123", "Product name1", "Product Description1", "280.00","3",order));
		listOfOrderItems.add(new OrderItem("124", "Product name2", "Product Description2", "280.00","3",order));
		order.setOrderItems(listOfOrderItems);

		when(orderRepository.findById(Long.parseLong("1"))).thenReturn(Optional.of(order));
		
		Optional<Order> createdorder = mockedrepository.getOrderById("1");
		assertEquals(createdorder.get().getOrderRefrenceNo(), "ORD1");
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
		
		
		when(orderRepository.findAllByUserId("u_ajeet")).thenReturn(Optional.of(listOfOrders));
		Optional<List<Order>> orderHistory = mockedrepository.getAllOrderByUserId("u_ajeet");
		assertEquals(2, orderHistory.get().size());
	}


}
