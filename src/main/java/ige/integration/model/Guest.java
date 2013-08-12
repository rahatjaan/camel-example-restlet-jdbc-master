package ige.integration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Guest {
	@XmlElement
	private String name;
	@XmlElement
	private String address;
	@XmlElement
	private String phoneNum;
	@XmlElement
	private int id;
	
	public int getId() {
		return id;
	}

    @XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	@XmlElement
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
