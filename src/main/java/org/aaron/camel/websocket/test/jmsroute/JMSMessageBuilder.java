package org.aaron.camel.websocket.test.jmsroute;

public class JMSMessageBuilder {

	public String buildMessage() {
		return "<xmlMessage><stuff>things</stuff><timeMS>"
				+ System.currentTimeMillis() + "</timeMS></xmlMessage>";
	}
}
