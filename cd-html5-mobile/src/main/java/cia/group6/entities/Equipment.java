package cia.group6.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the equipment database table.
 * 
 */
@NamedQueries({
	 @NamedQuery(name = "Equipment.findAll", query="SELECT e FROM Equipment e"),
	 @NamedQuery(name = "Equipment.findByTac", query = "SELECT e FROM Equipment e WHERE e.tAC=:Tac"),
	 @NamedQuery(name = "Equipment.findByModel", query = "SELECT e FROM Equipment e WHERE e.model=:MODEL"),
})

@Entity
public class Equipment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int tAC;

	private String accessCapability;

	private String equipmentType;

	private String inputMode;

	private String manufacturer;

	private String marketingName;

	private String model;

	private String operatingSystem;

	private String vendorName;

	//bi-directional many-to-one association to Callfailure
	@JsonIgnore
	@OneToMany(mappedBy="equipment")
	private List<Callfailure> callfailures;

	public Equipment() {
	}
	
	public Equipment(int tAC, String accessCapability, String equipmentType, String inputMode, String manufacturer, String marketingName, String model, String operatingSystem, String vendorName, List<Callfailure> callfailures){
		this.tAC = tAC;
		this.accessCapability = accessCapability;
		this.equipmentType = equipmentType;
		this.inputMode = inputMode;
		this.manufacturer = manufacturer;
		this.marketingName = marketingName; 
		this.model = model;
 		this.operatingSystem = operatingSystem; 
 		this.vendorName = vendorName;
 		this.callfailures = callfailures;
	}
	
	private Equipment(Builder builder) {
		this.tAC = builder.tAC;
		this.marketingName = builder.marketingName;
		this.manufacturer = builder.manufacturer;
		this.accessCapability = builder.accessCapability;
		this.model = builder.model;
		this.vendorName = builder.vendorName;
		this.equipmentType = builder.equipmentType;
		this.operatingSystem = builder.operatingSystem;
		this.inputMode = builder.inputMode;
	}
	
	public static class Builder {
		private int tAC;
		private String marketingName;
		private String manufacturer;
		private String accessCapability;
		private String model;
		private String vendorName;
		private String equipmentType;
		private String operatingSystem;
		private String inputMode;

		public Builder() {
			super();
		}

		public Builder tAC(int tAC) {
			this.tAC = tAC;
			return this;
		}

		public Builder marketingName(String marketingName) {
			this.marketingName = marketingName;
			return this;
		}

		public Builder manufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}

		public Builder accessCapability(String accessCapability) {
			this.accessCapability = accessCapability;
			return this;
		}

		public Builder model(String model) {
			this.model = model;
			return this;
		}

		public Builder vendorName(String vendorName) {
			this.vendorName = vendorName;
			return this;
		}

		public Builder equipmentType(String equipmentType) {
			this.equipmentType = equipmentType;
			return this;
		}

		public Builder operatingSystem(String operatingSystem) {
			this.operatingSystem = operatingSystem;
			return this;
		}

		public Builder inputMode(String inputMode) {
			this.inputMode = inputMode;
			return this;
		}

		public Equipment build() {
			return new Equipment(this);
		}

	}
	
	public boolean isTACEqual(int tAC) {
		return this.tAC == tAC;
	}

	
	/* Getters and Setters */
	
	public int getTAC() {
		return this.tAC;
	}

	public void setTAC(int tAC) {
		this.tAC = tAC;
	}

	public String getAccessCapability() {
		return this.accessCapability;
	}

	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public String getEquipmentType() {
		return this.equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getInputMode() {
		return this.inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getMarketingName() {
		return this.marketingName;
	}

	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOperatingSystem() {
		return this.operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public List<Callfailure> getCallfailures() {
		return this.callfailures;
	}

	public void setCallfailures(List<Callfailure> callfailures) {
		this.callfailures = callfailures;
	}

	public Callfailure addCallfailure(Callfailure callfailure) {
		getCallfailures().add(callfailure);
		callfailure.setEquipment(this);

		return callfailure;
	}

	public Callfailure removeCallfailure(Callfailure callfailure) {
		getCallfailures().remove(callfailure);
		callfailure.setEquipment(null);

		return callfailure;
	}

}