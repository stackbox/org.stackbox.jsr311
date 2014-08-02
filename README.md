org.stackbox.jsr311
=========
[![Build Status](https://drone.io/github.com/superalsrk/org.stackbox.jsr311/status.png)](https://drone.io/github.com/superalsrk/org.stackbox.jsr311/latest)

Another jax-rs implemention

###Install


###Usage

```xml
//web.xml
<servlet>
	<servlet-name>application1</servlet-name>
	<servlet-class>org.stackbox.jsr311.server.servlet.RestboxServlet</servlet-class>
	<init-param>
		<param-name>packageToScan</param-name>
		<param-value>restbox.test.resources1</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
	
<servlet-mapping>
	<servlet-name>application1</servlet-name>
	<url-pattern>/res/*</url-pattern>
</servlet-mapping>

```

资源文件如下

```java
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

```


