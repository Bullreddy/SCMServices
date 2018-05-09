package com.scm.services.dao;

import java.util.List;

import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.common.WrappedResponse;
import com.scm.services.dao.entity.Classification;
import com.scm.services.dao.entity.GetClassificationResponse;

public interface ClassificationDAO {
	
	WrappedResponse<GetClassificationResponse> getClassificationDetails(Classification requestEntity)throws Exception;
	
	List<StudentVO> getStudents();

}
