
package ige.integration.router;

import ige.integration.processes.IntegrationProcessor;
import ige.integration.processes.JMSProcessor;
import ige.integration.processes.RestProcessor;
import ige.integration.utils.DataBean;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class IntegrationRouteBuilder extends RouteBuilder {
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
        	.beanRef("dataBean","processOrder")
        .filter()
        	.method(DataBean.class, "checkOrder").process(new IntegrationProcessor())
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
