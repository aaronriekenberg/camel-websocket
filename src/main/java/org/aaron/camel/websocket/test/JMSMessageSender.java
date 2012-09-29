package org.aaron.camel.websocket.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JMSMessageSender {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("jmssender-context.xml");

		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
		}
	}

}
