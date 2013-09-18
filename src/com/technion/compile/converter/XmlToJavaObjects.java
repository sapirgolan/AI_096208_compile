package com.technion.compile.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.technion.ai.dao.Domain;
import com.technion.ai.dao.ObjectFactory;

public class XmlToJavaObjects {

/*  *//**
   * @param args
   * @throws JAXBException 
 * @throws FileNotFoundException 
   *//*
  public static void main(String[] args) throws JAXBException, FileNotFoundException {
    JAXBElement<Domain> unmarshalledObject = XmlToJavaConvert();

    //4. Get the instance of the required JAXB Root Class from the JAXBElement.
    Domain domain = unmarshalledObject.getValue();
    String domainName = domain.getName();
    List<Predicat> predicat = domain.getPredicats();
    Types types = domain.getTypes();
    List<Action> actions = domain.getActions();

    //Obtaining all the required data from the JAXB Root class instance.
    System.out.println("Domain Name: "+domainName);
  }*/

/**
 * @return
 * @throws JAXBException
 * @throws FileNotFoundException
 */
  public static Domain XmlToJavaConvert(String filename) throws JAXBException,
  FileNotFoundException {
	  //1. We need to create JAXContext instance
	  JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);

	  //2. Use JAXBContext instance to create the Unmarshaller.
	  Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

	  //3. Use the Unmarshaller to unmarshal the XML document to get an instance of JAXBElement.
	  File domainFile = new File( filename );
	  FileInputStream inputStream = new FileInputStream(domainFile);
	  Source source = new StreamSource(inputStream);
	  JAXBElement<Domain> unmarshalledObject = unmarshaller.unmarshal(source, Domain.class);

	  //4. Get the instance of the required JAXB Root Class from the JAXBElement.
	  Domain domain = unmarshalledObject.getValue();
	  
	  return domain;
  }

}