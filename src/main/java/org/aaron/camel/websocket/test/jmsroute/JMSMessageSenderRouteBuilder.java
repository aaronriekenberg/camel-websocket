package org.aaron.camel.websocket.test.jmsroute;

import org.apache.camel.builder.RouteBuilder;

public class JMSMessageSenderRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer://testMessageSenderTimer?fixedRate=true&period=500")
				.bean(new JMSMessageBuilder()).log("publishing ${body}")
				.to("activemq:topic:test.websocket.topic");
	}
}
