package cia.group6.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import cia.group6.entities.AllMasterTableRows;
import cia.group6.entities.Callfailure;
import cia.group6.entities.Cause;
import cia.group6.entities.Countryoperator;
import cia.group6.entities.Equipment;
import cia.group6.entities.Failureclass;
import cia.group6.repositories.CallfailureRepository;
import cia.group6.repositories.CauseRepository;
import cia.group6.repositories.CountryoperatorRepository;
import cia.group6.repositories.EquipmentRepository;
import cia.group6.repositories.FailureclassRepository;


@Stateless
@LocalBean
public class EntitiesEJB {

	public EntitiesEJB() {
		
	}
	
    @Inject
    private CauseRepository  causeRepository;  
    @Inject
    private CallfailureRepository  callfailureRepository;
    @Inject
    private CountryoperatorRepository countryoperatorRepository;   
    @Inject
    private EquipmentRepository equipmentRepository;
    @Inject
    private FailureclassRepository failureclassRepository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllMasterTableRows(AllMasterTableRows allMasterTableRows, List<Callfailure> callfailures) {
		persistAllCauses(allMasterTableRows);
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllCauses(AllMasterTableRows allMasterTableRows){
		for(Cause cause : allMasterTableRows.getCauses()) {
			causeRepository.mergeCause(cause);			
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllCallfailures(List<Callfailure> callfailures){
		for(Callfailure callfailure : callfailures) {
			callfailureRepository.mergeCallfailure(callfailure);			
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllCountryoperator(AllMasterTableRows allMasterTableRows){
		for(Countryoperator countryoperator : allMasterTableRows.getCountryoperators()) {
			countryoperatorRepository.mergeCountryoperator(countryoperator);			
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllEquipment(AllMasterTableRows allMasterTableRows){
		for(Equipment equipment : allMasterTableRows.getEquipment()) {
			equipmentRepository.mergeEquipment(equipment);			
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllFailureclass(AllMasterTableRows allMasterTableRows){
		for(Failureclass failureclass : allMasterTableRows.getFailureclasses()) {
			failureclassRepository.mergeFailureclass(failureclass);		
		}
	}
	
}
