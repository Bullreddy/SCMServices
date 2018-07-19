package com.scm.services.common;

public enum ServiceErrorCodes {

	RECORD_NOT_FOUND(100, "No Records found in Database."),
	FEE_DETAILS_FAILED(101,"Failed during fee details. Please see eror log for more info.");

	private ServiceErrorCodes(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
