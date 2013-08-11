package com.inroom.processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.inroom.utils.BeanToXML;
import com.inroom.utils.DataBean;
import com.inroom.utils.XMLToBean;

public class MyProcessor implements Processor{

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String body = exchange.getIn().getBody().toString();
    	DataBean obj = XMLToBean.readXML(body);
    	obj.setId(3);        	    	
    	String xml = BeanToXML.readObject(obj);
    	System.out.println("XML HERE IS: "+xml);
    	exchange.getOut().setBody(xml);
    	exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        System.out.println("Body is: " 
                + exchange.getIn().getBody(String.class));
	}

}
