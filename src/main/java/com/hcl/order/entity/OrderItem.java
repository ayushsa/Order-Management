package com.hcl.order.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcl.order.utilities.AuditModel;

@Entity
@Table(name = "orderItem")
@Transactional
public class OrderItem extends AuditModel{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank()
	private String product_id;
	private String productName;
	private String description;
	
	private String orderedPrice;
	private String orderedQty;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false )
	@JoinColumn(name = "order_id", nullable = false)
	@JsonIgnore
	private Order orders;
	
	private String productStatus;
	
	
	public OrderItem() {

	}


	public String getProduct_id() {
		return product_id;
	}


	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


//	public Order getOrders() {
//		return orders;
//	}


	public void setOrders(Order orders) {
		this.orders = orders;
	}


	public String getProductStatus() {
		return productStatus;
	}


	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}


	public String getOrderedPrice() {
		return orderedPrice;
	}


	public void setOrderedPrice(String orderedPrice) {
		this.orderedPrice = orderedPrice;
	}


	public String getOrderedQty() {
		return orderedQty;
	}


	public void setOrderedQty(String orderedQty) {
		this.orderedQty = orderedQty;
	}


	public OrderItem(@NotBlank String product_id, String productName, String description, String orderedPrice, String orderedQty, Order orders) {
		this.product_id = product_id;
		this.productName = productName;
		this.description = description;
		this.orderedPrice = orderedPrice;
		this.orderedQty = orderedQty;
		this.orders = orders;
	}
	
	
	

}
