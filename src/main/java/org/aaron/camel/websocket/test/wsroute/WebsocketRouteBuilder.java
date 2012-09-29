package org.aaron.camel.websocket.test.wsroute;

import org.apache.camel.builder.RouteBuilder;

public class WebsocketRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("activemq:topic:test.websocket.topic")
				.log("publishing ${body}")
				.to("websocket://localhost:9090/camel-test?sendToAll=true&staticResources=classpath:webapp");
	}

}
