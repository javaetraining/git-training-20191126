package com.javaetraining.spring.boot.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaetraining.spring.boot.model.ExceptionResponse;

@ControllerAdvice
@RestController
public class MyAppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getLocalizedMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value() + "", request.getDescription(false), "");
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptionException(Exception exception, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getLocalizedMessage(),
				HttpStatus.NOT_FOUND.value() + "", request.getDescription(false), "");
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);

	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getLocalizedMessage(),
				HttpStatus.BAD_REQUEST.value() + "", request.getDescription(false), exception.getBindingResult().toString());
		
		System.out.println(exception.getBindingResult().getModel());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
