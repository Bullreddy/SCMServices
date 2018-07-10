package com.scm.services.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.common.MapperUtils;
import com.scm.services.dao.StudentDAO;
import com.scm.services.dao.entity.Admission;
import com.scm.services.dao.entity.Datasourceconfig;

@Repository
public class StudentDAOImpl extends BaseDAOImpl implements StudentDAO {

	private final static Logger LOGGER = Logger.getLogger(StudentDAOImpl.class);
	
	public StudentDAOImpl() {
	}
	@Override
	public StudentVO getStudentById(String number) {
		LOGGER.info("result from student...");
		
		//masterEntityManager
		Query query = getEM().createNamedQuery("Admission.findOne");
			query.setParameter("number", number);
		
		
		try {
			Admission admission =  (Admission) query.getSingleResult();
			StudentVO studentVO = mapper.map(admission, StudentVO.class);
			return studentVO;
			//return admission;
			
		}catch(NoResultException err) {
			 
		return null;	
		}
	}
	@Override
	public StudentVO saveStudent(StudentVO studentVO) {
		LOGGER.info("save student...........");
		LOGGER.info(studentVO.getCasteID());
		LOGGER.info(studentVO.getPhaseID());
		Admission admission = mapper.map(studentVO, Admission.class);
		admission.setAcademicYearID(studentVO.getAcademicYearID());
		admission.setCaste(studentVO.getCasteID());
		admission.setPhaseID(studentVO.getPhaseID());
		admission.setTradeID(studentVO.getTradeID());
		admission.setTypeID(studentVO.getTypeID());
		if(admission.getId()==0) {
			getEM().persist(admission);
			LOGGER.info("save student... new........");
		}else {
			LOGGER.info("save student... update........");
			LOGGER.info("save student... update........"+admission.getId());
			getEM().merge(admission);
		}
		studentVO = mapper.map(admission, StudentVO.class);
		return studentVO;
	}

	@Override
	public List<StudentVO> getStudents() {
		List<StudentVO> studentVOList = new ArrayList<StudentVO>();
		Query query = getEM().createNamedQuery("Admission.findAll");
		List<Admission> admissions = query.getResultList();
		admissions.forEach(admission -> {
			StudentVO studentVO = mapper.map(admission, StudentVO.class); 
			studentVO.setAcademicYearID(admission.getAcademicYearID());
			studentVO.setCasteID(admission.getCaste());
			studentVO.setPhaseID(admission.getPhaseID());
			studentVO.setTradeID(admission.getTradeID());
			studentVO.setTypeID(admission.getTypeID());
			studentVOList.add(studentVO);
		});
		return studentVOList;
	}

}
