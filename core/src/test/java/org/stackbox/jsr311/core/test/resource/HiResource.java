package org.stackbox.jsr311.core.test.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/path2")
public class HiResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String say() {
		return "hi!";
	}
	
	@POST@Path("/hi")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(@QueryParam("name") String name) {
		return "Hi " + name;
	}
}
