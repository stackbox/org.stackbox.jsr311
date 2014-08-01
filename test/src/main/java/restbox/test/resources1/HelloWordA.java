package restbox.test.resources1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWordA {
	
	@GET@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(){
		return "hello world";
	}
}
