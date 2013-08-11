package ige.integration.processes;

import ige.integration.utils.BeanToXML;
import ige.integration.utils.DataBean;
import ige.integration.utils.XMLToBean;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class IntegrationProcessor implements Processor{

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String body = exchange.getIn().getBody().toString();
    	DataBean obj = XMLToBean.readXML(body);
    	obj.setId(1);        	    	
    	String xml = BeanToXML.readObject(obj);
    	System.out.println("XML HERE IS: "+xml);
    	exchange.getOut().setBody(xml);
    	exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        System.out.println("Body is: " 
                + exchange.getIn().getBody(String.class));
	}

}
