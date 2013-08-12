package ige.integration.service;

import javax.sql.DataSource;

import ige.integration.model.InRoomOrderPayLoad;
import ige.integration.model.TenantInfo;

import org.apache.camel.Exchange;
import org.springframework.jdbc.core.JdbcTemplate;

public class PopulateTenantInfo {

	private DataSource dataSource;
	
	public InRoomOrderPayLoad populateTenantInfo(Exchange exchange){
		String value = exchange.getIn().getBody().toString();
		System.out.println("PopulateTenantInfo xml \n"+value);
		InRoomOrderPayLoad payload = new InRoomOrderPayLoad(value,exchange,null);
		
		return payload;
	}
	
	public TenantInfo getTenantInfo(String tenantId){
		 JdbcTemplate jdbc = new JdbcTemplate(dataSource);
	     String sql = "";
	        try {
	            jdbc.execute("select * from tenant where tenantguid="+tenantId);
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
