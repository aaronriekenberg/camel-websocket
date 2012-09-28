package org.aaron.camel.websocket.test;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.bean.BeanComponent;
import org.apache.camel.main.Main;
import org.apache.log4j.Logger;

public class JMSMessageSender {

	private static final Logger log = Logger.getLogger(JMSMessageSender.class);

	private static final String JMS_BROKER_URL = "tcp://localhost:61616";

	private static final int MESSAGE_RATE_MS = 500;

	private JMSMessageSender() {

	}

	public static class JMSMessageBuilder {

		private static final AtomicInteger counter = new AtomicInteger(0);

		public String buildMessage() {
			return ("Message " + counter.getAndIncrement());
		}
	}

	private static class JMSMessageSenderRoute extends RouteBuilder {
		@Override
		public void configure() throws Exception {

			final ActiveMQComponent amc = getContext().getComponent("activemq",
					ActiveMQComponent.class);
			amc.setBrokerURL(JMS_BROKER_URL);
			amc.setDeliveryPersistent(false);

			final BeanComponent bc = getContext().getComponent("bean",
					BeanComponent.class);
			final Endpoint beanEndpoint = bc
					.createEndpoint(new JMSMessageBuilder());

			from(
					"timer://testMessageSenderTimer?fixedRate=true&period="
							+ MESSAGE_RATE_MS).to(beanEndpoint)
					.log("publishing ${body}")
					.to("activemq:topic:test.websocket.topic");
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

			main.addRouteBuilder(new JMSMessageSenderRoute());

			// and run, which keeps blocking until we terminate the JVM (or stop
			// CamelContext)
			main.run();
		} catch (Exception e) {
			log.warn("main", e);
		}
	}

}
