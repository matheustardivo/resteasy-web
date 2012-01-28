package br.com.ig.rest.resteasy.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/test")
public interface TestResource {

	@GET
	@Path("/ok")
	public String getOk();

	@GET
	@Path("/error")
	public String getError();
}
