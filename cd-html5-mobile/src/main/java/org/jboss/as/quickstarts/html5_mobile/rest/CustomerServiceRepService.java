package org.jboss.as.quickstarts.html5_mobile.rest;

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

import com.conygre.training.entities.Callfailure;
import com.conygre.training.entities.query.UserStory05Structure;
import com.conygre.training.entities.query.UserStory06Structure;


/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/cust")
@RequestScoped
@Stateful
public class CustomerServiceRepService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private CallfailureRepository repository;

    @Inject
    MemberRegistration upload;
  
    @GET
    @Path("/us04/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Callfailure> findCauseCode_EventIDByIMSI(@PathParam("imsi") String IMSI) {	
    	List<Callfailure> callfailures = repository.findCauseCode_EventIDByIMSI(IMSI);
        if (callfailures == null) {
        	return null;
        }
        return callfailures;
    }
    
    @GET
    @Path("/us05/{imsi}/{start}/{end}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserStory05Structure> findCauseCode_EventIDByIMSI(	@PathParam("imsi") String IMSI,
														    		@PathParam("start") String startString,
																	@PathParam("end") String endString) {
    	startString = startString.replaceAll("T", " ");
    	endString = endString.replaceAll("T", " ");
    	List<UserStory05Structure> callfailures = repository.findCallByIMSIBetweenDate(IMSI, startString, endString);
        if (callfailures == null) {
        	return null;
        }
        return callfailures;
    }
    
    @GET
    @Path("/us06/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserStory06Structure> test(@PathParam("imsi") String IMSI) {	
    	List<UserStory06Structure> userStory06Structures = repository.findUserStory06(IMSI);
        if (userStory06Structures == null) {
        	return null;
        }
        return userStory06Structures;
    }  
    
}
