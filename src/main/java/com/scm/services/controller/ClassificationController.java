package com.scm.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bulls.scm.vo.ClassificationVO;
import com.bulls.scm.vo.GetClassificationRequestVO;
import com.bulls.scm.vo.GetClassificationResponseVO;
import com.scm.services.common.MapperUtils;
import com.scm.services.dto.GetClassificationRequestDTO;
import com.scm.services.serviceinf.MasterService;

@RestController
@RequestMapping("/services/rest/classifications")
public class ClassificationController {

	@Autowired
	private MapperUtils mapper;

	@Autowired
	private MasterService masterService;

	@RequestMapping(value = "/getClassifications", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetClassificationResponseVO> getMaster(@RequestBody GetClassificationRequestVO requestVO) {
		ClassificationVO responseVO = new ClassificationVO();
		GetClassificationRequestDTO reswuestDTO = this.mapper.map(requestVO, GetClassificationRequestDTO.class);
		this.masterService.getClassificationDetails(this.mapper.map(requestVO, GetClassificationRequestDTO.class));
		return new ResponseEntity(responseVO, HttpStatus.OK);
	}

}
