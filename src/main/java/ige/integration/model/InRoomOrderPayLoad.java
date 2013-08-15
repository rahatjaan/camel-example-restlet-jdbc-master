package ige.integration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({TenantInfo.class})
public class InRoomOrderPayLoad {

	
	private String payload;
	private Object objectPayload;
	private TenantInfo tenant;
	
	public InRoomOrderPayLoad(String payload,TenantInfo tenant) {
		super();
		this.payload = payload;
		this.tenant = tenant;
	}
	
	public InRoomOrderPayLoad() {
		
	}
	public String getPayload() {
		return payload;
	}
	
	@XmlElement
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public Object getObjectPayload() {
		return objectPayload;
	}
	@XmlElement
	public void setObjectPayload(Object objectPayload) {
		this.objectPayload = objectPayload;
	}
	public TenantInfo getTenant() {
		return tenant;
	}
	@XmlElement
	public void setTenant(TenantInfo tenant) {
		this.tenant = tenant;
	}
	
	@Override
	public String toString() {
		return payload;
	}	
}
