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
import com.bulls.scm.vo.StudentRequestVO;
import com.scm.services.common.MapperUtils;
import com.scm.services.convertor.EntityConvertor;
import com.scm.services.dao.StudentDAO;
import com.scm.services.dao.entity.Admission;

import com.scm.services.dao.entity.Datasourceconfig;

import com.scm.services.dao.entity.StudentCertificate;


@Repository
public class StudentDAOImpl extends BaseDAOImpl implements StudentDAO {

	private final static Logger LOGGER = Logger.getLogger(StudentDAOImpl.class);
	
	@Autowired
	private EntityConvertor convertor;
	
	public StudentDAOImpl() {
	}
	@Override
	public StudentVO getStudentById(String number) {
		LOGGER.info("result from student...");
		
		//masterEntityManager
		Query query = getEM().createNamedQuery("Admission.findByAdmissionNO");
			query.setParameter("admissionNO", number);
		
		try {
			Admission admission =  (Admission) query.getSingleResult();
			return convertor.convertAdmissionTOStudentVO(admission);
			
		}catch(NoResultException err) {
			 
		return null;	
		}
	}
	@Override
	public StudentVO saveStudent(StudentVO studentVO) {
		LOGGER.info("save student...........");
		Admission admission = mapper.map(studentVO, Admission.class);
		admission.setPhotoSbmtd(studentVO.isPhotoSubmitted()?"Y":"N");
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

		studentVO = convertor.convertAdmissionTOStudentVO(admission);
		return studentVO;
	}
	@Override
	public List<StudentVO> getStudents(StudentRequestVO studentRequestVO) {
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
		if(studentRequestVO.getStudents() != null)
			addStudentFilter(sb,studentRequestVO);
		Query  query = getEM().createQuery(sb.toString());
		query.setParameter("branchID", Long.valueOf(studentRequestVO.getBranchID()));
	
		List<Admission> admissions = (List<Admission>) query.getResultList();
		
		LOGGER.info(admissions);
		if(null == admissions)
			return null;
		return convertor.convertAdmissionListTOStudentVOList(admissions);
	}
	
	private void addStudentFilter(StringBuilder sb, StudentRequestVO studentRequestVO) {
		StringBuilder studentIdBuilder = new StringBuilder();
			if(studentRequestVO.getStudents() != null && !studentRequestVO.getStudents().isEmpty()) {
				studentRequestVO.getStudents().forEach(studentId ->{
					if(studentIdBuilder.length()>0)
						studentIdBuilder.append(",");
						studentIdBuilder.append(studentId);
				});
			}
			if(studentIdBuilder.length()>0) {
				sb.append(" and id in ("+studentIdBuilder.toString()+")");
			}
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
