package org.mu.cloudservice.utility;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMUtil {
	
	public static List<Element> getComplexElements(Element root, String path) {
		return getComplexElements(root, path, true);
	}
	
	public static List<Element> getComplexElements(Element root, String path, boolean immediate) {
		Element element = root;
		String[] names = path.split(";");
		for (int i = 0; i < names.length; i++) {
			if (i != names.length - 1) {
				element = getElement(element, names[i]);
			} else {
				return getElements(element, names[i]);
			}
		}
		return null;
	}
	
	public static List<Element> getElements(Element root, String name) {
		return getElements(root, name, true);
	}
	
	public static List<Element> getElements(Element root, String name, boolean immediate) {
		List<Element> elements = new ArrayList<>();
		NodeList list = root.getElementsByTagName(name);
		for (int i = 0; i < list.getLength(); i++) {
			if (!immediate || list.item(i).getParentNode().equals(root)) {
				elements.add((Element) list.item(i));
			}
		}
		return elements;
	}
	
	public static Element getComplexElement(Element root, String path, boolean immediate) {
		String[] names = path.split(".");
		Element element = root;
		for (String name : names) {
			element = getElement(element, name, immediate);
		}
		return element;
	}
	
 	public static Element getElement(Element root, String name) {
		return getElement(root, name, true);
	}

 	public static Element getElement(Element root, String name, boolean immediate) {
 		NodeList nodeList = root.getElementsByTagName(name);
 		if (!immediate) {
 			return (Element) nodeList.item(0);
 		}
 		for (int i = 0; i < nodeList.getLength(); i++) {
 			if (nodeList.item(i).getParentNode().equals(root)) {
 				return (Element) nodeList.item(i);
 			}
 		}
 		return null;
	}
 	
 	public static String getStringValue(Element root, String name) {
 		return getElement(root, name).getTextContent();
 	}
 	
 	public static int getIntValue(Element root, String name) {
 		return Integer.parseInt(getElement(root, name).getTextContent());
 	}
 	
 	public static String getOptionalStringValue(Element root, String name) {
 		String value = null;
 		try {
 			value = getElement(root, name).getTextContent();
 		} catch (Exception e) {
 			value = null;
 		}
 		return value;
 	}
 	
 	public static int getOptionalIntValue(Element root, String name) {
 		int value = 0;
 		try {
 			value = Integer.parseInt(getElement(root, name).getTextContent());
 		} catch (Exception e) {
 			value = 0;
 		}
 		return value;
 	}
	
}
