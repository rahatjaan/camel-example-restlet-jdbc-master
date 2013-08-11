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

import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mail.MailComponent;
import org.apache.camel.impl.DefaultCamelContext;

import com.inroom.processes.JMSProcessor;
import com.inroom.processes.MyProcessor;
import com.inroom.processes.RestProcessor;
import com.inroom.utils.DataBean;

public class TestRouteBuilder extends RouteBuilder {
	// create CamelContext
    CamelContext context = new DefaultCamelContext();
    
    private final String HOSTNAME = "smtp.gmail.com";
    private final String PORT = "587";
    private final String PASSWORD = "PASSWORD";
    private final String USERNAME = "USERNAME";
    private final String FROM = "FROM";
    private final String TO = "TO";

    @Override
    public void configure() {
    	
        from("restlet:/createOrder?restletMethod=POST")
        .transform()
        	.method(DataBean.class, "newData(${header[id]})")
        .pipeline("sql:{{sql.selectData}}")
        	.beanRef("orderBean","processOrder")
        .filter()
        	.method(DataBean.class, "checkOrder").process(new MyProcessor())
        .choice()
        		.when().xpath("/dataBean/id=1")
        			.to("jms:orders")
        		.when().xpath("/dataBean/id=2")
        			//.setBody(this.body()).to("restlet:/postOrder?restletMethod=GET")
        		.setBody(this.body()).to("restlet:/postOrder?restletMethod=GET")
        		.otherwise()
        			.setHeader("subject", constant("TEST")).to("smtp://"+HOSTNAME+":"+PORT+"?password="+PASSWORD+"&username="+USERNAME+"&from="+FROM+"&to="+TO+"&mail.smtp.starttls.enable=true");
        
        from("jms:orders").process(new JMSProcessor());
        
        from("restlet:/postOrder?restletMethod=GET").process(new RestProcessor());
    }
}
