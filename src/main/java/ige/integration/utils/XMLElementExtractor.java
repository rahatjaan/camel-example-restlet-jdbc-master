package ige.integration.utils;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLElementExtractor {

	public static String extractXmlElementValue(String xml, String element) {

		String value = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName(element);
			Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				value = eElement.getTextContent();
				System.out.println(element + " : " + value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
