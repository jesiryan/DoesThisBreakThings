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

	public EntitiesEJB() {
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllMasterTableRows(AllMasterTableRows allMasterTableRows, List<Callfailure> callfailures) {
		persistAllCauses(allMasterTableRows);
		persistAllCountryoperator(allMasterTableRows);
		persistAllFailureclass(allMasterTableRows);
		persistAllEquipment(allMasterTableRows);
		persistAllCallfailures(callfailures);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllFailureclass(AllMasterTableRows allMasterTableRows){
		int i = 0;
		for(Failureclass failureclass : allMasterTableRows.getFailureclasses()) {
			System.out.println(failureclass.getFailureclass() + " " + failureclass.getDescription());
			failureclassRepository.mergeFailureclass(failureclass);		
			i++;
		}
		System.out.println("Persisted FailureClasses = "+i);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllCauses(AllMasterTableRows allMasterTableRows){
		int i = 0;
		for(Cause cause : allMasterTableRows.getCauses()) {
			causeRepository.mergeCause(cause);			
			i++;
		}
		System.out.println("Persisted Causes = "+i);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllCountryoperator(AllMasterTableRows allMasterTableRows){
		int i = 0;
		for(Countryoperator countryoperator : allMasterTableRows.getCountryoperators()) {
			countryoperatorRepository.mergeCountryoperator(countryoperator);			
			i++;
		}
		System.out.println("Persisted CountryOperators= "+i);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllEquipment(AllMasterTableRows allMasterTableRows){
		int i = 0;
		for(Equipment equipment : allMasterTableRows.getEquipment()) {
			equipmentRepository.mergeEquipment(equipment);			
			i++;
		}
		System.out.println("Persisted Equipments = "+i);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persistAllCallfailures(List<Callfailure> callfailures){
		int i = 0;
		for(Callfailure callfailure : callfailures) {
			callfailureRepository.mergeCallfailure(callfailure);			
			i++;
		}
		System.out.println("Persisted Callfailures = "+i);
	}
	
}