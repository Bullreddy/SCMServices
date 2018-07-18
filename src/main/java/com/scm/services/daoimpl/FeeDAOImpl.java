package com.scm.services.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulls.scm.common.vo.FeeDetailVO;
import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.convertor.FeeEntityConvertor;
import com.scm.services.dao.FeeDAO;
import com.scm.services.dao.entity.FeeDetail;
import com.scm.services.exception.ServiceException;

@Repository
public class FeeDAOImpl extends BaseDAOImpl implements FeeDAO {

	private final static Logger LOGGER = Logger.getLogger(FeeDAOImpl.class);

	@Autowired
	private FeeEntityConvertor convertor;

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
			return convertor.convertToFeeDetailVO(feeDetails);

		} catch (Exception err) {

			throw new ServiceException(err);
		}
	}

}
