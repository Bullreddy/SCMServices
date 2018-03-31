package com.scm.services.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.scm.services.dao.ConfigurationDAO;
import com.scm.services.dao.entity.Classification;

@Repository
public class ConfigurationDAOImpl extends BaseDAOImpl implements ConfigurationDAO {

	@Override
	public List getMasterDetails(
			Classification requestEntity) throws Exception {
		List result=null;
		try {
		EntityManager em = getEM();
		System.out.println("em "+em);
		Query query = em.createQuery("from Configuration");
		result = query.getResultList();
		System.out.println(" result "+result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
