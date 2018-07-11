package com.scm.services.serviceinf;

import java.util.List;

import com.bulls.scm.common.vo.StudentVO;

public interface StudentService {

	public StudentVO saveStudent(StudentVO studentVO);
	
	List<StudentVO> getStudents();
}
