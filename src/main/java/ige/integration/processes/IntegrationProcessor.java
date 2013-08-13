package ige.integration.processes;

import ige.integration.model.InRoomOrderPayLoad;
import ige.integration.model.TenantInfo;
import ige.integration.utils.BeanToXML;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class IntegrationProcessor implements Processor {

	private DataSource dataSource;
	
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		InRoomOrderPayLoad payload = populateTenantInfo(exchange);
		System.out.println("IN PROCESSOR TOP: "+payload.toString());
		//System.out.println("IN PROCESSOR: "+exchange.getIn(InRoomOrderPayLoad.class).getTenant().getTenantId());
		String xml = BeanToXML.readObject(payload.getTenant());
		exchange.getOut().setBody(xml);
		System.out.println("PROCESSOR ENDS "+xml);
		Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(Status.SUCCESS_OK);
        response.setEntity("<response>Order is sent!</response>", MediaType.TEXT_XML);
        //exchange.getOut().setBody(response);
		//System.out.println("EXCHANGE IS NOW!!! "+exchange.getIn().getBody().toString());
		/*InRoomOrderPayLoad p = exchange.getIn().getBody(InRoomOrderPayLoad.class);
		System.out.println("Object value: "+p.getPayload());*/
		
		/*System.out.println("Rahat hereeeeeeeeeeeeeee: "+exchange.getIn().getBody().toString());
		String value = exchange.getIn().getBody().toString();
		InRoomOrderPayLoad payload = new InRoomOrderPayLoad(value,exchange,new PopulateTenantInfo().getTenantInfo("test_guid2"));
		exchange.getOut().setBody(payload);
		InRoomOrderPayLoad p = exchange.getIn().getBody(InRoomOrderPayLoad.class);
		System.out.println("Object value: "+p.getPayload());*/
		/*String body = exchange.getIn().getBody().toString();
    	DataBean obj = XMLToBean.readXML(body);
    	obj.setId(1);        	    	
    	String xml = BeanToXML.readObject(obj);
    	System.out.println("XML HERE IS: "+xml);
    	exchange.getOut().setBody(xml);
    	exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        System.out.println("Body is: " 
                + exchange.getIn().getBody(String.class));*/
	}
	
	
	public InRoomOrderPayLoad populateTenantInfo(Exchange exchange){
		String value = exchange.getIn().getBody().toString();
		System.out.println("PopulateTenantInfo xml \n"+value);
		InRoomOrderPayLoad payload = new InRoomOrderPayLoad(value,exchange,getTenantInfo("test_guid2"));
		System.out.println("PAYLOAD HERE IS: "+payload.getTenant().getTenantId());
		String val = "<outboundType>"+payload.getTenant().getOutboundType()+"</outboudType>"+"<outboundURL>"+payload.getTenant().getOutboundUrl()+"</outboundURL>";
		
		/*exchange.getOut().setBody(payload, InRoomOrderPayLoad.class);
		System.out.println("MEDIUM IS: "+exchange.getIn().getBody().toString());*/
		//exchange.getOut().setBody(payload,InRoomOrderPayLoad.class);
		
		/*InRoomOrderPayLoad obj = exchange.getIn(InRoomOrderPayLoad.class);
		System.out.println("OBJECT HERE IS: "+obj.getTenant().getTenantId());*/
		
		return payload;
	}
	
	public TenantInfo getTenantInfo(String tenantId){
		 JdbcTemplate jdbc = new JdbcTemplate(dataSource);
	     String sql = "select * from tenant where tenant_guid='"+tenantId+"'";
	        try {
	        	TenantInfo info = jdbc.query(sql, new ResultSetExtractor<TenantInfo>(){

					public TenantInfo extractData(ResultSet result)
							throws SQLException, DataAccessException {
						TenantInfo info = new TenantInfo();
						if(result.next()){
							info.setTenantId(result.getString("tenant_guid"));
							info.setOutboundType(result.getInt("outbound_end_point_type"));
							info.setOutboundUrl(result.getString("outbound_url"));
						}
						else
						{
							return null;
						}
						System.out.println("tenant found, outbound type:"+info.getOutboundType()+",url:"+info.getOutboundUrl());
						return info;
					}
	            });
	        	return info;
	        } catch (Throwable e) {
	        	e.printStackTrace();
	        }
	        return null;
	}
	
	 public DataSource getDataSource() {
	        return dataSource;
	    }

	    public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }	
}

