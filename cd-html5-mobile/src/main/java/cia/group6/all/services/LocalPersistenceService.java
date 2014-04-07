package cia.group6.all.services;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cia.group6.fileimport.CallfailureReader;
import cia.group6.fileimport.CauseReader;
import cia.group6.fileimport.CountryoperatorReader;
import cia.group6.fileimport.EquipmentReader;
import cia.group6.fileimport.FailureclassReader;
import cia.group6.fileimport.FileReader;
import cia.group6.services.EntitiesEJB;
import cia.group6.story.structures.UserStory05Structure;

@Path("/persist")
@RequestScoped
@Stateful
public class LocalPersistenceService {

	@Inject
	private EntitiesEJB entEJB;
	private FileReader lowLevelReader;
	private FailureclassReader failureClassReader;
	private CauseReader causeReader;
	private CountryoperatorReader countryOperatorReader;
	private EquipmentReader equipmentReader;
	private CallfailureReader callFailureReader;

	public LocalPersistenceService() {
		super();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void login(String pathAndName) throws Exception {
		System.out.println("pathAndName" + pathAndName+"a");
//		createReaders(pathAndName);
	}

	public void createReaders(String pathAndName) {
		lowLevelReader = new FileReader(pathAndName);
		failureClassReader = new FailureclassReader(lowLevelReader);
		causeReader = new CauseReader(lowLevelReader);
		countryOperatorReader = new CountryoperatorReader(lowLevelReader);
		equipmentReader = new EquipmentReader(lowLevelReader);
		callFailureReader = new CallfailureReader(lowLevelReader);
	}

	public void checkInjection() {
		System.out.println("entEJB: " + entEJB);
	}

}
