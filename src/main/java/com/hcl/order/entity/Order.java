package com.hcl.order.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.hcl.order.utilities.AuditModel;



@Entity
@Table(name = "orders")
@Transactional
public class Order extends AuditModel{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderRefrenceNo;

	private String userId;

	private String orderStatus;

	private String orderTotal;
	private String paymentMode;
	private String paymentCurrency;
	private String shippingAdderess;
	
	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	
	
	public Order() {
	
	}
	
	

	public Order(String orderRefrenceNo, String userId, String orderStatus, String orderTotal, String paymentMode,
			String paymentCurrency,String shippingAdderess) {
		super();
		this.orderRefrenceNo = orderRefrenceNo;
		this.userId = userId;
		this.orderStatus = orderStatus;
		this.orderTotal = orderTotal;
		this.paymentMode = paymentMode;
		this.paymentCurrency = paymentCurrency;
		this.shippingAdderess = shippingAdderess;
		
	}



	public String getOrderRefrenceNo() {
		return orderRefrenceNo;
	}

	public void setOrderRefrenceNo(String orderRefrenceNo) {
		this.orderRefrenceNo = orderRefrenceNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public String getShippingAdderess() {
		return shippingAdderess;
	}



	public void setShippingAdderess(String shippingAdderess) {
		this.shippingAdderess = shippingAdderess;
	}



	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	

}
