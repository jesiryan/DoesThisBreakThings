package cia.group6.all.services;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cia.group6.entities.Callfailure;
import cia.group6.registration.MemberRegistration;
import cia.group6.repositories.CallfailureRepository;
import cia.group6.repositories.EquipmentRepository;
import cia.group6.story.structures.UserStory07Structure;
import cia.group6.story.structures.UserStory08Structure;
import cia.group6.story.structures.UserStory14Structure;


@Path("/supp")
@RequestScoped
@Stateful
public class SupportEngineerService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private CallfailureRepository repositoryC;
    @Inject
    private EquipmentRepository repositoryE;

    @Inject
    MemberRegistration upload;
  
    
    @GET
    @Path("/us07/{start}/{end}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserStory07Structure> findAllCallFailuresBetween(	@PathParam("start") String startString,
    																@PathParam("end") String endString) {
    	
    	startString = startString.replaceAll("T", " ");
    	endString = endString.replaceAll("T", " ");
    	
    	List<UserStory07Structure> userStory07Structures = repositoryC.findAllCallFailuresBetween(startString, endString);
        if (userStory07Structures == null) {
        	return null;
        }
        return userStory07Structures;
    }  
    
    
    @GET
    @Path("/us08/{start}/{end}/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserStory08Structure> findEquipmentByModel(@PathParam("start") String startString,
															@PathParam("end") String endString,
															@PathParam("model") String model) {
    	
    	startString = startString.replaceAll("T", " ");
    	endString = endString.replaceAll("T", " ");
    	
    	List<UserStory08Structure> UserStory08Structures = repositoryC.findAllCallFailuresBetweenDatesByModel(startString, endString, model);
      if (UserStory08Structures == null) {
        	return null;
       }
        return UserStory08Structures;
    }

    
    @GET
    @Path("/us14/{causeCode}/{eventId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserStory14Structure> findAffectedIMSIsGivenCauseClass(	@PathParam("causeCode") String causeCode,
    																@PathParam("eventId") String eventId) {
    	
    	List<UserStory14Structure> userStory14Structures = repositoryC.findAffectedIMSIsGivenCauseClass(Double.parseDouble(causeCode), Double.parseDouble(eventId));
        if (userStory14Structures == null) {
        	return null;
        }
        return userStory14Structures;
    }  
}
