package com.javaetraining.spring.boot.model;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private String statusCode;
	private String url;
	private String developerMessage;

	public ExceptionResponse(Date timestamp, String message, String statusCode, String url, String developerMessage) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.statusCode = statusCode;
		this.url = url;
		this.developerMessage = developerMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

}
