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
import org.jboss.as.quickstarts.html5_mobile.data.EquipmentRepository;
import org.jboss.as.quickstarts.html5_mobile.service.MemberRegistration;

import com.conygre.training.entities.Callfailure;
import com.conygre.training.entities.query.UserStory07Structure;


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
    
    
//    @GET
//    @Path("/us08/{model}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Equipment> findEquipmentByModel(@PathParam("model") String model) {
//    	
//    	List<Equipment> equipments = repositoryE.findEquipmentByModel(model);
//        if (equipments == null) {
//        	return null;
//        }
//        return equipments;
//    }
    
    
}