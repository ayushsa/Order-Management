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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.order.entity.Order;
import com.hcl.order.entity.OrderItem;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration
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
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.status").value("201"))
				.andExpect(jsonPath("$.message").value("success")).andDo(print());
		
	}

	@Test
	void testUpdateOrder() throws Exception {
		mockmvc.perform(put("/order/1/delivered")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(jsonPath("$.status").exists())
		        .andExpect(jsonPath("$.message").exists())
		        .andExpect(jsonPath("$.status").value("200"))
		        .andExpect(jsonPath("$.message").value("success"))
		        .andExpect(status().isOk())
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
	void testGetOrderHistoryOfUser() throws Exception {
		mockmvc.perform(get("/order/user/u_ajeet")
				.accept(MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(jsonPath("$.status").exists())
		        .andExpect(jsonPath("$.status").value("200"))
		        .andExpect(status().isOk())
				.andDo(print());
		
	}

}
