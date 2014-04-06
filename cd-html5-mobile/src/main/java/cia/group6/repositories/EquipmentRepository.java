package cia.group6.repositories;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cia.group6.entities.Callfailure;
import cia.group6.entities.Equipment;


@ApplicationScoped
public class EquipmentRepository {

    //@Inject
    @PersistenceContext(unitName="sprint2")
    private EntityManager em;

    public Equipment findByTac(int tAC) {
        return em.find(Equipment.class, tAC);
    }

	public void mergeEquipment(Equipment equipment) {
		em.merge(equipment);
	}
	
    public Equipment findByEquipmentType(String equipmentType) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Equipment> criteria = cb.createQuery(Equipment.class);
        Root<Equipment> equipment = criteria.from(Equipment.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(equipment).where(cb.equal(equipment.get("equipmentType"), equipmentType));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Equipment> findAllOrderedByEquipmentType() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Equipment> criteria = cb.createQuery(Equipment.class);
        Root<Equipment> equipment = criteria.from(Equipment.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(equipment).orderBy(cb.asc(equipment.get("equipmentType")));
        return em.createQuery(criteria).getResultList();
    }
    
	// User Story 8
//	public List<Equipment> findEquipmentByModel(String model) {
//		System.out.println("equip");
//		Query query = em.createQuery("SELECT e FROM Equipment e WHERE e.model=:MODEL").setParameter("MODEL", model);
//		List<Equipment> equipment = query.getResultList();
//		System.out.println("size="+equipment.size());
//		if (equipment.size() == 0)
//			return null;
//		else
//			return equipment;
//	}

}
