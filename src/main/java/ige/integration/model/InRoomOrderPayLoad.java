package ige.integration.model;


public class InRoomOrderPayLoad {

	private String payload;
	private Object objectPayload;
	private TenantInfo tenant;
	
	public InRoomOrderPayLoad(String payload, Object objectPayload,
			TenantInfo tenant) {
		super();
		this.payload = payload;
		this.objectPayload = objectPayload;
		this.tenant = tenant;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public Object getObjectPayload() {
		return objectPayload;
	}
	public void setObjectPayload(Object objectPayload) {
		this.objectPayload = objectPayload;
	}
	public TenantInfo getTenant() {
		return tenant;
	}
	public void setTenant(TenantInfo tenant) {
		this.tenant = tenant;
	}
	
}
