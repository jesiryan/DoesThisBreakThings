package cia.group6.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cia.group6.entities.Cause;

@ApplicationScoped
public class CauseRepository {

    //@Inject
    @PersistenceContext(unitName="sprint2")
    private EntityManager em;

    public Cause findById(int id) {
        return em.find(Cause.class, id);
    }
	
    public void mergeCause(Cause cause) {
		em.merge(cause);
	}
	
    public Cause findByDescription(String description) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cause> criteria = cb.createQuery(Cause.class);
        Root<Cause> cause = criteria.from(Cause.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(cause).where(cb.equal(cause.get("description"), description));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Cause> findAllOrderedByDescription() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cause> criteria = cb.createQuery(Cause.class);
        Root<Cause> cause = criteria.from(Cause.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(cause).orderBy(cb.asc(cause.get("description")));
        return em.createQuery(criteria).getResultList();
    }
    
    
}
