package com.scm.services.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bulls.scm.common.vo.ServiceErrorVO;
import com.bulls.scm.vo.GetClassificationRequestVO;
import com.bulls.scm.vo.GetClassificationResponseVO;
import com.scm.services.common.ClassificationType;
import com.scm.services.common.MapperUtils;
import com.scm.services.common.ServiceErrorVOBuilder;
import com.scm.services.dto.GetClassificationRequestDTO;
import com.scm.services.dto.GetClassificationResponseDTO;
import com.scm.services.serviceinf.ClassificationService;


@RestController
@RequestMapping("/services/rest/classifications")
public class ClassificationController {

	private final static Logger LOGGER = Logger.getLogger(ClassificationController.class);
	
	@Autowired
	private MapperUtils mapper;

	@Autowired
	private ClassificationService masterService;
	
	@Autowired
	private ServiceErrorVOBuilder errorBuilder;

	@RequestMapping(value = "/getClassifications", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetClassificationResponseVO> getMaster(@RequestBody GetClassificationRequestVO requestVO,BindingResult bindingResults) {
		LOGGER.debug("getClassifications operation begins--->");
		GetClassificationResponseVO responseVO = new GetClassificationResponseVO();
		try {
			if(bindingResults!=null && bindingResults.hasErrors()) {
				responseVO.setStatus(HttpStatus.BAD_REQUEST.value());
				ServiceErrorVO errorVO = this.errorBuilder.buildErrorFromBindingResults(bindingResults);
				responseVO.setErrorVO(errorVO);
			}
		GetClassificationRequestDTO requestDTO = new GetClassificationRequestDTO();
		requestDTO.setTypes(this.mapper.map(requestVO.getTypes(), new TypeToken<List<ClassificationType>>() {}.getType()));
		GetClassificationResponseDTO responseDTO = this.masterService.getClassificationDetails(requestDTO);
		responseVO = this.mapper.map(responseDTO, GetClassificationResponseVO.class);
		return new ResponseEntity(responseVO, HttpStatus.OK);
		}catch(Exception exception) {
			LOGGER.error("getClassifications operation failed due to -->",exception);
			ServiceErrorVO errorVO = this.errorBuilder.buildErrorFromException(exception);
			responseVO.setErrorVO(errorVO);
			return  new ResponseEntity(responseVO, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
	}

}
