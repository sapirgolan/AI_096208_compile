package com.technion.compile.main;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import com.technion.ai.wrappers.DomainWrapper;
import com.technion.compile.converter.XmlToJavaObjects;
import com.technion.compile.core.DomainBuilder;
import com.technion.utils.Utils;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DomainWrapper problemDomain = XmlToJavaObjects.XmlToJavaConvert("resources/robot_domain_9.xml");
			//TODO; change DomainBuilder logic
			DomainBuilder domainBuilder = new DomainBuilder(problemDomain.getDomain());
			StringBuilder stringBuilder = domainBuilder.buildDomain();
			Utils.writeTextFile("problem.pddl", stringBuilder.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
