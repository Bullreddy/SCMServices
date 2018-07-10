package com.scm.services.daoimpl;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.bulls.scm.common.vo.StudentVO;
import com.bulls.scm.common.vo.UserVO;
import com.scm.services.common.MapperUtils;
import com.scm.services.config.JwtAuthenticationEntryPoint;
import com.scm.services.dao.DataSoureConfigDAO;
import com.scm.services.dao.StudentDAO;
import com.scm.services.dao.UserDAO;
import com.scm.services.dao.entity.Admission;

import com.scm.services.dao.entity.Datasourceconfig;
import com.scm.services.dao.entity.User;
import com.scm.services.dto.JwtUser;
import com.scm.services.serviceinf.UserService;
@Repository
public class DataSoureConfigDAOImpl  extends BaseDAOImpl implements DataSoureConfigDAO{

	@Autowired
	private MapperUtils mapper;
	private final static Logger LOGGER = Logger.getLogger(DataSoureConfigDAOImpl.class);
	
	public DataSoureConfigDAOImpl() {
		
	}

	@Override
	public Datasourceconfig getDatasourceByName(String name) {
		LOGGER.info("result from Datasource");
		LOGGER.info(name);
		//masterEntityManager
		Query query = getEM().createNamedQuery("Datasourceconfig.findOne");
			query.setParameter("name", name);
		
		
		try {
			Datasourceconfig config =  (Datasourceconfig) query.getSingleResult();
			return config;
		}catch(NoResultException err) {
			 
		return null;	
		}
		
	}

	@Override
	public List<Datasourceconfig> getAllDataSource() {
		// TODO Auto-generated method stub
		
		List<Datasourceconfig> configs=getEM().createNamedQuery("Datasourceconfig.findAll").getResultList();
		return configs;
	}
}
