/**
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.html5_mobile.service;

import com.conygre.training.dao.CauseDAO;
import com.conygre.training.entities.Callfailure;
import com.conygre.training.entities.Cause;
import com.conygre.training.entities.CausePK;
import com.conygre.training.entities.Countryoperator;
import com.conygre.training.entities.CountryoperatorPK;
import com.conygre.training.entities.Equipment;
import com.conygre.training.entities.Failureclass;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Path("/cause")
@Stateless
@LocalBean
public class CauseRegistration {
	
	@EJB
    private CauseDAO causeDao;
	
    @GET
    @Path("/{id}")
    public Cause getUserEquipment(@PathParam("id") String id) {
        return causeDao.getCauseById(id);
    }
    
    @GET
    @Path("/all")
    public List<Cause> getAllCauses() {
        return causeDao.getAllCauses();
    }
    
    @POST
    public void addCauses(List<Cause> cause) {
        causeDao.addCauses(cause);
    }
    
    @POST
    public void addCause(){
    	CausePK causePK = new CausePK(001, 002);
    	CountryoperatorPK countryoperatorPK = new CountryoperatorPK(123, 123);
    	Countryoperator countryoperator = new Countryoperator(countryoperatorPK, "country", "operator");
    	Equipment equipment = new Equipment(123, "accessCapability", "equipmentType", "inputMode", "manufacturer", "marketingName", "model", "operatingSystem", "vendorName", null);
    	Failureclass failureclass = new Failureclass(123, "description");
    	Callfailure c1 = new Callfailure(123, 123, new Date(), 500, "hier3", "hier32", "hier321", "iMSI", "nEVersion", equipment, failureclass, countryoperator, null);
    	List<Callfailure> callfailures = null;
    	callfailures.add(c1);
    	Cause cause = new Cause(causePK, "description", callfailures);
    	
    	causeDao.mergeCause(cause);
    }
}
