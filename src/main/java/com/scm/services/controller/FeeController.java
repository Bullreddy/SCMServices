package com.scm.services.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bulls.scm.common.vo.FeeDetailVO;
import com.bulls.scm.common.vo.ServiceErrorVO;
import com.bulls.scm.common.vo.StudentVO;
import com.bulls.scm.fee.vo.GetFeeDetailsResponseVO;
import com.scm.services.common.ServiceErrorCodes;
import com.scm.services.exception.ServiceException;
import com.scm.services.serviceinf.FeeService;

@RestController
@RequestMapping("/fee")

public class FeeController {
	
	private final static Logger LOGGER = Logger.getLogger(FeeController.class);
	
	@Autowired
	private FeeService feeService;
	
	@RequestMapping(value="/getFeeDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetFeeDetailsResponseVO> saveStudent(@RequestBody StudentVO studentVO,BindingResult bindingResults) {
		LOGGER.debug("vo "+studentVO.getCertificateIds());
		GetFeeDetailsResponseVO response = new GetFeeDetailsResponseVO();
		List<FeeDetailVO> feeDetails = new ArrayList<>();
		try {
		 feeDetails = feeService.getFeeDetails(studentVO);
		 if(CollectionUtils.isEmpty(feeDetails)) {
			 ServiceErrorVO errorVO = new ServiceErrorVO();
			 errorVO.setErrorMessage(ServiceErrorCodes.RECORD_NOT_FOUND.getMessage());
			 errorVO.setErrorCode(String.valueOf(ServiceErrorCodes.FEE_DETAILS_FAILED.getCode()));
			 response.setErrorVO(errorVO);
			 return new ResponseEntity<GetFeeDetailsResponseVO>(response,HttpStatus.NOT_FOUND);
		 }
		 response.setFeeDetails(feeDetails);
		}catch(ServiceException e) {
			ServiceErrorVO errorVO = new ServiceErrorVO();
			errorVO.setErrorMessage(e.getMessage());
			errorVO.setErrorCode(String.valueOf(ServiceErrorCodes.FEE_DETAILS_FAILED.getCode()));
			response.setErrorVO(errorVO);
			return new ResponseEntity<GetFeeDetailsResponseVO>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<GetFeeDetailsResponseVO>(response, HttpStatus.OK);
	}

	
}
