package com.conygre.training.dao.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.conygre.training.entities.*;
import com.conygre.training.fileimport.*;

public class PersistenceUtil implements Serializable {

	private static final long serialVersionUID = -2227022978224595895L;
	private FailureclassReader failureclassReader;
	private CauseReader causeReader;
	private CountryoperatorReader countryoperatorReader;
	private EquipmentReader equipmentReader;
	private CallfailureReader callfailureReader;
	private AllMasterTableRows allMasterTableRows;

	public static String filePath;// ="test.xls";

	protected static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("sprint1");

	public PersistenceUtil() {
		super();
		failureclassReader = new FailureclassReader();
		causeReader = new CauseReader();
		countryoperatorReader = new CountryoperatorReader();
		equipmentReader = new EquipmentReader();
		callfailureReader = new CallfailureReader();
		allMasterTableRows = new AllMasterTableRows();
	}
	
	public static void persistAll(String fileName) {
		PersistenceUtil.filePath = fileName;
		PersistenceUtil persistenceUtil = new PersistenceUtil();
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		persistenceUtil.persistFailureclasses(entityManager);

		persistenceUtil.persistEventCauses(entityManager);

		persistenceUtil.persistCountryoperators(entityManager);

		persistenceUtil.persistEquipment(entityManager);

		persistenceUtil.persistCallfailures(entityManager);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void persistFailureclasses(EntityManager entityManager) {
		allMasterTableRows.setFailureclasses(failureclassReader.getAllFailureclassRows());
		for (Object row : allMasterTableRows.getFailureclasses()) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
	}

	public void persistEventCauses(EntityManager entityManager) {
		allMasterTableRows.setCauses(causeReader.getAllEventCauseRows());
		for (Object row : allMasterTableRows.getCauses()) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
	}

	public void persistCountryoperators(EntityManager entityManager) {
		allMasterTableRows.setCountryoperators(countryoperatorReader
				.getAllCountryoperatorRows());
		for (Object row : allMasterTableRows.getCountryoperators()) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
	}

	public void persistEquipment(EntityManager entityManager) {
		allMasterTableRows.setEquipment(equipmentReader.getAllEquipmentRows());
		for (Object row : allMasterTableRows.getEquipment()) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
	}

	public void persistCallfailures(EntityManager entityManager) {
		for (Object row : callfailureReader
				.getAllCallfailureRows(allMasterTableRows)) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
	}

	public static void persistAll(List<Object> entityList) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Object entity : entityList) {
			em.persist(entity);
		}
		em.getTransaction().commit();
		em.close();
	}

	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}

	public static Object merge(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	public static EntityManager createEM() {
		return emf.createEntityManager();
	}

	/* ******************************************************************
	 * Methods relating to the entities SQL queries
	 * *****************************************************************
	 */

	// TODO why is this called findCausecode???
	@SuppressWarnings("unchecked")
	public static List<Callfailure> findCauseCode_EventIDByIMSI(String IMSI) {

		EntityManager em = emf.createEntityManager();
		List<Callfailure> callfailures = (List<Callfailure>) em
				.createNamedQuery("Callfailure.findByIMSI")
				.setParameter("IMSI", IMSI).getResultList();
		em.close();

		if (callfailures.size() == 0)
			return null;
		else
			return callfailures;
	}

	@SuppressWarnings("unchecked")
	public static List<Callfailure> findCallByIMSIBetweenDate(String IMSI,
			Date startDateTime, Date endDateTime) {

		EntityManager em = emf.createEntityManager();
		List<Callfailure> callfailures = (List<Callfailure>) em
				.createNamedQuery("Callfailure.findImsiBetween")
				.setParameter("IMSI", IMSI)
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		em.close();

		if (callfailures.size() == 0)
			return null;
		else
			return callfailures;
	}

	@SuppressWarnings("unchecked")
	public static List<Callfailure> findCallfailureByTACInTime(int tac,
			Date startDateTime, Date endDateTime) {

		EntityManager em = emf.createEntityManager();
		List<Callfailure> callfailures = (List<Callfailure>) em
				.createNamedQuery("Callfailure.findByTACInTime")
				.setParameter("TAC", tac)
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		em.close();

		if (callfailures.size() == 0)
			return null;
		else
			return callfailures;
	}

	@SuppressWarnings("unchecked")
	public static List<Equipment> findEquipmentByModel(String model) {
		EntityManager em = emf.createEntityManager();
		List<Equipment> equipment = (List<Equipment>) em
				.createNamedQuery("Equipment.findByModel")
				.setParameter("MODEL", model).getResultList();
		em.close();

		if (equipment.size() == 0)
			return null;
		else
			return equipment;
	}

	@SuppressWarnings("unchecked")
	public static List<List> findCountBetweenTimesTotalDuration(
			Date startDateTime, Date endDateTime) {
		EntityManager em = emf.createEntityManager();
		List<List> us09List = (List<List>) em
				.createNamedQuery(
						"Callfailure.findCountBetweenTimesTotalDuration")
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		em.close();

		if (us09List.size() == 0)
			return null;
		else
			return us09List;
	}

	@SuppressWarnings("unchecked")
	public static List<Callfailure> countCauseCode(int tAC, double cause,
			double event) {
		// eventId and o.causeCode =:causeCode
		EntityManager em = emf.createEntityManager();
		List<Callfailure> causeList = (List<Callfailure>) em
				.createNamedQuery("Callfailure.countByEventAndCause")
				.setParameter("TAC", tAC).setParameter("EVENT", event)
				.setParameter("CAUSE", cause).getResultList();
		em.close();

		if (causeList.size() == 0)
			return null;
		else
			return causeList;
	}

	@SuppressWarnings("unchecked")
	public static List<Callfailure> groupCallfailureByTAC(int tac) {
		EntityManager em = emf.createEntityManager();
		List<Callfailure> callfailures = (List<Callfailure>) em
				.createNamedQuery("Callfailure.groupByTAC")
				.setParameter("TAC", tac).getResultList();
		em.close();
		if (callfailures.size() == 0)
			return null;
		else
			return callfailures;
	}

	public static List<Callfailure> findAllCallfailuresBetween(Date startDateTime,
			Date endDateTime) {
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Callfailure> callfailures = (List<Callfailure>) em
				.createNamedQuery("Callfailure.findAllBetween")
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		em.close();
		if (callfailures.size() == 0)
			return null;
		else
			return callfailures;
	}

	public static List<Callfailure> findAllCallfailures() {
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Callfailure> callfailures = (List<Callfailure>) em
				.createNamedQuery("Callfailure.findAll").getResultList();
		em.close();
		if (callfailures.size() == 0)
			return null;
		else
			return callfailures;
	}
	
	@SuppressWarnings("unchecked")
	public static List<User> findAllUsers(){
		EntityManager em = emf.createEntityManager();
		List<User> users = (List<User>) em.createNamedQuery("User.findAll").getResultList();
		em.close();		
		return users;		
	}
	
	@SuppressWarnings("unchecked")
	public static User findUserByUsername(String username){
		
		EntityManager em = emf.createEntityManager();
		List<User> users = (List<User>) em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
		em.close();
		
		if (users.size() == 0)
			return null;
		else 
			return users.get(0);
	}
}
