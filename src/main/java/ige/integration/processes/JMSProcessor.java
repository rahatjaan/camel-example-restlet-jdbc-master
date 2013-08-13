package ige.integration.processes;

import ige.integration.model.TenantInfo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class JMSProcessor implements Processor{
	public void process(Exchange exchange) throws Exception {
		System.out.println("JMS ORDERS: "+exchange.getIn().getBody().toString());
    }
}
