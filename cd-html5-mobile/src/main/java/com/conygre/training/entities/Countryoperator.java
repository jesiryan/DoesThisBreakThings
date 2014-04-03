package com.conygre.training.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the countryoperator database table.
 * 
 */
@Entity
@NamedQuery(name="Countryoperator.findAll", query="SELECT c FROM Countryoperator c")
public class Countryoperator implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CountryoperatorPK id;

	private String country;

	private String operator;

	//bi-directional many-to-one association to Callfailure
	@JsonIgnore
	@OneToMany(mappedBy="countryoperator")
	private List<Callfailure> callfailures;

	public Countryoperator() {
	}
	
	public Countryoperator(CountryoperatorPK id, String country, String operator){
		this.id = id;
		this.country = country;
		this.operator = operator;
	}
	
	private Countryoperator(Builder builder) {
		this.id.setMCC(builder.mCC);
		this.id.setMNC(builder.mNC);
		this.country = builder.country;
		this.operator = builder.operator;
	}

	public static class Builder {
		private double mCC;
		private double mNC;
		private String country;
		private String operator;

		public Builder() {
			super();
		}

		public Builder mCC(double mCC) {
			this.mCC = mCC;
			return this;
		}

		public Builder mNC(double mNC) {
			this.mNC = mNC;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder operator(String operator) {
			this.operator = operator;
			return this;
		}

		public Countryoperator build() {
			return new Countryoperator(this);
		}

	}
	
	public boolean isMCCEqual(int mCC) {
		return this.getId().getMCC() == mCC;
	}
	
	public boolean isMNCEqual(int mNC) {
		return this.getId().getMNC() == mNC;
	}
	
	/* Getters and Setters */
	
	public CountryoperatorPK getId() {
		return this.id;
	}

	public void setId(CountryoperatorPK id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<Callfailure> getCallfailures() {
		return this.callfailures;
	}

	public void setCallfailures(List<Callfailure> callfailures) {
		this.callfailures = callfailures;
	}

	public Callfailure addCallfailure(Callfailure callfailure) {
		getCallfailures().add(callfailure);
		callfailure.setCountryoperator(this);

		return callfailure;
	}

	public Callfailure removeCallfailure(Callfailure callfailure) {
		getCallfailures().remove(callfailure);
		callfailure.setCountryoperator(null);

		return callfailure;
	}

}