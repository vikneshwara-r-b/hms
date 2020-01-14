/*
 *
 */
package global.coda.hmsbackend.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The Class HelloService-To test the server and the POSTMAN environment.
 */
@Path("/service")
public class HelloService {

	/**
	 * Hello.
	 *
	 * @return the string
	 */
	@GET
	@Path("hello")
	public String hello() {
		return "Hello, rest!";
	}
}
