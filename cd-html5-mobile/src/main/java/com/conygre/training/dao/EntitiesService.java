package com.conygre.training.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.conygre.training.entities.AllMasterTableRows;
import com.conygre.training.entities.Callfailure;
import com.conygre.training.entities.Cause;

@Stateless
@LocalBean
public class EntitiesService {

	public EntitiesService() {
		
	}
	

	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllMasterTableRows(AllMasterTableRows allMasterTableRows, List<Callfailure> callfailures) {
		CauseDAO causeDAO = new CauseDAO();
		CallfailureDAO callfailureDAO = new CallfailureDAO();
		CountryoperatorDAO countryoperatorDAO = new CountryoperatorDAO();
		EquipmentDAO equipmentDAO = new EquipmentDAO();
		FailureclassDAO failureclassDAO = new FailureclassDAO();
		
		

		persistAllCauses(allMasterTableRows);
		
//		countryoperatorDAO.mergeCountryoperators(allMasterTableRows.getCountryoperators());
		equipmentDAO.mergeEquipments(allMasterTableRows.getEquipment());
		failureclassDAO.mergeFailureclasses(allMasterTableRows.getFailureclasses());
		callfailureDAO.mergeCallfailures(callfailures);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllCauses(AllMasterTableRows allMasterTableRows){
		for(Cause cause : allMasterTableRows.getCauses()) {
//			causeDAO.mergeCause(cause);			
		}
	}
	
}
