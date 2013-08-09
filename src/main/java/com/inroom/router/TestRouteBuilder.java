/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.inroom.router;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import com.inroom.utils.BeanToXML;
import com.inroom.utils.DataBean;
import com.inroom.utils.XMLToBean;

public class TestRouteBuilder extends RouteBuilder {
	// create CamelContext
    CamelContext context = new DefaultCamelContext();
    
    private final String HOSTNAME = "123";
    private final String PORT = "123";
    private final String PASSWORD = "123";
    private final String USERNAME = "123";
    private final String FROM = "123";
    private final String TO = "123";

    @Override
    public void configure() {
        from("restlet:/createOrder?restletMethod=POST")
        .transform()
        	.method(DataBean.class, "newData(${header[id]})")
        .pipeline("sql:{{sql.selectData}}")
        	.beanRef("orderBean","processOrder")
        .filter()
        	.method(DataBean.class, "checkOrder").process(new Processor(){
        		// Hard Coding here for now, need the Business Logic to tranfer to endpoint.
        		public void process(Exchange exchange) throws Exception {
        			String body = exchange.getIn().getBody().toString();
        	    	DataBean obj = XMLToBean.readXML(body);
        	    	obj.setId(2);        	    	
        	    	String xml = BeanToXML.readObject(obj);
        	    	System.out.println("XML HERE IS: "+xml);
        	    	exchange.getOut().setBody(xml);
        	    	exchange.getOut().setHeaders(exchange.getIn().getHeaders());
                    System.out.println("Body is: " 
                            + exchange.getIn().getBody(String.class));   
                }
        	})
        .choice()
        		.when().xpath("/dataBean/id=1")
        			.to("jms:orders")
        		.when().xpath("/dataBean/id=2")
        			.to("restlet:/postOrder?restletMethod=GET")
        		.otherwise()
        			.to("smtp://"+HOSTNAME+":"+PORT+"?password="+PASSWORD+"&username="+USERNAME+"&from="+FROM+"&to="+TO);
        
        from("jms:orders").process(new Processor(){
        	public void process(Exchange exchange) throws Exception {
        		System.out.println("JMS ORDERS: "+exchange.getIn().getBody(String.class));
            }
        });
        
        from("restlet:/postOrder?restletMethod=GET").process(new Processor(){
        	public void process(Exchange exchange) throws Exception {
        		System.out.println("RESLET ORDERS: "+exchange.getIn().getBody(String.class));
            }
        });
    }
}
