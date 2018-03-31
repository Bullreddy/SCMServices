package com.scm.services.common;

import java.io.Serializable;



public class WrappedResponse<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// TODO: have to import correct package for status and have to oconstructor for
	// the fields status and response
	private Status status;
	private T response;
	private transient Exception exception;
	private String errorCode;
	private String errorMessage;

	public WrappedResponse() {

	}

	public WrappedResponse(Status status, Exception exception) {
		this.status = status;
		this.exception = exception;
	}

	public WrappedResponse(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
