package br.com.ig.rest.resteasy.client;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.jboss.resteasy.client.core.ClientErrorInterceptor;

public class TestClienteErrorInterceptor implements ClientErrorInterceptor {

	private static final Logger LOGGER = Logger.getLogger(TestClienteErrorInterceptor.class);

	@Override
	public void handle(ClientResponse<?> response) throws RuntimeException {
		String errorMessage = "";

		try {
			BaseClientResponse<?> r = (BaseClientResponse<?>) response;
			InputStream stream = r.getStreamFactory().getInputStream();
			stream.reset();

			errorMessage = response.getEntity(String.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex);
		}

		throw new TestException(errorMessage);
	}
}
