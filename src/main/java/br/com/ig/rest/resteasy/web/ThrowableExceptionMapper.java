package br.com.ig.rest.resteasy.web;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Something went wrong...")
				.type("text/html; charset=UTF-8").build();
	}
}
