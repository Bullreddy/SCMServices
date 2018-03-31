package com.scm.services.dao;

import java.util.List;

import com.scm.services.dao.entity.Classification;


public interface ConfigurationDAO {
	
	List getMasterDetails(Classification requestEntity)throws Exception;

}
