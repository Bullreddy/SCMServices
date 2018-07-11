package com.scm.services.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scm.services.common.MapperUtils;

@Transactional
@Repository
public class BaseDAOImpl {
  
  @Autowired
  public MapperUtils mapper;
	
  @PersistenceContext
  private EntityManager em;
  
  public EntityManager getEM() {
    return em;
  }
}