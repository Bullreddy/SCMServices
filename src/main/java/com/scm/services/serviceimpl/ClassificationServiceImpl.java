package com.scm.services.serviceimpl;

import java.util.List;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scm.services.common.ClassificationType;
import com.scm.services.common.MapperUtils;
import com.scm.services.common.Status;
import com.scm.services.common.WrappedResponse;
import com.scm.services.dao.ClassificationDAO;
import com.scm.services.dao.entity.GetClassificationResponse;
import com.scm.services.dto.GetClassificationRequestDTO;
import com.scm.services.dto.GetClassificationResponseDTO;
import com.scm.services.exception.ServiceException;
import com.scm.services.serviceinf.ClassificationService;

@Service
public class ClassificationServiceImpl implements ClassificationService {

	@Autowired
	private ClassificationDAO masterDAO;
	
	@Autowired
	private MapperUtils mapper;

	@Override
	public GetClassificationResponseDTO getClassificationDetails(GetClassificationRequestDTO getMasterDTO) throws Exception {
		WrappedResponse<GetClassificationResponse> wrappedResponse = masterDAO
					.getClassificationDetails((List<ClassificationType>)this.mapper.map(getMasterDTO.getTypes(), new TypeToken<List<ClassificationType>>() {}.getType()),getMasterDTO.getBranchID());
			if(Status.FAILURE.equals(wrappedResponse.getStatus())) {
				throw new ServiceException(wrappedResponse.getException());
			}
			GetClassificationResponseDTO responseDTO = this.mapper.map(wrappedResponse.getResponse(), GetClassificationResponseDTO.class);
			return responseDTO;
		
	}

	@Override
	public GetClassificationResponseDTO getCertificates(GetClassificationRequestDTO getMasterDTO) throws Exception {
		WrappedResponse<GetClassificationResponse> wrappedResponse = masterDAO
				.getCertificates(getMasterDTO.getScholarshipType());
		if(Status.FAILURE.equals(wrappedResponse.getStatus())) {
			throw new ServiceException(wrappedResponse.getException());
		}
		GetClassificationResponseDTO responseDTO = this.mapper.map(wrappedResponse.getResponse(), GetClassificationResponseDTO.class);
		return responseDTO;
	}


}
