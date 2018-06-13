package com.scm.services.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.common.MapperUtils;
import com.scm.services.common.Status;
import com.scm.services.common.WrappedResponse;
import com.scm.services.dao.ClassificationDAO;
import com.scm.services.dao.entity.Admission;
import com.scm.services.dao.entity.Classification;
import com.scm.services.dao.entity.GetClassificationResponse;

@Repository
public class ClassificationDAOImpl extends BaseDAOImpl implements ClassificationDAO {

	private final static Logger LOGGER = Logger.getLogger(ClassificationDAOImpl.class);
	
	private MapperUtils mapper = new MapperUtils();
	
	@Override
	public WrappedResponse<GetClassificationResponse> getClassificationDetails(Classification requestEntity)
			throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug("getclassfications operation dao layer begins");
		WrappedResponse<GetClassificationResponse> wrappedResponse = new WrappedResponse<>(Status.FAILURE);
		try {
			GetClassificationResponse response = new GetClassificationResponse();
			List<Classification> classifications = getEM().createQuery("from Classification")
					.getResultList();
			response.setClassifications(classifications);
			wrappedResponse.setStatus(Status.SUCCESS);
			wrappedResponse.setResponse(response);
		} catch (Exception e) {
			LOGGER.error("getClassfication operation failed dao exception-->",e);
			wrappedResponse.setStatus(Status.FAILURE);
			wrappedResponse.setException(e);
		}
		return wrappedResponse;

	}

	@Override
	public List<StudentVO> getStudents() {
		List<StudentVO> studentVOList = new ArrayList<StudentVO>();
		Query query = getEM().createNamedQuery("Admission.findAll");
		List<Admission> admissions = query.getResultList();
		admissions.forEach(admission -> {
			studentVOList.add(mapper.map(admission, StudentVO.class));
		});
		LOGGER.info("admissions "+admissions);
		return studentVOList;
	}

}
