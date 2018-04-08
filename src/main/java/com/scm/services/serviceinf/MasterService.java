package com.scm.services.serviceinf;

import com.scm.services.dto.GetClassificationRequestDTO;
import com.scm.services.dto.GetClassificationResponseDTO;
import com.scm.services.exception.ServiceException;

public interface MasterService {

	GetClassificationResponseDTO getClassificationDetails(GetClassificationRequestDTO getMasterDTO) throws Exception;
}
