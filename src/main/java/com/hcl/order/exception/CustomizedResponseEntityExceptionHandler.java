package com.hcl.order.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.order.responseEntity.ExceptionResponse;

/**
 * This class is used for showing response to client on the basis of Exception
 * class.
 * 
 * @author AjeetY
 *
 */

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * handle all Exceptions
	 * 
	 * @param ex      Exception
	 * @param request WebRequest
	 * @return ResponseEntity
	 * @throws Exception
	 */

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse("500",ex.getMessage(),request.getDescription(false) ,new Date());

		logger.info("response ----> {}", ex.getClass().toString());

	 if (ex instanceof OrderNotFoundException) {
			exceptionResponse.setStatus("404");
			return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
		} else if (ex instanceof ProductNotFoundException) {
			exceptionResponse.setStatus("404");
			return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
		}

		else {
			return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
