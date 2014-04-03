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


@Path("/supp")
@RequestScoped
@Stateful
public class SupportEngineerService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private CallfailureRepository repository;

    @Inject
    MemberRegistration upload;
  
    
    // EXAMPLE METHOD - path consists of user story number and variable to send to query; can also use @QueryParam instead
    @GET
    @Path("/usXX/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Callfailure> findCauseCode_EventIDByIMSI(@PathParam("imsi") String IMSI) {	
    	List<Callfailure> callfailures = repository.findCauseCode_EventIDByIMSI(IMSI);
        if (callfailures == null) {
        	return null;
        }
        return callfailures;
    }  
    
}