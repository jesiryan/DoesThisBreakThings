package com.conygre.training.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the cause database table.
 * 
 */
@Entity
@NamedQuery(name="Cause.findAll", query="SELECT c FROM Cause c")
public class Cause implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CausePK id;

	private String description;

	//bi-directional many-to-one association to Callfailure
	@JsonIgnore
	@OneToMany(mappedBy="cause")
	private List<Callfailure> callfailures;

	public Cause() {
	}
	
	public Cause(CausePK id, String description, List<Callfailure> callfailures){
		this.id = id;
		this.description = description;
		this.callfailures = callfailures;
	}
	
	private Cause(Builder builder) {
		this.id.setEventId(builder.eventId);
		this.id.setCauseCode(builder.causeCode);
		this.description = builder.description;
	}

	public static class Builder {
		private double eventId;
		private double causeCode;
		private String description;

		public Builder() {
			super();
		}

		public Builder eventId(double eventId) {
			this.eventId = eventId;
			return this;
		}

		public Builder causeCode(double causeCode) {
			this.causeCode = causeCode;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Cause build() {
			return new Cause(this);
		}
		
	}
	
	public boolean isEventIdEqual(double eventId) {
		return this.id.getEventId() == eventId;
	}
	
	public boolean isCauseCodeEqual(double causeCode) {
		return this.id.getCauseCode() == causeCode;
	}
	
	/* Getters and Setters */

	public CausePK getId() {
		return this.id;
	}

	public void setId(CausePK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Callfailure> getCallfailures() {
		return this.callfailures;
	}

	public void setCallfailures(List<Callfailure> callfailures) {
		this.callfailures = callfailures;
	}

	public Callfailure addCallfailure(Callfailure callfailure) {
		getCallfailures().add(callfailure);
		callfailure.setCause(this);

		return callfailure;
	}

	public Callfailure removeCallfailure(Callfailure callfailure) {
		getCallfailures().remove(callfailure);
		callfailure.setCause(null);

		return callfailure;
	}

}