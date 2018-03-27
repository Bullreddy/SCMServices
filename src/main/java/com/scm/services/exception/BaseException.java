package com.scm.services.exception;

public abstract class BaseException extends RuntimeException {

	private Integer code;
	private String key;
	private String errorMessage;

	
	public BaseException(Integer code, String key, String errorMessage) {
		super();
		this.code = code!=null?ExceptionMapping.GENERAL_EXCEPTION.getCode():null;
		this.key = key!=null?ExceptionMapping.GENERAL_EXCEPTION.getKey():null;
		this.errorMessage = errorMessage;
	}
	protected BaseException(ExceptionMapping exceptionMapping,Throwable cause){
		super(cause);
		this.code = exceptionMapping.getCode();
		this.key = exceptionMapping.getKey();
		this.errorMessage = cause!=null?cause.getMessage():null;
	}
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
