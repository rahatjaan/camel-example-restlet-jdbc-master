package ige.integration.utils;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.camel.Exchange;


@XmlRootElement
public class DataBean {
	private int id;
	private String name;
	private String address;
	private String phoneNum;
	private String[] properties = {"id","name","address","phoneNum"};
	
	public DataBean(){
		
	}
	
	public DataBean(int id, String name, String address, String phoneNum){
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
	}

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

	public Map newData(int id, String name, String address, String phoneNum) {
        Map data = new HashMap();
        data.put("id", id);
        data.put("Name", name);
        data.put("Address", address);
        data.put("PhoneNum", phoneNum);
        return data;
    }
    
    public Map newData(int id) {
        Map data = new HashMap();
        data.put("id", id);
        return data;
    }
    
    public Map newData(String id) {
        Map data = new HashMap();
        data.put("id", Integer.parseInt(id));
        return data;
    }
    
    public String processOrder(Exchange data) {
    	String value = data.getIn().getBody().toString();
    	Map<String,Object> obj = new HashMap<String,Object>();
    	int ite = 0;
    	while(value.contains("=")){
    		int ind1 = value.indexOf('=');
    		int ind2 = value.indexOf(',', ind1);
    		if(-1 == ind2){
    			ind2 =  value.indexOf('}', ind1);
    		}
			obj.put(properties[ite], value.substring(ind1+1, ind2));
    		ite++;
    		value = value.substring(ind2+1);
    	}
    	DataBean object = new DataBean(Integer.parseInt(obj.get("id").toString()),obj.get("name").toString(),obj.get("address").toString(),obj.get("phoneNum").toString());
        return BeanToXML.readObject(object);
    }
    
    public String checkOrder(Exchange data) {
    	String body = data.getIn().getBody().toString();
    	DataBean obj = XMLToBean.readXML(body);
    	obj.setId(2);
    	
    	String xml = BeanToXML.readObject(obj);
    	System.out.println("XML HERE IS: "+xml);
    	data.getOut().setBody(xml);
    	data.getOut().setHeaders(data.getIn().getHeaders());
    	return xml;
    }
}
