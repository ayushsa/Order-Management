package com.hcl.order.responseEntity;

/**
 * Response class for showing all common Exceptions 
 * @author AjeetY
 *
 */
import java.util.Date;

public class ExceptionResponse {

	private String status;
	private String massage;
	private String detail;
	private Date timestamp;

	public ExceptionResponse(String status, String massage, String detail, Date timestamp) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.massage = massage;
		this.detail = detail;

	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMassage() {
		return massage;
	}

	public String getDetail() {
		return detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	

}
