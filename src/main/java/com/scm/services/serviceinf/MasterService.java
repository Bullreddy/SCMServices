package com.scm.services.serviceinf;

import com.scm.services.dto.GetMasterRequestDTO;
import com.scm.services.exception.ServiceException;

public interface MasterService {

	void getMaster(GetMasterRequestDTO getMasterDTO)throws ServiceException;
}
