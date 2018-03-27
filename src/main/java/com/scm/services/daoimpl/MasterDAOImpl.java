package com.scm.services.daoimpl;

import java.util.List;

import com.scm.services.dao.MasterDAO;
import com.scm.services.dao.entity.GetMasterRequestEntity;
import com.scm.services.dao.entity.GetMasterResponseEntity;

public class MasterDAOImpl extends BaseDAOImpl implements MasterDAO {

	@Override
	public List<GetMasterResponseEntity> getMasterDetails(
			GetMasterRequestEntity requestEntity) throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Configuration where master_type=?").list();

	}

}
