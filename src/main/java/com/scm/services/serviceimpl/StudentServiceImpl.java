package com.scm.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.dao.StudentDAO;
import com.scm.services.serviceinf.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDao;
	
	public StudentServiceImpl() {
		
	}

	@Override
	public StudentVO saveStudent(StudentVO studentVO) {
		return studentDao.saveStudent(studentVO);
	}

	@Override
	public List<StudentVO> getStudents() {
		return studentDao.getStudents();
	}

}
