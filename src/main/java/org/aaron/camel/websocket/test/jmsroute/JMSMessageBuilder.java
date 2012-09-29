package org.aaron.camel.websocket.test.jmsroute;

import java.util.concurrent.atomic.AtomicInteger;

public class JMSMessageBuilder {

	private static final AtomicInteger counter = new AtomicInteger(0);

	public String buildMessage() {
		return ("Message " + counter.getAndIncrement());
	}

}
