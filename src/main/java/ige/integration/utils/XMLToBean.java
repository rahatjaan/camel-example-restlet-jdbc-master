package ige.integration.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XMLToBean {
	public static DataBean readXML(String args){
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(DataBean.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
			StringReader reader = new StringReader(args);
			DataBean dataBean = (DataBean) unmarshaller.unmarshal(reader);
			return dataBean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String [] args){
		DataBean obj = new DataBean(1,"rahat","isb","123");
		DataBean obj1 = readXML(BeanToXML.readObject(obj));
		System.out.println(obj1.getName());
	}
}
