package cia.group6.registration.test.arquillian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import cia.group6.all.services.CallfailureService;
import cia.group6.entities.Callfailure;
import cia.group6.entities.Cause;
import cia.group6.entities.Countryoperator;
import cia.group6.entities.Equipment;
import cia.group6.entities.Failureclass;
import cia.group6.resources.Resources;

/**
 * Uses Arquilian to test the JAX-RS processing class for Callfailure registration.
 * 
 */
@RunWith(Arquillian.class)
public class TestFileImport {
   @Deployment
   public static Archive<?> createTestArchive() {
      return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(cia.group6.entities.Callfailure.class, 
            		cia.group6.entities.Cause.class, 
            		cia.group6.entities.Countryoperator.class, 
            		cia.group6.entities.Equipment.class, 
            		cia.group6.entities.Failureclass.class, Resources.class)
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource("arquillian-ds.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Inject
   CallfailureService callfailureRegistration;

   @Inject
   Logger log;

   @Test
   public void testRegister() throws Exception {
      Callfailure callfailure = createCallfailureInstance(6, new Cause(), 
       		  021, new Countryoperator(), new Date(), 30, new Equipment(), new Failureclass(),
       		  "hier3", "hier32", "hier321", "iMSI", "nEVersion");
      Response response = callfailureRegistration.createCallfailure(callfailure);

      assertEquals("Unexpected response status", 200, response.getStatus());
      log.info(" New Callfailure was persisted and returned status " + response.getStatus());
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testInvalidRegister() throws Exception {
      Callfailure Callfailure = createCallfailureInstance(0, null, 
       		  021, new Countryoperator(), new Date(), 30, null, null,
       		  "hier3", "hier32", "hier321", "iMSI", "nEVersion");
      Response response = callfailureRegistration.createCallfailure(Callfailure);

      assertEquals("Unexpected response status", 400, response.getStatus());
      assertNotNull("response.getEntity() should not null",response.getEntity());
      assertEquals("Unexpected response.getEntity(). It contains " + response.getEntity(), 
                    3, ((Map<String, String>)response.getEntity()).size());
      log.info("Invalid Callfailure register attempt failed with return code " + response.getStatus());
   }
   		
   @SuppressWarnings("unchecked")
   @Test
   public void testDuplicateName() throws Exception {
      //Register an initial user
      Callfailure Callfailure = createCallfailureInstance(2, new Cause(), 
       		  021, new Countryoperator(), new Date(), 30, new Equipment(), new Failureclass(),
       		  "hier3", "hier32", "hier321", "iMSI", "nEVersion");
      callfailureRegistration.createCallfailure(Callfailure);

      //Register a different user with the same email
      Callfailure anotherCallfailure = createCallfailureInstance(2, new Cause(), 
   		  021, new Countryoperator(), new Date(), 30, new Equipment(), new Failureclass(),
   		  "hier3", "hier32", "hier321", "iMSI", "nEVersion");
      Response response = callfailureRegistration.createCallfailure(anotherCallfailure);

      assertEquals("Unexpected response status", 409, response.getStatus());
      assertNotNull("response.getEntity() should not null",response.getEntity());
      assertEquals("Unexpected response.getEntity(). It contains" + response.getEntity(), 
                   1, ((Map<String, String>)response.getEntity()).size());
      log.info("Duplicate Callfailure register attempt failed with return code " + response.getStatus());
   }
    
   private Callfailure createCallfailureInstance(int baseDataID, Cause cause, 
		   int cellId, Countryoperator countryoperator, Date dateTime, 
		   int duration, Equipment equipment, Failureclass failureclass,
		   String hier3, String hier32, String hier321, String iMSI, String nEVersion) {
      Callfailure callfailure = new Callfailure();
      callfailure.setBaseDataID(baseDataID);
      callfailure.setCause(cause);
      callfailure.setCellId(cellId);
      callfailure.setCountryoperator(countryoperator);
      callfailure.setDateTime(dateTime);
      callfailure.setDuration(duration);
      callfailure.setEquipment(equipment);
      callfailure.setFailureclass(failureclass);
      callfailure.setHier3(hier3);
      callfailure.setHier32(hier32);
      callfailure.setHier321(hier321);
      callfailure.setIMSI(iMSI);
      callfailure.setNEVersion(nEVersion);
      return callfailure;
   }
}
