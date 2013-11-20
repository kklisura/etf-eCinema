package ba.etf.tim11.eCinema;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("tracks")
public class TrackResource
{
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Track getOne() {
	  Track t = new Track();
	  t.setSinger("Kenan");
	  t.setTitle("Test");
	  return t;
  }
} 