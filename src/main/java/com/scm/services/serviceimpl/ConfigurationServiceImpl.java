package com.scm.services.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.services.dao.ConfigurationDAO;
import com.scm.services.dto.GetClassificationRequestDTO;
import com.scm.services.exception.ServiceException;
import com.scm.services.serviceinf.ConfigurationService;



@Service
public class ConfigurationServiceImpl implements ConfigurationService{

	@Autowired
	private ConfigurationDAO configurationDAO;
	
	@Override
	public List getConfiguration(GetClassificationRequestDTO getClassificationRequestDTO)
			throws ServiceException {
		List masterResponse = new ArrayList();
		try{
		masterResponse = configurationDAO.getMasterDetails(null);
		}catch(Exception e){
			throw new ServiceException(e);
		}
		return masterResponse;
		
	}

}
