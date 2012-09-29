package org.aaron.camel.websocket.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActiveMQBrokerMain {

	private ActiveMQBrokerMain() {

	}

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("activemq-broker.xml");

		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
		}
	}

}
