package com.scm.services.dao;

import java.util.List;

import com.bulls.scm.common.vo.StudentVO;
import com.scm.services.dao.entity.Datasourceconfig;

public interface DataSoureConfigDAO {


	public Datasourceconfig getDatasourceByName(String name);
	
	List<Datasourceconfig> getAllDataSource();
}
