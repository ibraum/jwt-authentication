/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package jwt;

import entity.User;
import generate.token.Jwttoken;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;

/**
 * REST Web Service
 *
 * @author kondoibrahim
 */
@Path("/get")
@RequestScoped
public class Test {
    
    @Inject
    Jwttoken jwtt;
    
    /**
     * Creates a new instance of GenericResource
     */
    public Test() {
    }


    @POST
    @Path("/tokenspringsecurity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(User user) {
        String message = "{\"Message\":"+ jwtt.getConnection(user.username) +"}";
        return Response.status(Response.Status.OK).entity(message).build();
    }


    @POST
    @Path("/tokenjavaawt")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonJavaJwt(String username) throws IOException{
        String message = "{\n\"Token\":"+ jwtt.getTokenWithJavaJwt(username) +"\n}";
        return Response.status(Response.Status.OK).entity(message).build();
    }
}
