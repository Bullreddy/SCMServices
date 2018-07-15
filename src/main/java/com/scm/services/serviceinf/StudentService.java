package com.scm.services.serviceinf;

import java.util.List;

import com.bulls.scm.common.vo.StudentVO;
import com.bulls.scm.vo.StudentRequestVO;

public interface StudentService {

	public StudentVO saveStudent(StudentVO studentVO);
	
	List<StudentVO> getStudents();
	
	public StudentVO getStudentById(String admissionNo);
	
	public List<StudentVO>  getStudents(StudentRequestVO studentRequestVO);
	
}
