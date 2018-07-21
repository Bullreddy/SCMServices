package com.scm.services.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulls.scm.common.vo.FeeDetailVO;
import com.bulls.scm.common.vo.StudentVO;
import com.bulls.scm.vo.StudentRequestVO;
import com.scm.services.convertor.EntityConvertor;
import com.scm.services.convertor.FeeEntityConvertor;
import com.scm.services.dao.FeeDAO;
import com.scm.services.dao.entity.Admission;
import com.scm.services.dao.entity.FeeDetail;
import com.scm.services.exception.ServiceException;

@Repository
public class FeeDAOImpl extends BaseDAOImpl implements FeeDAO {

	private final static Logger LOGGER = Logger.getLogger(FeeDAOImpl.class);

	@Autowired
	private FeeEntityConvertor feeConvertor;
	
	@Autowired
	private EntityConvertor convertor;

	public FeeDAOImpl() {
	}

	@Override
	public List<FeeDetailVO> getFeeDetails(StudentVO studentVO) throws ServiceException {
		// masterEntityManager
		Query query = getEM().createNamedQuery("FeeDetails.findByParams");
		query.setParameter("studentId", studentVO.getId());
		query.setParameter("yearId", studentVO.getYearId().getId());

		try {
			List<FeeDetail> feeDetails = (List<FeeDetail>) query.getResultList();
			return feeConvertor.convertToFeeDetailVO(feeDetails);

		} catch (Exception err) {

			throw new ServiceException(err);
		}
	}

	@Override
	public List<StudentVO> getStudentFeeDetails(StudentRequestVO studentRequestVO) throws ServiceException {
		List<StudentVO> studentVOList = new ArrayList<StudentVO>();
		
		String s = getEM().createNamedQuery("getStudentbyParams")
			    .unwrap(org.hibernate.Query.class)
			    .getQueryString();
		StringBuilder sb = new StringBuilder(s);
		
		if(studentRequestVO.getPhaseID() != null)
			sb.append(" and a.phaseID = "+Integer.parseInt(studentRequestVO.getPhaseID()));
		if(studentRequestVO.getAcademicYearID() != null)
			sb.append(" and a.academicYearID = "+Integer.parseInt(studentRequestVO.getAcademicYearID()));
		if(studentRequestVO.getTradeID() != null)
			sb.append(" and a.tradeID = "+Integer.parseInt(studentRequestVO.getTradeID()));
		Query  query = getEM().createQuery(sb.toString());
		query.setParameter("branchID", Long.valueOf(studentRequestVO.getBranchID()));
	
		List<Admission> admissions = (List<Admission>) query.getResultList();
		
		LOGGER.info(admissions);
		if(null == admissions)
			return null;
		return convertor.convertAdmissionListTOStudentVOList(admissions);
	}

}
