package org.aaron.camel.websocket.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class WebsocketServerMain {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("websocket-context.xml");

		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
		}
	}
}
