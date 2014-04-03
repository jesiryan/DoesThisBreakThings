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
package org.jboss.as.quickstarts.html5_mobile.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.quickstarts.html5_mobile.data.CauseRepository;
import org.jboss.as.quickstarts.html5_mobile.rest.CauseService;
import org.jboss.as.quickstarts.html5_mobile.service.CauseRegistration;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.as.quickstarts.html5_mobile.util.Resources;

import com.conygre.training.entities.CallFailure;
import com.conygre.training.entities.Cause;
import com.conygre.training.entities.CausePK;

/**
 * Uses Arquilian to test the JAX-RS processing class for cause registration.
 * 
 * @author balunasj
 */
@RunWith(Arquillian.class)
public class CauseRegistrationTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(Cause.class, CauseService.class,
						CauseRepository.class, CauseRegistration.class,
						Resources.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource("arquillian-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	CauseService causeRegistration;

	@Inject
	Logger log;

	@Test
	public void testRegister() throws Exception {
		CausePK causePK = new CausePK(000, 000);
		Cause cause = createCauseInstance(causePK, "", null);
		Response response = causeRegistration.createCause(cause);

		assertEquals("Unexpected response status", 200, response.getStatus());
		log.info(" New cause was persisted and returned status "
				+ response.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testInvalidRegister() throws Exception {
		CausePK causePK = new CausePK(000, 000);
		Cause cause = createCauseInstance(causePK, "", null);
		Response response = causeRegistration.createCause(cause);

		assertEquals("Unexpected response status", 400, response.getStatus());
		assertNotNull("response.getEntity() should not null",
				response.getEntity());
		assertEquals(
				"Unexpected response.getEntity(). It contains "
						+ response.getEntity(), 3,
				((Map<String, String>) response.getEntity()).size());
		log.info("Invalid cause register attempt failed with return code "
				+ response.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDuplicateCausePK() throws Exception {

		// Register an initial user
		CausePK causePK = new CausePK(000, 000);
		List<CallFailure> callfailures = null;
		Cause cause = createCauseInstance(causePK, "description", callfailures);
		causeRegistration.createCause(cause);

		// Register a different user with the same CausePK
		List<CallFailure> callfailures2 = null;
		Cause anotherCause = createCauseInstance(causePK, "description2", callfailures2);
		Response response = causeRegistration.createCause(anotherCause);

		assertEquals("Unexpected response status", 409, response.getStatus());
		assertNotNull("response.getEntity() should not null",
				response.getEntity());
		assertEquals(
				"Unexpected response.getEntity(). It contains"
						+ response.getEntity(), 1,
				((Map<String, String>) response.getEntity()).size());
		log.info("Duplicate cause register attempt failed with return code "
				+ response.getStatus());
	}

	private Cause createCauseInstance(CausePK id, String description, List<CallFailure> callfailures) {
		Cause cause = new Cause();
		cause.setId(id);
		cause.setDescription(description);
		cause.setCallfailures(callfailures);
		return cause;
	}
}
