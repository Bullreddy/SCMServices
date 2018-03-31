package com.scm.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.services.common.MapperUtils;
import com.scm.services.common.WrappedResponse;
import com.scm.services.dao.MasterDAO;
import com.scm.services.dao.entity.Classification;
import com.scm.services.dao.entity.GetClassificationResponse;
import com.scm.services.dto.GetClassificationRequestDTO;
import com.scm.services.dto.GetClassificationResponseDTO;
import com.scm.services.exception.ServiceException;
import com.scm.services.serviceinf.MasterService;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private MasterDAO masterDAO;
	
	@Autowired
	private MapperUtils mapper;

	@Override
	public GetClassificationResponseDTO getClassificationDetails(GetClassificationRequestDTO getMasterDTO) throws ServiceException {
		try {
			WrappedResponse<GetClassificationResponse> wrappedResponse = masterDAO
					.getClassificationDetails(new Classification());
			if(wrappedResponse.getException()!=null) {
				throw new ServiceException(wrappedResponse.getException());
			}
			GetClassificationResponseDTO responseDTO = this.mapper.map(wrappedResponse.getResponse(), GetClassificationResponseDTO.class);
			return responseDTO;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
