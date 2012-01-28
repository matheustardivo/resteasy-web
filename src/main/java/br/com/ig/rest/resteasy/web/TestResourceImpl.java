package br.com.ig.rest.resteasy.web;

public class TestResourceImpl implements TestResource {

	@Override
	public String getOk() {
		return "This is a test!";
	}

	@Override
	public String getError() {
		throw new IllegalStateException();
	}
}
