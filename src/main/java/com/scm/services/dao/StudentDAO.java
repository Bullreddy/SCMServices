package com.scm.services.dao;

import java.util.List;

import com.bulls.scm.common.vo.StudentVO;

public interface StudentDAO {
	
	public StudentVO saveStudent(StudentVO studentVO);
	
	List<StudentVO> getStudents();

}
