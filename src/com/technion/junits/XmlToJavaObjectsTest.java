package com.technion.junits;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.compile.converter.XmlToJavaObjects;

public class XmlToJavaObjectsTest {

	private static final String DOMAIN_XML_FILE = "resources/robot_domain_9.xml";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testXmlToJavaConvert() throws FileNotFoundException, JAXBException {
		XmlToJavaObjects.XmlToJavaConvert(DOMAIN_XML_FILE);
	}

}
