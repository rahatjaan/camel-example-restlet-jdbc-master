package ige.integration.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class InRoomOrder {
	@XmlElement
	private String tenantGuid;	
	@XmlElement
	private Guest guest;
	@XmlElement
	private List<OrderItem> orderItems;
	
	public InRoomOrder(){
		
	}	
    public String getTenantGuid() {
		return tenantGuid;
	}

	public void setTenantGuid(String tenantGuid) {
		this.tenantGuid = tenantGuid;
	}

	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
