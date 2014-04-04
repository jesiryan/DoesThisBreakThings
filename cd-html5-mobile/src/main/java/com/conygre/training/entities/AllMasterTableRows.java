package com.conygre.training.entities;

import java.util.ArrayList;
import java.util.List;

public class AllMasterTableRows {
	
	private List<Failureclass> failureclasses;
	private List<Cause> causes;
	private List<Countryoperator> countryoperators;
	private List<Equipment> equipment;

	public AllMasterTableRows() {

	}

	public List<Failureclass> getFailureclasses() {
		return failureclasses;
	}

	public void setFailureclasses(List<Failureclass> failureclasses) {
		this.failureclasses = failureclasses;
	}

	public List<Cause> getCauses() {
		return causes;
	}

	public void setCauses(List<Cause> causes) {
		this.causes = causes;
	}

	public List<Countryoperator> getCountryoperators() {
		return countryoperators;
	}

	public void setCountryoperators(List<Countryoperator> countryoperators) {
		this.countryoperators = countryoperators;
	}

	public List<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

}
