package br.com.ig.rest.resteasy.client;

import java.io.IOException;
import java.util.Arrays;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.interception.ClientInterceptor;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.plugins.providers.jaxb.JAXBUnmarshalException;
import org.jboss.resteasy.spi.interception.ClientExecutionContext;
import org.jboss.resteasy.spi.interception.ClientExecutionInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyReaderContext;
import org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor;

@ClientInterceptor
public class TestRestInterceptor implements ClientExecutionInterceptor, MessageBodyReaderInterceptor {

	private static final Logger LOGGER = Logger.getLogger(TestResourceBuilder.class);

	private static String mediaType = MediaType.APPLICATION_JSON;

	@SuppressWarnings("rawtypes")
	@Override
	public ClientResponse execute(ClientExecutionContext ctx) throws Exception {
		ctx.getRequest().getHeaders().put("Accept", Arrays.asList(mediaType));
		if (ctx.getRequest().getBody() != null) {
			ctx.getRequest().body(mediaType, ctx.getRequest().getBody());
		}
		return ctx.proceed();
	}

	@Override
	public Object read(MessageBodyReaderContext context) throws IOException, WebApplicationException {
		try {
			return context.proceed();
		} catch (JAXBUnmarshalException e) {
			LOGGER.error(e);
			throw e;
		}
	}

	public static void setMediaType(String mediaType) {
		TestRestInterceptor.mediaType = mediaType;
	}
}