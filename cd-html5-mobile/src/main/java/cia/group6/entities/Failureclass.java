package cia.group6.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the failureclass database table.
 * 
 */
@Entity
@XmlRootElement
//@NamedQuery(name="Failureclass.findAll", query="SELECT f FROM Failureclass f")
@Table(name = "Failureclass", uniqueConstraints = @UniqueConstraint(columnNames = "failureclass"))
public class Failureclass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int failureclass;

	private String description;

	//bi-directional many-to-one association to Callfailure
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy="failureclass")
	private List<Callfailure> callfailures;

	public Failureclass() {
	}
	
	public Failureclass(int failureclass, String description){
		this.failureclass = failureclass;
		this.description = description;
	}
	
	public Failureclass(int failureclass, String description, List<Callfailure> callfailures){
		this.failureclass = failureclass;
		this.description = description;
		this.callfailures = callfailures;
	}
	
	private Failureclass(Builder builder) {
		this.failureclass = builder.failureclass;
		this.description = builder.description;
	}

	public static class Builder {
		private int failureclass;
		private String description;

		public Builder() {
			super();
		}

		public Builder failureclass(int failureclass) {
			this.failureclass = failureclass;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Failureclass build() {
			return new Failureclass(this);
		}
		
	}
	
	public boolean isFailureclassEqual(int failureclass) {
		return this.getFailureclass() == failureclass;
	}
	
	/* Getters and Setters */
	
	public int getFailureclass() {
		return this.failureclass;
	}

	public void setFailureclass(int failureclass) {
		this.failureclass = failureclass;
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
		callfailure.setFailureclass(this);

		return callfailure;
	}

	public Callfailure removeCallfailure(Callfailure callfailure) {
		getCallfailures().remove(callfailure);
		callfailure.setFailureclass(null);

		return callfailure;
	}

}