package com.scm.services.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.common.MapperUtils;
import com.scm.services.dao.entity.Admission;

@Component
public class EntityConvertor {

	private final static Logger LOGGER = Logger.getLogger(EntityConvertor.class);
	
	@Autowired
	public MapperUtils mapper;
	  
	public StudentVO convertAdmissionTOStudentVO(Admission admission) {
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
	}

	public List<StudentVO> convertAdmissionListTOStudentVOList(List<Admission> admissions) {
		List<StudentVO> studentVOList = new ArrayList<StudentVO>();
		LOGGER.info("mapper "+mapper);
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
}
