package com.conygre.training.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.conygre.training.entities.Callfailure;

@Stateless
@LocalBean
public class CallfailureDAO {

	public CallfailureDAO() {
		
	}
	
	@PersistenceContext(unitName="sprint2")
	private EntityManager em;
	
	public Callfailure getCallfailureById(String id) {
		return em.find(Callfailure.class, id);
	}
	
	public List<Callfailure> getAllCallfailures(){
		Query query = em.createQuery("select o from Callfailure o", Callfailure.class);
		List<Callfailure> callfailures = query.getResultList();
		return callfailures;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addCallfailures(List<Callfailure> callfailures) {
		for (Callfailure callfailure : callfailures) {
			em.persist(callfailure);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void addCallfailure(Callfailure callfailure) {
		em.persist(callfailure);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void mergeCallfailures(List<Callfailure> callfailures) {
		for (Callfailure callfailure : callfailures) {
			em.merge(callfailure);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void mergeCallfailure(Callfailure callfailure) {
		em.merge(callfailure);
	}
	
}
