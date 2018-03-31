package com.scm.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.services.dao.ConfigurationDAO;
import com.scm.services.dao.entity.GetMasterRequestEntity;
import com.scm.services.dto.GetMasterRequestDTO;
import com.scm.services.exception.ServiceException;
import com.scm.services.serviceinf.ConfigurationService;



@Service
public class ConfigurationServiceImpl implements ConfigurationService{

	@Autowired
	private ConfigurationDAO configurationDAO;
	
	@Override
	public List getConfiguration(GetMasterRequestDTO getMasterDTO)
			throws ServiceException {
		List masterResponse = new ArrayList();
		try{
		masterResponse = configurationDAO.getMasterDetails(new GetMasterRequestEntity());
		}catch(Exception e){
			throw new ServiceException(e);
		}
		return masterResponse;
		
	}

}
