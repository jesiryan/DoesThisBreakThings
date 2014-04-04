package com.conygre.training.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.conygre.training.entities.Failureclass;

@Stateless
@LocalBean
public class FailureclassDAO {

	public FailureclassDAO() {
		
	}
	
	@PersistenceContext(unitName="sprint2")
	private EntityManager em;
	
	public Failureclass getFailureclassesById(String id) {
		return em.find(Failureclass.class, id);
	}
	
	public List<Failureclass> getAllEfailureclasses(){
		Query query = em.createQuery("select o from Failureclasses o", Failureclass.class);
		List<Failureclass> failureclasses = query.getResultList();
		return failureclasses;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addEfailureclasses(List<Failureclass> failureclasses) {
		for (Failureclass failureclass : failureclasses) {
			em.persist(failureclass);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addFailureclasses(Failureclass failureclass) {
		em.persist(failureclass);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void mergeFailureclasses(List<Failureclass> failureclasses) {
		for (Failureclass failureclass : failureclasses) {
			em.merge(failureclass);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void mergeFailureclass(Failureclass failureclass) {
		em.merge(failureclass);
	}
	
}
