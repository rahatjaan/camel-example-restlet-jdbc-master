package ige.integration.processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ResponseProcessor implements Processor{
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setBody("Order is Sent!");
		System.out.println("RESPONSE PROCESSOR: ");
    }
}
