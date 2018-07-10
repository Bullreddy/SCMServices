package com.scm.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bulls.scm.common.vo.StudentVO;
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
	@RequestMapping(value="/getStudents", method = RequestMethod.GET)
	public ResponseEntity getStudents() {
		return new ResponseEntity(studentService.getStudents(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/exportStudents",produces="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public void exportStudents(HttpServletRequest request, HttpServletResponse response) {
		ExportUtil.exporttoXLS(studentService.getStudents(),response);
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"testExcel.xls\"");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		   // response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		    
			ExportUtil.exporttoXLS(studentService.getStudents(),response).write(response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
