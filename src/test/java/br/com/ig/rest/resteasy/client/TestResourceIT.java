package br.com.ig.rest.resteasy.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.ig.rest.web.TestResource;

public class TestResourceIT {

	private TestResource testResource;

	@Before
	public void setup() {
		testResource = new TestResourceBuilder().build();
	}

	@Test
	public void getOk() {
		String ok = testResource.getOk();
		Assert.assertNotNull(ok);
		Assert.assertEquals("This is a test!", ok);
	}

	@Test
	public void getError() {
		try {
			testResource.getError();
			Assert.fail();
		} catch (TestException e) {
			Assert.assertNotNull(e.getMessage());
			Assert.assertEquals("Something went wrong...", e.getMessage());
		}
	}
}
