package com.scm.services.dao;

import com.scm.services.common.WrappedResponse;
import com.scm.services.dao.entity.Classification;
import com.scm.services.dao.entity.GetClassificationResponse;

public interface ClassificationDAO {
	
	WrappedResponse<GetClassificationResponse> getClassificationDetails(Classification requestEntity)throws Exception;

}
