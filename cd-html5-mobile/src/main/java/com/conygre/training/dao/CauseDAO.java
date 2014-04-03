package com.conygre.training.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.conygre.training.entities.Cause;

@Stateless
@LocalBean
public class CauseDAO {

	public CauseDAO() {
		
	}
	
	@PersistenceContext
	private EntityManager em;
	
	public Cause getCauseById(String id) {
		return em.find(Cause.class, id);
	}
	
	public List<Cause> getAllCauses(){
		Query query = em.createQuery("select o from Cause o", Cause.class);
		List<Cause> causes = query.getResultList();
		return causes;
	}
	
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCauses(List<Cause> causes) {
        for (Cause cause : causes) {
            em.persist(cause);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void mergeCauses(List<Cause> causes) {
        for (Cause cause : causes) {
            em.merge(cause);
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void mergeCause(Cause cause) {
        em.merge(cause);
    }
    
}
