package com.scm.services.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.scm.services.common.Status;
import com.scm.services.common.WrappedResponse;
import com.scm.services.dao.MasterDAO;
import com.scm.services.dao.entity.Classification;
import com.scm.services.dao.entity.GetClassificationResponse;

@Repository
public class MasterDAOImpl extends BaseDAOImpl implements MasterDAO {

	@Override
	public WrappedResponse<GetClassificationResponse> getClassificationDetails(Classification requestEntity)
			throws Exception {
		// TODO Auto-generated method stub
		WrappedResponse<GetClassificationResponse> wrappedResponse = new WrappedResponse<>(Status.FAILURE);
		try {
			GetClassificationResponse response = new GetClassificationResponse();
			List<Classification> classifications = getEM().createQuery("from Configuration where master_type=?")
					.getResultList();
			response.setClassifications(classifications);
			wrappedResponse.setStatus(Status.SUCCESS);
			wrappedResponse.setResponse(response);
		} catch (Exception e) {
			wrappedResponse.setException(e);
		}
		return wrappedResponse;

	}

}
