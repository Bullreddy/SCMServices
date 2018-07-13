package com.scm.services.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;

import org.hibernate.Criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.common.MapperUtils;
import com.scm.services.dao.StudentDAO;
import com.scm.services.dao.entity.Admission;

import com.scm.services.dao.entity.Datasourceconfig;

import com.scm.services.dao.entity.StudentCertificate;


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
			studentVO.setAcademicYearID(admission.getAcademicYearID());
			studentVO.setTradeID(admission.getTradeID());
			studentVO.setPhaseID(admission.getPhaseID());
			studentVO.setTypeID(admission.getTypeID());
			List<String> certificateIds = new ArrayList();
			if(admission.getStudentCertificates() !=null && admission.getStudentCertificates().size() > 0) {
				
				admission.getStudentCertificates().forEach( studentCertificate ->{
					certificateIds.add(String.valueOf(studentCertificate.getCertificateid()));
				});
			}
			studentVO.setCertificateIds(certificateIds);
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

		
		saveCertificates(admission,studentVO);

		studentVO = mapper.map(admission, StudentVO.class);
		return studentVO;
	}
	@Override
	public List<StudentVO> getStudentByFilter(String phase,String trade,String year) {
		List<StudentVO> studentVOList = new ArrayList<StudentVO>();
		
		StoredProcedureQuery  query = getEM().createNamedStoredProcedureQuery("getstudentsByFilter");
	
		query.setParameter("phase", Integer.parseInt(phase));
		query.setParameter("trade", Integer.parseInt(trade));
		query.setParameter("years", Integer.parseInt(year));
		//query.execute();
		List<Admission> admissions = (List<Admission>) query.getResultList();
		
		
		
		LOGGER.info(admissions);
		
		for(Admission admission:admissions) {
			StudentVO responseVO = mapper.map(admission, StudentVO.class); 
			responseVO.setAcademicYearID(admission.getAcademicYearID());
			responseVO.setCasteID(admission.getCaste());
			responseVO.setPhaseID(admission.getPhaseID());
			responseVO.setTradeID(admission.getTradeID());
			responseVO.setTypeID(admission.getTypeID());
			studentVOList.add(responseVO);
		}
		
		return studentVOList;
	}
	
	private void saveCertificates(Admission admission, StudentVO studentVO) {
		List<String> certificatesIDs = new ArrayList<>();
		if(studentVO.getCertificateIds() !=null) {
			Query query = getEM().createNamedQuery("getStudentCertificateByStudentAndCertificateID");

			studentVO.getCertificateIds().forEach(certificateID ->{
				query.setParameter("studentID", admission.getId());
				query.setParameter("certificateID", Integer.valueOf((String)certificateID));
				StudentCertificate studentCertificate = null;
					List<StudentCertificate> resultList = (List<StudentCertificate>) query.getResultList();
					if(resultList!=null && !resultList.isEmpty())
					studentCertificate = resultList.get(0);
				if(null == studentCertificate)
					studentCertificate = new StudentCertificate();
				studentCertificate.setStudentid(admission.getId());
				studentCertificate.setCertificateid(Integer.valueOf((String)certificateID));
				if(studentCertificate.getId() == 0) {
					getEM().persist(studentCertificate);
				}else
					getEM().merge(studentCertificate);
				certificatesIDs.add((String)certificateID);
			});
			
			Query certificatesQuery = getEM().createNamedQuery("getStudentCertificateByStudentID");
			certificatesQuery.setParameter("studentID", admission.getId());
			
			List<StudentCertificate> studentCertificates = certificatesQuery.getResultList();
			Iterator<StudentCertificate> iter = studentCertificates.iterator();
			while(iter.hasNext()) {
				StudentCertificate studentCertificate = iter.next();
				if(!certificatesIDs.contains(String.valueOf(studentCertificate.getCertificateid())))
					getEM().remove(studentCertificate);
			}
		}
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
