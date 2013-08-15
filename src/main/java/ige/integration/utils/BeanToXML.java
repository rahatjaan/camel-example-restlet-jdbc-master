package ige.integration.utils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class BeanToXML {
	public static String readObject(Class classs,Object object) {
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(classs);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter xml = new StringWriter();
			// jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(object, xml);
			// System.out.println(xml.toString());
			return xml.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String readObject(Object object) {
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(DataBean.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter xml = new StringWriter();
			// jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(object, xml);
			// System.out.println(xml.toString());
			return xml.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}
