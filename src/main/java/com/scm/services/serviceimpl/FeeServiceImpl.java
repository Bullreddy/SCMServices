package com.scm.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulls.scm.common.vo.FeeDetailVO;
import com.bulls.scm.common.vo.StudentVO;
import com.bulls.scm.vo.StudentRequestVO;
import com.scm.services.dao.FeeDAO;
import com.scm.services.exception.ServiceException;
import com.scm.services.serviceinf.FeeService;

@Service
public class FeeServiceImpl implements FeeService {

	@Autowired
	private FeeDAO feeDAO;
	
	@Override
	public List<FeeDetailVO> getFeeDetails(StudentVO studentVO) throws ServiceException {
		return feeDAO.getFeeDetails(studentVO);
	}

	@Override
	public List<StudentVO> getStudentFeeDetails(StudentRequestVO studentRequestVO) throws ServiceException {
		return feeDAO.getStudentFeeDetails(studentRequestVO);
	}
}
