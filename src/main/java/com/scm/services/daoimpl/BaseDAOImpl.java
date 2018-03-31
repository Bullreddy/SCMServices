package com.scm.services.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BaseDAOImpl {
  
  @PersistenceContext
  private EntityManager em;
  
  public EntityManager getEM() {
    return em;
  }
}