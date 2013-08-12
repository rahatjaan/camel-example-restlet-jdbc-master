package ige.integration.service;

import ige.integration.model.InRoomOrderPayLoad;
import ige.integration.model.TenantInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class PopulateTenantInfo {

	private DataSource dataSource;
	
	public InRoomOrderPayLoad populateTenantInfo(Exchange exchange){
		String value = exchange.getIn().getBody().toString();
		System.out.println("PopulateTenantInfo xml \n"+value);
		InRoomOrderPayLoad payload = new InRoomOrderPayLoad(value,exchange,getTenantInfo("test"));
		return payload;
	}
	
	public TenantInfo getTenantInfo(String tenantId){
		 JdbcTemplate jdbc = new JdbcTemplate(dataSource);
	     String sql = "select * from tenant where tenantguid="+tenantId;
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
