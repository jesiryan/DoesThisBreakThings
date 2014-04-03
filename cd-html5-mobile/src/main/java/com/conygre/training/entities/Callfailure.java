package com.conygre.training.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the callfailure database table.
 * 
 */
@NamedQueries({
	 @NamedQuery(name = "Callfailure.findByIMSI", query = "SELECT c FROM Callfailure c WHERE c.iMSI=:IMSI"),	 
	 @NamedQuery(name = "Callfailure.findAll", query = "SELECT c FROM Callfailure c ORDER BY c.iMSI"),	 
	 @NamedQuery(name = "Callfailure.findAllUnordered", query = "SELECT c FROM Callfailure c"),
	 @NamedQuery(name = "Callfailure.findByTAC", query = "SELECT c FROM Callfailure c WHERE c.equipment.tAC=:TAC"), 
	//@NamedQuery(name = "Callfailure.findCallfailuresDurationPerIMSI", query = "SELECT new List(c.iMSI, count(c), sum(c.duration)) FROM Callfailure c WHERE c.dateTime>:startDate AND c.dateTime<:endDate GROUP BY c.iMSI"),
	//@NamedQuery(name = "Callfailure.findDate", query = "SELECT new List(c.iMSI, c.dateTime) FROM Callfailure c WHERE c.dateTime > :startDate"),
	 
	 @NamedQuery(name = "Callfailure.findCountBetweenTimesTotalDuration", query = "SELECT new List(c.iMSI, count(*), SUM(c.duration)) FROM Callfailure c WHERE c.dateTime BETWEEN :startDateTime AND :endDateTime GROUP BY iMSI"),
	
	//@NamedQuery(name = "Callfailure.groupByTAC", query = "SELECT c FROM Callfailure c WHERE c.equipment.tAC=:TAC GROUP BY c.cause.eventId, c.cause.causeCode"),	 
	//@NamedQuery(name = "Callfailure.countByEventAndCause", query = "SELECT count(c) FROM Callfailure c WHERE c.equipment.tAC=:TAC AND c.cause.eventId=:EVENT AND o.cause.causeCode=:CAUSE"),
	 @NamedQuery(name = "Callfailure.findAllBetween", query = "SELECT c FROM Callfailure c WHERE c.dateTime BETWEEN :startDateTime AND :endDateTime ORDER BY c.iMSI"),	
	 @NamedQuery(name = "Callfailure.findImsiBetween", query = "SELECT c FROM Callfailure c WHERE c.dateTime BETWEEN :startDateTime AND :endDateTime AND c.iMSI=:IMSI"),	
	 @NamedQuery(name = "Callfailure.findCountOfOccurancesForGivenIMSI", query = "SELECT count(c) FROM Callfailure c WHERE c.iMSI=:IMSI"),
	 @NamedQuery(name = "Callfailure.findByTACInTime", query = "SELECT c FROM Callfailure c WHERE c.equipment.tAC=:TAC and c.dateTime BETWEEN :startDateTime and :endDateTime"),
	//@NamedQuery(name = "Callfailure.findAffectedIMSIsGivenCauseClass", query = "SELECT c FROM Callfailure c WHERE c.failureclass.failureclass=:causeClass GROUP BY c.iMSI"),
	})

	@Entity
public class Callfailure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int baseDataID;

	private int cellId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	private int duration;

	private String hier3;

	private String hier32;

	private String hier321;

	private String iMSI;

	private String nEVersion;

	//bi-directional many-to-one association to Equipment
	@ManyToOne(fetch=FetchType.EAGER)
	private Equipment equipment;

	//bi-directional many-to-one association to Failureclass
	@ManyToOne(fetch=FetchType.EAGER)
	private Failureclass failureclass;

	//bi-directional many-to-one association to Countryoperator
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="countryoperator_mCC", referencedColumnName="mCC"),
		@JoinColumn(name="countryoperator_mNC", referencedColumnName="mNC")
		})
	private Countryoperator countryoperator;

	//bi-directional many-to-one association to Cause
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="cause_causeCode", referencedColumnName="causeCode"),
		@JoinColumn(name="cause_eventId", referencedColumnName="eventId")
		})
	private Cause cause;

	public Callfailure() {
	}

	public Callfailure(int baseDataID, int cellId, Date dateTime, int duration, String hier3, String hier32, String hier321, String iMSI, String nEVersion, Equipment equipment, Failureclass failureclass, Countryoperator countryoperator, Cause cause){
		this.baseDataID = baseDataID;
		this.cellId = cellId;
		this.dateTime = dateTime;
		this.duration = duration;
		this.hier3 = hier3;
		this.hier32 = hier32;
		this.hier321 = hier321;
		this.iMSI = iMSI;
		this.nEVersion = nEVersion;
		this.equipment = equipment;
		this.failureclass = failureclass;
		this.countryoperator = countryoperator;
		this.cause = cause;
	}
	
	private Callfailure(Builder builder) {
		this.dateTime = builder.dateTime;
		this.cause = builder.cause;
		this.failureclass = builder.failureclass;
		this.equipment = builder.equipment;
		this.countryoperator = builder.countryoperator;
		this.cellId = builder.cellId;
		this.duration = builder.duration;
		this.nEVersion = builder.nEVersion;
		this.iMSI = builder.iMSI;
		this.hier3 = builder.hier3;
		this.hier32 = builder.hier32;
		this.hier321 = builder.hier321;
	}

	public static class Builder {
		private Date dateTime;
		private Cause cause;
		private Failureclass failureclass;
		private Equipment equipment;
		private Countryoperator countryoperator;
		private int cellId;
		private int duration;
		private String nEVersion;
		private String iMSI;
		private String hier3;
		private String hier32;
		private String hier321;

		public Builder() {
			super();
		}

		public Builder dateTime(Date dateTime) {
			this.dateTime = dateTime;
			return this;
		}

		public Builder cause(Cause cause) {
			this.cause = cause;
			return this;
		}

		public Builder failureclass(Failureclass failureclass) {
			this.failureclass = failureclass;
			return this;
		}

		public Builder equipment(Equipment equipment) {
			this.equipment = equipment;
			return this;
		}

		public Builder countryoperator(Countryoperator countryoperator) {
			this.countryoperator = countryoperator;
			return this;
		}

		public Builder cellId(int cellId) {
			this.cellId = cellId;
			return this;
		}

		public Builder duration(int duration) {
			this.duration = duration;
			return this;
		}

		public Builder nEVersion(String nEVersion) {
			this.nEVersion = nEVersion;
			return this;
		}

		public Builder iMSI(String iMSI) {
			this.iMSI = iMSI;
			return this;
		}

		public Builder hier3(String hier3) {
			this.hier3 = hier3;
			return this;
		}

		public Builder hier32(String hier32) {
			this.hier32 = hier32;
			return this;
		}

		public Builder hier321(String hier321) {
			this.hier321 = hier321;
			return this;
		}

		public Callfailure build() {
			return new Callfailure(this);
		}

	}
	
	/* Getters and Setters */
	
	public int getBaseDataID() {
		return this.baseDataID;
	}

	public void setBaseDataID(int baseDataID) {
		this.baseDataID = baseDataID;
	}

	public int getCellId() {
		return this.cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getHier3() {
		return this.hier3;
	}

	public void setHier3(String hier3) {
		this.hier3 = hier3;
	}

	public String getHier32() {
		return this.hier32;
	}

	public void setHier32(String hier32) {
		this.hier32 = hier32;
	}

	public String getHier321() {
		return this.hier321;
	}

	public void setHier321(String hier321) {
		this.hier321 = hier321;
	}

	public String getIMSI() {
		return this.iMSI;
	}

	public void setIMSI(String iMSI) {
		this.iMSI = iMSI;
	}

	public String getNEVersion() {
		return this.nEVersion;
	}

	public void setNEVersion(String nEVersion) {
		this.nEVersion = nEVersion;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Failureclass getFailureclass() {
		return this.failureclass;
	}

	public void setFailureclass(Failureclass failureclass) {
		this.failureclass = failureclass;
	}

	public Countryoperator getCountryoperator() {
		return this.countryoperator;
	}

	public void setCountryoperator(Countryoperator countryoperator) {
		this.countryoperator = countryoperator;
	}

	public Cause getCause() {
		return this.cause;
	}

	public void setCause(Cause cause) {
		this.cause = cause;
	}

}