package com.hcl.order.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class AppProperties {

	private String orderStatusPreparing;
	private String successStatus;

	public String getOrderStatusPreparing() {
		return orderStatusPreparing;
	}

	public void setOrderStatusPreparing(String orderStatusPreparing) {
		this.orderStatusPreparing = orderStatusPreparing;
	}

	public String getSuccessStatus() {
		return successStatus;
	}

	public void setSuccessStatus(String successStatus) {
		this.successStatus = successStatus;
	}

}
