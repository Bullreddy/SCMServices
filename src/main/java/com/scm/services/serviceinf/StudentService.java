package com.scm.services.serviceinf;

import java.util.List;

import com.bulls.scm.common.vo.StudentVO;

public interface StudentService {

	public StudentVO saveStudent(StudentVO studentVO);
	
	List<StudentVO> getStudents();
	
	public StudentVO getStudentById(String admissionNo);
	
	public List<StudentVO>  getStudentByFilter(String phase,String trade,String year);
	
}
