package com.scm.services.serviceinf;

import java.util.List;

import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.dto.GetClassificationRequestDTO;
import com.scm.services.dto.GetClassificationResponseDTO;

public interface ClassificationService {

	GetClassificationResponseDTO getClassificationDetails(GetClassificationRequestDTO getMasterDTO) throws Exception;
	
}
