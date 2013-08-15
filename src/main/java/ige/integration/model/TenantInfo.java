package ige.integration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class TenantInfo {
	
	
	private String tenantId;
	
	private String outboundType;
	
	private String outboundUrl;
	
	private String outboundUserName;
	
	private String outboundPassword;
	
	public TenantInfo(String tenantId, String outboundType,
			String outboundUrl, String outboundUserName, String outboundPassword) {
		super();
		this.tenantId = tenantId;
		this.outboundType = outboundType;
		this.outboundUrl = outboundUrl;
		this.outboundUserName = outboundUserName;
		this.outboundPassword = outboundPassword;
	}
	
	public TenantInfo() {
	}


	public String getTenantId() {
		return tenantId;
	}
	@XmlElement
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getOutboundType() {
		return outboundType;
	}
	@XmlElement
	public void setOutboundType(String outboundType) {
		this.outboundType = outboundType;
	}
	public String getOutboundUrl() {
		return outboundUrl;
	}
	@XmlElement
	public void setOutboundUrl(String outboundUrl) {
		this.outboundUrl = outboundUrl;
	}
	public String getOutboundUserName() {
		return outboundUserName;
	}
	@XmlElement
	public void setOutboundUserName(String outboundUserName) {
		this.outboundUserName = outboundUserName;
	}
	public String getOutboundPassword() {
		return outboundPassword;
	}
	@XmlElement
	public void setOutboundPassword(String outboundPassword) {
		this.outboundPassword = outboundPassword;
	}
	
	
}
