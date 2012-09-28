package org.aaron.camel.websocket.test;

import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;

public class ActiveMQBrokerMain {

	private static final Logger log = Logger.getLogger(ActiveMQBrokerMain.class);

	private ActiveMQBrokerMain() {

	}

	public static void main(String[] args) {
		try {
			final BrokerService brokerService = new BrokerService();
			brokerService.addConnector("tcp://0.0.0.0:61616");
			brokerService.setPersistent(false);
			brokerService.start();
			log.info("broker started");
		} catch (Exception e) {
			log.warn("main", e);
		}
	}

}
