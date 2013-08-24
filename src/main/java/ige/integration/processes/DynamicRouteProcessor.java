package ige.integration.processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DynamicRouteProcessor implements Processor{

	public void process(Exchange arg0) throws Exception {
		// TODO Auto-generated method stub
		String url = arg0.getIn().getHeader("OutboundUrl").toString();
		System.out.println("URL IS: "+url);
		arg0.getContext().createProducerTemplate().send((String) arg0.getIn().getHeader("OutboundUrl"),arg0);
	}

}
