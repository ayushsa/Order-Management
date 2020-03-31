package com.hcl.order.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;


//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class OrderControllerTest {
	private MockMvc mockmvc;

	@Autowired
	private WebApplicationContext context;
	ObjectMapper objectmapper = new ObjectMapper();

	@BeforeEach
	private void setUp() {
		mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
 
	@Test
	void testCreateOrder() throws Exception {
		Order order = new Order("ORD1", "u_ajeet", "Preparing", "2345", "CC", "INR","Shipping Adderess");
		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		listOfOrderItems.add(new OrderItem("123", "Product name1", "Product Description1","280.00","3", order));
		listOfOrderItems.add(new OrderItem("124", "Product name2", "Product Description2", "280.00","3",order));
		order.setOrderItems(listOfOrderItems);

		String jsonRequest = objectmapper.writeValueAsString(order);
		
		mockmvc.perform(post("/order/u_ajeet").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON))
				/*
				 * .andExpect(jsonPath("$.status").exists())
				 * .andExpect(jsonPath("$.message").exists())
				 * .andExpect(jsonPath("$.status").value("201"))
				 * .andExpect(jsonPath("$.message").value("success"))
				 */
				.andExpect(status().isCreated())
				.andDo(print());
		
	}
	
	
	
	@Test
	void testCreateOrder_With_invalid_data() throws Exception {
		Order order = new Order();
		List<OrderItem> listOfOrderItems = new ArrayList<OrderItem>();
		listOfOrderItems.add(new OrderItem());
		listOfOrderItems.add(new OrderItem());
		order.setOrderItems(listOfOrderItems);

		String jsonRequest = objectmapper.writeValueAsString(order);
		
		mockmvc.perform(post("/order/u_ajeet").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError())
		        .andDo(print());
		
	}

	@Test
	void testUpdateOrder() throws Exception {
		mockmvc.perform(put("/order/1/delivered")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				/*
				 * .andExpect(jsonPath("$.status").exists())
				 * .andExpect(jsonPath("$.message").exists())
	 			 * .andExpect(jsonPath("$.status").value("200"))
				 * .andExpect(jsonPath("$.message").value("success"))
				 */
		        .andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	void test_Update_Order_when_orderId_invalid() throws Exception {
		mockmvc.perform(put("/order/4537447/delivered")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		       
		        .andExpect(status().isNotFound())
				.andDo(print());
	}
	
	
	@Test
	void testGetAllItemsOfOrder() throws Exception {
		mockmvc.perform(get("/order/{orderId}", "1")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(status().isOk())
				.andDo(print());
		
	}
	
	@Test
	void testGetAllItemsOfOrder_when_orderId_invalid() throws Exception {
		mockmvc.perform(get("/order/{orderId}", "325445")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(status().isNotFound())
				.andDo(print());
		
	}
	
	@Test
	void testGetAllItemsOfOrder_when_orderId_is_String() throws Exception {
		mockmvc.perform(get("/order/{orderId}", "sessererewrew")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(status().isInternalServerError())
				.andDo(print());
		
	}

	@Test
	void testGetOrderHistoryOfUser() throws Exception {
		mockmvc.perform(get("/order/user/u_ajeet")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				/*
				 * .andExpect(jsonPath("$.status").exists())
				 * .andExpect(jsonPath("$.status").value("200"))
				 */
		        .andExpect(status().isOk())
				.andDo(print());
		
	}
	
	@Test
	void testGetOrderHistoryOfUser_when_userId_invalid() throws Exception {
		mockmvc.perform(get("/order/user/fdgdfggfg")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(jsonPath("$.status").exists())
		        .andExpect(jsonPath("$.status").value("404"))
		        .andExpect(status().isNotFound())
				.andDo(print());
		
	}
	
	
	@Test
	void testgetItemDetailOfOrder() throws Exception {
		mockmvc.perform(get("/order/2/124")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				/*
				 * .andExpect(jsonPath("$.status").exists())
				 * .andExpect(jsonPath("$.status").value("200"))
				 */
		        .andExpect(status().isOk())
				.andDo(print());
		
	}

	
	
	@Test
	void testgetItemDetailOfOrder_when_orderId_invalid() throws Exception {
		mockmvc.perform(get("/order/77373/124")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(jsonPath("$.status").exists())
		        .andExpect(jsonPath("$.status").value("404"))
				.andDo(print());
		
	}
	
	@Test
	void testgetItemDetailOfOrder_when_productId_invalid() throws Exception {
		mockmvc.perform(get("/order/1/65465654")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(jsonPath("$.status").exists())
		        .andExpect(jsonPath("$.status").value("404"))
				.andDo(print());
		
	}
}
