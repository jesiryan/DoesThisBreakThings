package com.conygre.training.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.conygre.training.entities.AllMasterTableRows;
import com.conygre.training.entities.Callfailure;

@Stateless
@LocalBean
public class EntitiesDAO {

	public EntitiesDAO() {
		
	}
	
	@PersistenceContext(unitName="sprint2")
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllMasterTableRows(AllMasterTableRows allMasterTableRows, List<Callfailure> callfailures) {
		CauseDAO causeDAO = new CauseDAO();
		CallfailureDAO callfailureDAO = new CallfailureDAO();
		CountryoperatorDAO countryoperatorDAO = new CountryoperatorDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		FailureclassDAO failureclassDAO = new FailureclassDAO();
		
		causeDAO.mergeCauses(allMasterTableRows.getCauses());
		countryoperatorDAO.mergeCountryoperators(allMasterTableRows.getCountryoperators());
		equipmentDAO.mergeEquipments(allMasterTableRows.getEquipment());
		failureclassDAO.mergeFailureclasses(allMasterTableRows.getFailureclasses());
		callfailureDAO.mergeCallfailures(callfailures);
	}
	
}
