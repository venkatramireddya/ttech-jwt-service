package com.ttech.jwt.security.exception;

public class ApiResponse {
	 private long statusCode;
	 private String message;
	 
	 public ApiResponse() {
	}

	 public ApiResponse(long statusCode, String message) {
		 this.statusCode = statusCode;
		 this.message = message;
	 }

	public long getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(long statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [statusCode=" + statusCode + ", message=" + message + "]";
	}
}
