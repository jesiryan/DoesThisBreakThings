package org.jboss.as.quickstarts.html5_mobile.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.jboss.as.quickstarts.html5_mobile.data.CallfailureRepository;
import org.jboss.as.quickstarts.html5_mobile.service.MemberRegistration;

import com.conygre.training.entities.query.UserStory09Structure;
import com.conygre.training.entities.query.UserStory12Structure;


@Path("/net")
@RequestScoped
@Stateful
public class NetworkManagementEngineerService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private CallfailureRepository repository;

    @Inject
    MemberRegistration upload;
  
    
    @GET
    @Path("/us09/{start}/{end}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserStory09Structure> findCauseCode_EventIDByIMSI(	@PathParam("start") String startString,
    																@PathParam("end") String endString) {
    	startString = startString.replaceAll("T", " ");
    	endString = endString.replaceAll("T", " ");

    	List<UserStory09Structure> userStory09Structures = repository.findCountBetweenTimesTotalDuration(startString, endString);
        if (userStory09Structures == null) {
        	return null;
        }
        return userStory09Structures;
    }
    

    @GET
    @Path("/us12/{start}/{end}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserStory12Structure> findAllCallFailuresBetween(	@PathParam("start") String startString,
    																@PathParam("end") String endString) {
    	startString = startString.replaceAll("T", " ");
    	endString = endString.replaceAll("T", " ");

    	List<UserStory12Structure> userStory12Structures = repository.findTop10CountBetweenTimesTotalDuration(startString, endString);
        if (userStory12Structures == null) {
        	return null;
        }
        return userStory12Structures;
    }
}