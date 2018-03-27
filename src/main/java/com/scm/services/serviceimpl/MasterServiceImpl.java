package com.scm.services.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.services.dao.MasterDAO;
import com.scm.services.dao.entity.GetMasterRequestEntity;
import com.scm.services.dao.entity.GetMasterResponseEntity;
import com.scm.services.dto.GetMasterRequestDTO;
import com.scm.services.exception.ServiceException;
import com.scm.services.serviceinf.MasterService;

@Service
public class MasterServiceImpl implements MasterService{

	@Autowired
	private MasterDAO masterDAO;
	
	@Override
	public void getMaster(GetMasterRequestDTO getMasterDTO)
			throws ServiceException {
		try{
		List<GetMasterResponseEntity> masterResponse = masterDAO.getMasterDetails(new GetMasterRequestEntity());
		}catch(Exception e){
			throw new ServiceException(e);
		}
		
	}

}
