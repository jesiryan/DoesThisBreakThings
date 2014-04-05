//package com.conygre.training.services;
//
//import java.util.List;
//
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.inject.Inject;
//
//import com.conygre.training.dao.CallfailureDAO;
//import com.conygre.training.dao.CauseDAO;
//import com.conygre.training.dao.CountryoperatorDAO;
//import com.conygre.training.dao.EquipmentDAO;
//import com.conygre.training.dao.FailureclassDAO;
//import com.conygre.training.entities.AllMasterTableRows;
//import com.conygre.training.entities.Callfailure;
//import com.conygre.training.entities.Cause;
//import com.conygre.training.entities.Countryoperator;
//import com.conygre.training.entities.Equipment;
//import com.conygre.training.entities.Failureclass;
//
//@Stateless
//@LocalBean
//public class EntitiesEJB {
//
//	public EntitiesEJB() {
//		
//	}
//	
//    @Inject
//    private CauseDAO causeDAO;  
//    @Inject
//    private CallfailureDAO callfailureDAO;
//    @Inject
//    private CountryoperatorDAO countryoperatorDAO;   
//    @Inject
//    private EquipmentDAO equipmentDAO;
//    @Inject
//    private FailureclassDAO failureclassDAO;
//	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void persistAllMasterTableRows(AllMasterTableRows allMasterTableRows, List<Callfailure> callfailures) {
//		persistAllCauses(allMasterTableRows);
//		
//	}
//	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void persistAllCauses(AllMasterTableRows allMasterTableRows){
//		for(Cause cause : allMasterTableRows.getCauses()) {
//			causeDAO.mergeCause(cause);			
//		}
//	}
//	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void persistAllCallfailures(List<Callfailure> callfailures){
//		for(Callfailure callfailure : callfailures) {
//			callfailureDAO.mergeCallfailure(callfailure);			
//		}
//	}
//	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void persistAllCountryoperator(AllMasterTableRows allMasterTableRows){
//		for(Countryoperator countryoperator : allMasterTableRows.getCountryoperators()) {
//			countryoperatorDAO.mergeCountryoperator(countryoperator);			
//		}
//	}
//	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void persistAllEquipment(AllMasterTableRows allMasterTableRows){
//		for(Equipment equipment : allMasterTableRows.getEquipment()) {
//			equipmentDAO.mergeEquipment(equipment);			
//		}
//	}
//	
//	@TransactionAttribute(TransactionAttributeType.REQUIRED)
//	public void persistAllFailureclass(AllMasterTableRows allMasterTableRows){
//		for(Failureclass failureclass : allMasterTableRows.getFailureclasses()) {
//			failureclassDAO.mergeFailureclass(failureclass);		
//		}
//	}
//	
//}
