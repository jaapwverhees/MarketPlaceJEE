package com.controller.resources;

import com.controller.VisitorLoginController;
import com.model.Visitor;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserResource {
    private VisitorLoginController loginController = new VisitorLoginController();

    //TODO VERY UNSAFE
    @GET
    @Path("{email}/{password}")
    public Visitor get(@PathParam("email") String email, @PathParam("password") String password) {
        return loginController.login(email, password);
    }
    @POST
    @Path("{email}/{password}")
    public Visitor post(@PathParam("email") String email, @PathParam("password") String password) {
        return loginController.login(email, password);
    }
}
