package ige.integration.utils;

import ige.integration.model.InRoomOrderPayLoad;
import ige.integration.model.TenantInfo;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class BeanToXML {
public static String readObject(Object object) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(TenantInfo.class);/*
			jaxbContext = JAXBContext.newInstance(object.getPayload().getClass());
			jaxbContext = JAXBContext.newInstance(object.getObjectPayload().getClass());*/
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			StringWriter xml = new StringWriter();
			//jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(object, xml);
			//System.out.println(xml.toString());
			return xml.toString();
	 
		      } catch (JAXBException e) {
				e.printStackTrace();
				return null;
		      }
	}
}
