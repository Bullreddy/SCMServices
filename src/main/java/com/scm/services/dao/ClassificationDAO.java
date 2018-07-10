package com.scm.services.dao;

import java.util.List;

import com.scm.services.common.ClassificationType;
import com.scm.services.common.WrappedResponse;
import com.scm.services.dao.entity.Classification;
import com.scm.services.dao.entity.GetClassificationResponse;

public interface ClassificationDAO {
	
	WrappedResponse<GetClassificationResponse> getClassificationDetails(Classification requestEntity)throws Exception;
	
	WrappedResponse<GetClassificationResponse> getClassificationDetails(List<ClassificationType> classificationTypes, String branchID)throws Exception;
	
	WrappedResponse<GetClassificationResponse> getCertificates(String scholarshipType)throws Exception;
	
}
