package com.scm.services.exception;

public class ServiceException extends BaseException {

	protected ServiceException(ExceptionMapping exceptionMapping,
			Throwable cause) {
		super(exceptionMapping, cause);
		// TODO Auto-generated constructor stub
	}

	protected ServiceException(Throwable cause) {
		super(ExceptionMapping.GENERAL_EXCEPTION, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Integer code, String key, String errorMessage) {
		super(code, key, errorMessage);
	}

	public ServiceException(Exception ex) {
		super(null, null, ex.getMessage());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
