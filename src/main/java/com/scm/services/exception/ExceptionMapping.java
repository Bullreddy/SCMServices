package com.scm.services.exception;

public enum ExceptionMapping {
	GENERAL_EXCEPTION(500,
			"we were unable to process your request at this time. Please contact admin");

	private final Integer code;
	private final String key;

	private ExceptionMapping(Integer code, String key) {
		this.code = code;
		this.key = key;
	}

	public Integer getCode() {
		return code;
	}

	public String getKey() {
		return key;
	}

}
