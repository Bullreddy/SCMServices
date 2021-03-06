package com.scm.services.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

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
import com.bulls.scm.vo.StudentRequestVO;
import com.scm.services.common.ExportUtil;
import com.scm.services.serviceinf.StudentService;

@RestController
@RequestMapping("/student")

public class StudentController {
	
	private final static Logger LOGGER = Logger.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/saveStudent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentVO> saveStudent(@RequestBody StudentVO studentVO,BindingResult bindingResults) {
		LOGGER.debug("vo "+studentVO.getCertificateIds());
		return new ResponseEntity<StudentVO>(studentService.saveStudent(studentVO),HttpStatus.OK);
	}

	@RequestMapping(value="/getStudentForm", method = RequestMethod.PUT)
	public ResponseEntity<StudentVO> getStudent(@RequestBody StudentVO studentVO) {
		
		
		LOGGER.info("rest controler.."+studentVO.getAdmissionNo());
		StudentVO response=studentService.getStudentById(studentVO.getAdmissionNo());
		LOGGER.info("rest controler from be.."+response);
		if(response!=null) {
			return new ResponseEntity<StudentVO>(response,HttpStatus.OK);
		}else {
			
			LOGGER.info("rest controler null..");
			return new ResponseEntity(null,HttpStatus.OK);
		}
		
	}
	@RequestMapping(value="/getStudents", method = RequestMethod.POST)
	public ResponseEntity getStudents(@RequestBody StudentRequestVO studentRequestVO) {
		return new ResponseEntity(studentService.getStudents(studentRequestVO),HttpStatus.OK);
	}
	@RequestMapping(value="/getallstudents", method = RequestMethod.GET)
	public ResponseEntity getAllStudents() {
		return new ResponseEntity(studentService.getStudents(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/exportStudents",produces="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public void exportStudents(@RequestBody StudentRequestVO studentRequestVO, HttpServletResponse response) {
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"testExcel.xls\"");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		    
			ExportUtil.exporttoXLS(studentService.getStudents(studentRequestVO),response).write(response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
