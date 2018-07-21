package com.scm.services.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bulls.scm.common.vo.FeeDetailVO;
import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.common.DateUtil;
import com.scm.services.common.MapperUtils;
import com.scm.services.dao.entity.Admission;
import com.scm.services.dao.entity.FeeDetail;
import com.scm.services.exception.ServiceException;

@Component
public class EntityConvertor {

	private final static Logger LOGGER = Logger.getLogger(EntityConvertor.class);
	
	@Autowired
	public MapperUtils mapper;
	  
	public StudentVO convertAdmissionTOStudentVO(Admission admission, boolean feeDetails) {
		StudentVO studentVO = mapper.map(admission, StudentVO.class);
		studentVO.setPhotoSubmitted(admission.getPhotoSbmtd()!=null && admission.getPhotoSbmtd().equals("Y"));
		studentVO.setAcademicYearID(admission.getAcademicYearID());
		studentVO.setTradeID(admission.getTradeID());
		studentVO.setPhaseID(admission.getPhaseID());
		studentVO.setTypeID(admission.getTypeID());
		studentVO.setCasteID(admission.getCaste());
		List<String> certificateIds = new ArrayList();
		if(admission.getStudentCertificates() !=null && admission.getStudentCertificates().size() > 0) {
			
			admission.getStudentCertificates().forEach( studentCertificate ->{
				certificateIds.add(String.valueOf(studentCertificate.getCertificateid()));
			});
		}
		
		if(feeDetails) {
			LOGGER.info(admission.getFeeDetails());
			if(admission.getFeeDetails() != null && admission.getFeeDetails().size()>0) {
				List<FeeDetailVO> feeDetailsVO = new ArrayList();
				admission.getFeeDetails().forEach(feeDetail ->{
					feeDetailsVO.add(convertFeeToFeeDetailVO(feeDetail));
				});
				if(feeDetailsVO != null && feeDetailsVO.size() > 0)
					studentVO.setFeeDetailsVO(feeDetailsVO);
			}
			
		}
		
		studentVO.setCertificateIds(certificateIds);
		return studentVO;
	}
	
	public FeeDetailVO convertFeeToFeeDetailVO(FeeDetail entity) throws ServiceException{
		FeeDetailVO vo = new FeeDetailVO();
		vo.setAmount(entity.getAmount());
		vo.setCollectedBy(entity.getCollectedByUser().getUsername());
		//if(entity.getCreatedDate() != null)
		//vo.setCollectedDate(entity.getCreatedDate() != null ? DateUtil.convertToLocalDateTimeViaInstant(entity.getCreatedDate()) : null);
		vo.setCollectedDate(entity.getCollectedDate());
		vo.setStudentId(String.valueOf(entity.getStudentId()));
		vo.setStudentName(entity.getStudent().getName());
		return vo;
	}

	public List<StudentVO> convertAdmissionListTOStudentVOList(List<Admission> admissions, boolean feeDetails) {
		List<StudentVO> studentVOList = new ArrayList<StudentVO>();
		LOGGER.info("mapper "+mapper);
		for(Admission admission:admissions) {
			studentVOList.add(convertAdmissionTOStudentVO(admission,feeDetails));
		}
		return studentVOList;
	}
}
