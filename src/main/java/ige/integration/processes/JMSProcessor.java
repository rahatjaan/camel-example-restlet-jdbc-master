package ige.integration.processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class JMSProcessor implements Processor{
	public void process(Exchange exchange) throws Exception {
		System.out.println("JMS ORDERS: "+exchange.getIn().getBody());
		exchange.getOut().setBody("Message is received at POS JMS Endpoint");
    }
}
