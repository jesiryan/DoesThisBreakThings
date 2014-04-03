package org.jboss.as.quickstarts.html5_mobile.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.dt340a.group6.sprint1.entity.CallFailure;
import org.jboss.as.quickstarts.html5_mobile.data.CallfailureRepository;
import org.jboss.as.quickstarts.html5_mobile.service.MemberRegistration;

import com.conygre.training.entities.CallFailure;


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
    public List<CallFailure> findCauseCode_EventIDByIMSI(@PathParam("imsi") String IMSI) {	
    	List<CallFailure> callfailures = repository.findCauseCode_EventIDByIMSI(IMSI);
        if (callfailures == null) {
        	return null;
        }
        return callfailures;
    }  

	public static List<CallFailure> countCauseCode(int tAC, double cause,
			double event) {
		// eventId and o.causeCode =:causeCode
		EntityManager em = emf.createEntityManager();
		List<CallFailure> causeList = (List<CallFailure>) em
				.createNamedQuery("CallFailure.countByEventAndCause")
				.setParameter("TAC", tAC).setParameter("EVENT", event)
				.setParameter("CAUSE", cause).getResultList();
		em.close();

		if (causeList.size() == 0)
			return null;
		else
			return causeList;
	}
    
}