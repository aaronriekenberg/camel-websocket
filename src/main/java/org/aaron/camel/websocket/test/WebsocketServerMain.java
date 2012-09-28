package org.aaron.camel.websocket.test;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.websocket.WebsocketComponent;
import org.apache.camel.main.Main;
import org.apache.log4j.Logger;

public final class WebsocketServerMain {

	private static final Logger log = Logger.getLogger(JMSMessageSender.class);

	private static final String JMS_BROKER_URL = "tcp://localhost:61616";

	private static final int WEBSOCKET_PORT = 9090;

	private WebsocketServerMain() {

	}

	private static class WebsocketRoute extends RouteBuilder {
		@Override
		public void configure() throws Exception {

			final ActiveMQComponent amc = getContext().getComponent("activemq",
					ActiveMQComponent.class);
			amc.setBrokerURL(JMS_BROKER_URL);
			amc.setDeliveryPersistent(false);

			// setup Camel web-socket component on the port we have defined
			final WebsocketComponent wc = getContext().getComponent(
					"websocket", WebsocketComponent.class);
			wc.setPort(WEBSOCKET_PORT);
			// we can serve static resources from the classpath: or file: system
			wc.setStaticResources("classpath:.");

			log.info("websocket server listening on " + wc.getHost() + ":"
					+ wc.getPort());

			// poll twitter search for new tweets
			fromF("activemq:topic:test.websocket.topic").log(
					"publishing ${body}")
			// and push tweets to all web socket subscribers on camel-tweet
					.to("websocket:camel-test?sendToAll=true");
		}
	}

	public static void main(String[] args) {
		try {
			// create a new Camel Main so we can easily start Camel
			Main main = new Main();

			// enable hangup support which mean we detect when the JVM
			// terminates,
			// and stop Camel graceful
			main.enableHangupSupport();

			// add our routes to Camel
			main.addRouteBuilder(new WebsocketRoute());

			// and run, which keeps blocking until we terminate the JVM (or stop
			// CamelContext)
			main.run();
		} catch (Exception e) {
			log.warn("main", e);
		}
	}
}
