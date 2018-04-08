package com.scm.services.common;

import java.util.ResourceBundle;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.bulls.scm.common.vo.ServiceErrorVO;
import com.scm.services.exception.BaseException;

@Component
public class ServiceErrorVOBuilder {

	public ServiceErrorVO buildErrorFromException(Exception ex) {
		ServiceErrorVO errorVO = new ServiceErrorVO();
		if(ex instanceof BaseException) {
			BaseException baseEx = (BaseException) ex;
			errorVO.setErrorCode(baseEx.getCode()!=null?String.valueOf(baseEx.getCode()):ServiceStatus.INTERNAL_SERVER_ERROR.getCode().toString());
			errorVO.setErrorMessage(baseEx.getErrorMessage());
			errorVO.setDeveloperMessage(baseEx.getErrorMessage());
			errorVO.setMoreInfo("For More Info contact admin");
		}else {
			errorVO.setErrorMessage(ex.getMessage());
		}
		
		return errorVO;
	}
	
	public ServiceErrorVO buildErrorFromException(Throwable ex) {
		ServiceErrorVO errorVO = new ServiceErrorVO();
		if(ex instanceof BaseException) {
			BaseException baseEx = (BaseException) ex;
			errorVO.setErrorCode(baseEx.getCode()!=null?String.valueOf(baseEx.getCode()):null);
			errorVO.setErrorMessage(baseEx.getErrorMessage());
		}else {
			errorVO.setErrorMessage(ex.getMessage());
		}
		
		return errorVO;
	}
	public ServiceErrorVO buildErrorFromBindingResults(BindingResult bindingResults) {
		ServiceErrorVO errorVO = null;
		String message= null;
		if(null!=bindingResults.getFieldError()) {
			message = this.getValidationMessage(bindingResults.getFieldError().getCodes()[0]);
			errorVO = new ServiceErrorVO(String.valueOf(ServiceStatus.BAD_REQUEST.getCode()),
					bindingResults.getFieldError().getDefaultMessage(),
					bindingResults.getFieldError().getDefaultMessage(),
					bindingResults.getFieldError().getField(),"For More Info contact admin");
		}
		return errorVO;
	}
	
	private String getValidationMessage(String messageKey) {
		ResourceBundle validationMessage = ResourceBundle.getBundle("validation.validation");
		if(validationMessage!=null) {
			try {
				return validationMessage.getString(messageKey);
			}catch(Exception e) {
				
			}
		}
		return null;
	}
}
