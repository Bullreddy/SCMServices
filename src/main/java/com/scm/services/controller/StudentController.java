package com.scm.services.controller;

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

import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.serviceinf.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	private final static Logger LOGGER = Logger.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/saveStudent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentVO> saveStudent(@RequestBody StudentVO studentVO,BindingResult bindingResults) {
		LOGGER.info(studentVO);
		return new ResponseEntity<StudentVO>(studentService.saveStudent(studentVO),HttpStatus.OK);
	}
	
	@RequestMapping(value="/getStudents", method = RequestMethod.GET)
	public ResponseEntity getStudents() {
		return new ResponseEntity(studentService.getStudents(),HttpStatus.OK);
	}

}
