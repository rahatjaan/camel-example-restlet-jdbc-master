package ige.integration.processes;

import ige.integration.model.InRoomOrderPayLoad;
import ige.integration.model.TenantInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class IntegrationProcessor implements Processor {

	private DataSource dataSource;
	
	public void process(Exchange exchange) throws Exception {
		InRoomOrderPayLoad payload = populateTenantInfo(exchange);
		exchange.getOut().setBody(payload);
		System.out.println("IntegrationProcessor \nout: "+payload.getPayload());

	}
	
	
	public InRoomOrderPayLoad populateTenantInfo(Exchange exchange){
		String value = exchange.getIn().getBody().toString();
		System.out.println("PopulateTenantInfo xml \n"+value);
		InRoomOrderPayLoad payload = new InRoomOrderPayLoad(value,getTenantInfo("test_guid"));
		System.out.println("PAYLOAD HERE IS: "+payload.getTenant().getTenantId());		
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
							info.setOutboundType(result.getInt("outbound_end_point_type")+"");
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

