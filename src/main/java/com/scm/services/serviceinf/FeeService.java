package com.scm.services.serviceinf;

import java.util.List;

import com.bulls.scm.common.vo.FeeDetailVO;
import com.bulls.scm.common.vo.StudentVO;
import com.bulls.scm.vo.StudentRequestVO;
import com.scm.services.exception.ServiceException;

public interface FeeService {

	
	List<FeeDetailVO> getFeeDetails(StudentVO studentVO)throws ServiceException;
	
	List<StudentVO> getStudentFeeDetails(StudentRequestVO studentRequestVO)throws ServiceException;
	
	
	
	
}
