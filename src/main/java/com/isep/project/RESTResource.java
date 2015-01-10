package com.isep.project;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/res")
public class RESTResource {
    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("/resxml")
    public RESTObject testObjectXML(){
        RESTObject obj=new RESTObject(); obj.setAuthor("Asimov"); obj.setTitle("Foundation"); return obj;
    }
    @GET @Produces(MediaType.APPLICATION_JSON)
    @Path("/resjson")
    public RESTObject testObjectJSON(){

        RESTObject obj=new RESTObject();
        obj.setAuthor("Asimov");
        obj.setTitle("Foundation");
        return obj;

    }
}