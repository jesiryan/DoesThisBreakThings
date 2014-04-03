package com.conygre.training.entities;

import java.util.ArrayList;
import java.util.List;

public class AllMasterTableRows {
	
	private List<Object> failureclasses;
	private List<Object> causes;
	private List<Object> countryoperators;
	private List<Object> equipment;

	public AllMasterTableRows() {

	}

	public List<Object> getFailureclasses() {
		return failureclasses;
	}

	public void setFailureclasses(List<Object> failureclasses) {
		this.failureclasses = failureclasses;
	}

	public List<Object> getCauses() {
		return causes;
	}

	public void setCauses(List<Object> causes) {
		this.causes = causes;
	}

	public List<Object> getCountryoperators() {
		return countryoperators;
	}

	public void setCountryoperators(List<Object> countryoperators) {
		this.countryoperators = countryoperators;
	}

	public List<Object> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Object> equipment) {
		this.equipment = equipment;
	}

}
