package com.hcl.order.responseEntity;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class Response {

	private String textStatus;
	private HttpStatus status;
	
	private String message;
	private List<?> data;
	private Set<?> setdata;
	
	

//	public Response(String status, String message, List<?> data) {
//		this.status = status;
//		this.message = message;
//		this.data = data;
//	}

	public Response() {
		super();
	}

//	public Response(String status, String message, Set<?> setdata) {
//
//		this.status = status;
//		this.message = message;
//		this.setdata = setdata;
//	}

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Set<?> getSetdata() {
		return setdata;
	}

	public String getTextStatus() {
		return textStatus;
	}

	public void setTextStatus(String textStatus) {
		this.textStatus = textStatus;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	
//	public void setSetdata(Set<?> setdata) {
//		this.setdata = setdata;
//	}

	
	
	
}
