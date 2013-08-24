package ige.integration.exception;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CustomExceptionProcessor implements Processor {
	 
    public void process(Exchange exchange) throws Exception {
        // the caused by exception is stored in a property on the exchange
        //assertNotNull(caused);
        // here you can do what you want, but Camel regard this exception as handled, and
        // this processor as a failurehandler, so it wont do redeliveries. So this is the
        // end of this route. But if we want to route it somewhere we can just get a
        // producer template and send it.
 
        // send it to our mock endpoint
       // exchange.getContext().createProducerTemplate().sendBody("bean:responseProcessor",exchange.getIn().getBody().toString());
        exchange.getOut().setBody("Could not process request.");
    }
}
