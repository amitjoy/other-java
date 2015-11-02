package com.example.SelfishConfigurator.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import com.example.SelfishConfigurator.model.Category;

@Stateless
@Path("/categorys")
public class CategoryEndpoint {

    @PersistenceContext
    private EntityManager em;

    @POST
    @Consumes("application/xml")
    public Response create(Category entity) {
        em.persist(entity);
        return Response.created(UriBuilder.fromResource(CategoryEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") Long id) {
        Category entity = em.find(Category.class, id);
        if (entity == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        em.remove(entity);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("application/xml")
    public Response findById(@PathParam("id") Long id) {
        Category entity = em.find(Category.class, id);
        if (entity == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }

    @GET
    @Encoded
    @Produces("application/xml")
    public List<Category> listAll() {
        final List<Category> results = em.createQuery("FROM Category", Category.class).getResultList();
        return results;
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    @Consumes("application/xml")
    public Response update(@PathParam("id") Long id, Category entity) {
        entity.setId(id);
        entity = em.merge(entity);
        return Response.noContent().build();
    }
}