package ige.integration.transformer;

import ige.integration.utils.DataBean;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class BeanToXML {
public static String readObject(DataBean object) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
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
