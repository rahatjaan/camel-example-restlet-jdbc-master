package ige.integration.service;

import ige.integration.model.TenantInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class PopulateTenantInfo {

	private DataSource dataSource;
	
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
