package com.scm.services.dao;

import java.util.List;

import com.scm.services.dao.entity.GetMasterRequestEntity;
import com.scm.services.dao.entity.GetMasterResponseEntity;

public interface ConfigurationDAO {
	
	List getMasterDetails(GetMasterRequestEntity requestEntity)throws Exception;

}
