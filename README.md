camel-websocket
===============

Playground for Camel Websocket component.

Programs:

ActiveMQBrokerMain - start an ActiveMQ broker.

WebsocketServerMain - start a Camel route from activemq:test.websocket.topic to websocket:camel-test

JMSMessageSender - send a text message every 500 ms to activemq:test.websocket.topic.


Running:

Start the programs, then go to http://localhost:9090 to see JMS -> websocket -> browser.