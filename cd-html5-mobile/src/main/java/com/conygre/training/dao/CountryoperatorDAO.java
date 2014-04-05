//package com.conygre.training.dao;
//
//import java.util.List;
//
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import com.conygre.training.dao.jpa.JPA;
//import com.conygre.training.entities.Countryoperator;
//
//@Stateless
////@LocalBean
//@JPA
//public class CountryoperatorDAO {
//
//	public CountryoperatorDAO() {
//		
//	}
//	
//	@PersistenceContext(unitName="sprint2")
//    private EntityManager em;
//	
//	public Countryoperator getCountryoperatorById(String id) {
//		return em.find(Countryoperator.class, id);
//	}
//	
//	public List<Countryoperator> getAllCountryoperators(){
//		Query query = em.createQuery("select o from Countryoperator o", Countryoperator.class);
//		List<Countryoperator> countryoperators = query.getResultList();
//		return countryoperators;
//	}
//	
////	@TransactionAttribute(TransactionAttributeType.REQUIRED)
////	public void addCountryoperators(List<Countryoperator> countryoperators) {
////		for (Countryoperator countryoperator : countryoperators) {
////			em.persist(countryoperator);
////		}
////	}
////	
////	@TransactionAttribute(TransactionAttributeType.REQUIRED)
////	public void addCountryoperator(Countryoperator countryoperator) {
////		em.persist(countryoperator);
////	}
//	
////	@TransactionAttribute(TransactionAttributeType.REQUIRED)
////	public void mergeCountryoperators(List<Countryoperator> countryoperators) {
////		for (Countryoperator countryoperator : countryoperators) {
////			em.merge(countryoperator);
////		}
////	}
//	
////	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void mergeCountryoperator(Countryoperator countryoperator) {
//		em.merge(countryoperator);
//	}
//	
//}
