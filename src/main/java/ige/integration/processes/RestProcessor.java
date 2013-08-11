package ige.integration.processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RestProcessor implements Processor{
	public void process(Exchange exchange) throws Exception {
		System.out.println("RESLET ORDERS: "+exchange.getIn().getBody(String.class));
    }
}
