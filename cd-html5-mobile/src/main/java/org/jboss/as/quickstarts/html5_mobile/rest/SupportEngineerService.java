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
import org.jboss.as.quickstarts.html5_mobile.data.EquipmentRepository;
import org.jboss.as.quickstarts.html5_mobile.service.MemberRegistration;

import com.conygre.training.entities.Callfailure;
import com.conygre.training.entities.Equipment;


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
    public List<Callfailure> findAllCallFailuresBetween(	@PathParam("start") String startString,
    														@PathParam("end") String endString) {
    	
    	Date startDateTime=null, endDateTime=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");      
		try {
		    startDateTime = sdf.parse(startString);
		    endDateTime = sdf.parse(endString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
    	List<Callfailure> userStory09Structures = repositoryC.findAllCallFailuresBetween(startDateTime, endDateTime);
        if (userStory09Structures == null) {
        	return null;
        }
        return userStory09Structures;
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