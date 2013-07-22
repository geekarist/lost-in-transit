package com.github.geekarist.lostintransit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/myresource")
public class MyResource {

    private final static String WORK = "2, Place de La Défense, 92800, Puteaux";

    @GET
    @Produces("text/plain")
    public String getIt() {
        return "194";
    }
    
    public String getIt(String home) {
    	return getIt();
    }
}
