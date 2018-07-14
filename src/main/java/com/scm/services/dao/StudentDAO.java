package com.scm.services.dao;

import java.util.List;

import com.bulls.scm.common.vo.StudentVO;
import com.bulls.scm.vo.StudentRequestVO;

public interface StudentDAO {
	
	public StudentVO saveStudent(StudentVO studentVO);
	
	List<StudentVO> getStudents();
	public StudentVO getStudentById(String number);
	public List<StudentVO> getStudents(StudentRequestVO studentRequestVO);

}
