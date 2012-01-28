package br.com.ig.rest.resteasy.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import br.com.ig.rest.resteasy.web.TestResource;

public class TestResourceBuilder {

	private static final Logger LOGGER = Logger.getLogger(TestResourceBuilder.class);

	private static ResteasyProviderFactory pf = ResteasyProviderFactory.getInstance();
	{
		pf.addClientErrorInterceptor(new TestClienteErrorInterceptor());
		pf.registerProvider(TestRestInterceptor.class);
	}

	private String url = "http://localhost:9080/resteasy-web/rest/";

	public TestResource build() {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			ClientExecutor clientExecutor = new ApacheHttpClient4Executor(httpClient);
			return ProxyFactory.create(TestResource.class, new URI(this.url), clientExecutor, TestResourceBuilder.pf);
		} catch (URISyntaxException e) {
			LOGGER.error(e);
		}
		return null;
	}
}
