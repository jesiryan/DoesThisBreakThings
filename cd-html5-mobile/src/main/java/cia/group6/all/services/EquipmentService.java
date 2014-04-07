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
package cia.group6.all.services;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cia.group6.entities.Equipment;
import cia.group6.registration.MemberRegistration;
import cia.group6.repositories.EquipmentRepository;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/equipments")
@RequestScoped
@Stateful
public class EquipmentService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private EquipmentRepository repository;

    @Inject
    MemberRegistration upload;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Equipment> listAllCauses() {
        return repository.findAllOrderedByEquipmentType();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Equipment lookupCauseById(@PathParam("id") int id) {
    	Equipment equipment = repository.findByTac(id);
        if (equipment == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return equipment;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEquipment(Equipment equipment) {
    	return null;
    }
    
}

