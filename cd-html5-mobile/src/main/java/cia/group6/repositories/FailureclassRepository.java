package cia.group6.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cia.group6.entities.Failureclass;

@ApplicationScoped
public class FailureclassRepository {

    //@Inject
    @PersistenceContext(unitName="sprint2")
    private EntityManager em;

    public Failureclass findById(int id) {
        return em.find(Failureclass.class, id);
    }

	public void mergeFailureclass(Failureclass failureclass) {
		em.merge(failureclass);
	}
    
    public Failureclass findByDescription(String description) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Failureclass> criteria = cb.createQuery(Failureclass.class);
        Root<Failureclass> failureclass = criteria.from(Failureclass.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(failureclass).where(cb.equal(failureclass.get("description"), description));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Failureclass> findAllOrderedByDescription() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Failureclass> criteria = cb.createQuery(Failureclass.class);
        Root<Failureclass> failureclass = criteria.from(Failureclass.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(failureclass).orderBy(cb.asc(failureclass.get("description")));
        return em.createQuery(criteria).getResultList();
    }
}
