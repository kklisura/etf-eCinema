package ba.etf.tim11.eCinema;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("hello")
public class Hello {

  // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Track sayPlainTextHello() {
	  Track t = new Track();
	  t.setSinger("Kenan");
	  t.setTitle("Test");
    return t;
  }
} 