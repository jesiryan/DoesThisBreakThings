package cia.group6.dao;
//package com.conygre.training.dao;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import com.conygre.training.dao.jpa.JPA;
//import com.conygre.training.entities.Cause;
//
//@Stateless
////@LocalBean
////@ApplicationScoped
//@JPA
//public class CauseDAO implements Serializable{
//
//	public CauseDAO() {
//		
//	}
//	
//	@PersistenceContext(unitName="sprint2")
//    private EntityManager em;
//	
//	public Cause getCauseById(String id) {
//		return em.find(Cause.class, id);
//	}
//	
//	public List<Cause> getAllCauses(){
//		Query query = em.createQuery("select o from Cause o", Cause.class);
//		List<Cause> causes = query.getResultList();
//		return causes;
//	}
//	
////	@TransactionAttribute(TransactionAttributeType.REQUIRED)
////	public void addCauses(List<Cause> causes) {
////		for (Cause cause : causes) {
////			em.persist(cause);
////		}
////	}
////	
////	@TransactionAttribute(TransactionAttributeType.REQUIRED)
////	public void addCause(Cause cause) {
////		em.persist(cause);
////	}
////	
////	@TransactionAttribute(TransactionAttributeType.REQUIRED)
////	public void mergeCauses(List<Cause> causes) {
////		Cause findCause = null;
////		for (Cause cause : causes) {
////			System.out.println("Cause description before merge: " + cause.getDescription());
//////			findCause = em.find(Cause.class, cause);
//////			if(findCause != null){
//////				em.persist(cause);
//////			}
////			em.persist(cause);
//////			em.merge(cause);
////			System.out.println("Cause description AFTER merge: " + cause.getDescription());
////		}
////	}
//	
////	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void mergeCause(Cause cause) {
//		em.merge(cause);
//	}
//	
//}
