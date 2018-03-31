package com.scm.services.serviceinf;

import java.util.List;

import com.scm.services.dto.GetMasterRequestDTO;
import com.scm.services.exception.ServiceException;

public interface ConfigurationService {

	List getConfiguration(GetMasterRequestDTO getMasterDTO)throws ServiceException;
}
