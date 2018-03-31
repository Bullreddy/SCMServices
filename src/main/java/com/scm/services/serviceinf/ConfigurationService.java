package com.scm.services.serviceinf;

import java.util.List;

import com.scm.services.dto.GetClassificationRequestDTO;
import com.scm.services.exception.ServiceException;

public interface ConfigurationService {

	List getConfiguration(GetClassificationRequestDTO getClassificationRequestDTO)throws ServiceException;
}
