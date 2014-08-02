package restbox.test.resources1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class HelloWordA {
	
	@GET@Path("/hi")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(@QueryParam("name") String name){
		return "hello " + name;
	}
}
