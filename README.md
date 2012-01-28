# Resteasy Sample project

This is a sample project that uses Resteasy [Client Framework](http://docs.jboss.org/resteasy/docs/2.3.1.GA/userguide/html_single/index.html#RESTEasy_Client_Framework). 

This project just exposes a simple REST resource and access it through the client framework. 

In one of the test scenarios, the REST resource respond an `INTERNAL SERVER ERROR` status with a message, and the client receive this error and send the message within an exception. 

If you run the tests using an old Resteasy version (**2.0.1.GA**), all the tests will pass. But using the current release (**2.3.1.GA**) the test that expect receive an exception with the REST resource message will fail. 

## Running the tests

Running `mvn verify` will start a jetty server at `pre-integration-test` phase, run the tests and shut down the jetty server at `post-integration-test` phase. 

Running the tests with the `resteasy.version` **2.3.1.GA**, the test `getError()` of `br.com.ig.rest.resteasy.client.TestResourceIT` class will fail. 

The `br.com.ig.rest.resteasy.client.TestClienteErrorInterceptor` class will throw an exception at line 21 `stream.reset()`. 

The exception is `java.io.IOException: Stream closed`.

`java.io.IOException: Stream closed
	at java.io.BufferedInputStream.getBufIfOpen(BufferedInputStream.java:145)
	at java.io.BufferedInputStream.reset(BufferedInputStream.java:414)
	at br.com.ig.rest.resteasy.client.TestClienteErrorInterceptor.handle(TestClienteErrorInterceptor.java:21)
	at org.jboss.resteasy.client.core.extractors.ClientErrorHandler.clientErrorHandling(ClientErrorHandler.java:49)
	at org.jboss.resteasy.client.core.extractors.BodyEntityExtractor.extractEntity(BodyEntityExtractor.java:40)
	at org.jboss.resteasy.client.core.ClientInvoker.invoke(ClientInvoker.java:120)
	at org.jboss.resteasy.client.core.ClientProxy.invoke(ClientProxy.java:88)
	at $Proxy19.getError(Unknown Source)
	at br.com.ig.rest.resteasy.client.TestResourceIT.getError(TestResourceIT.java:28)`

Just changing the `resteasy.version` property to **2.0.1.GA** then all tests pass.

## Environment
`java version "1.6.0_29"
Java(TM) SE Runtime Environment (build 1.6.0_29-b11-402-11M3527)
Java HotSpot(TM) 64-Bit Server VM (build 20.4-b02-402, mixed mode)` 

`Apache Maven 2.2.1 (r801777; 2009-08-06 16:16:01-0300)`
