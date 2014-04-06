package cia.group6.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cia.group6.dao.jpa.JPA;
import cia.group6.entities.Countryoperator;

//@Stateless
@Stateful
@LocalBean
@JPA
public class CountryoperatorDAO {

	public CountryoperatorDAO() {
		
	}
	
	@PersistenceContext(unitName="sprint2")
    private EntityManager em;
	
	public Countryoperator getCountryoperatorById(String id) {
		return em.find(Countryoperator.class, id);
	}
	
	public List<Countryoperator> getAllCountryoperators(){
		Query query = em.createQuery("select o from Countryoperator o", Countryoperator.class);
		List<Countryoperator> countryoperators = query.getResultList();
		return countryoperators;
	}
	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void addCountryoperators(List<Countryoperator> countryoperators) {
//		for (Countryoperator countryoperator : countryoperators) {
//			em.persist(countryoperator);
//		}
//	}
//	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void addCountryoperator(Countryoperator countryoperator) {
//		em.persist(countryoperator);
//	}
	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void mergeCountryoperators(List<Countryoperator> countryoperators) {
//		for (Countryoperator countryoperator : countryoperators) {
//			em.merge(countryoperator);
//		}
//	}
	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void mergeCountryoperator(Countryoperator countryoperator) {
		em.merge(countryoperator);
	}
	
}
