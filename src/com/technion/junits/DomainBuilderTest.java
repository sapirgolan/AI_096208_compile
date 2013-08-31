package com.technion.junits;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Parameter;
import com.technion.ai.dao.Predicat;
import com.technion.ai.dao.Predicates;
import com.technion.ai.dao.Types;
import com.technion.builder.DomainBuilder;

public class DomainBuilderTest {
	
	private DomainBuilder classUnderTest;

	@Before
	public void setUp() throws Exception {
		Domain problemDomain = new Domain();
		problemDomain.setName("junit");
		problemDomain.setTypes(new Types());
		problemDomain.setPredicates(new Predicates());
		classUnderTest = new DomainBuilder(problemDomain); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDomainNameExists() {
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("Domain representation doesn't contain domian name", domainStr.contains("junit"));
		Assert.assertTrue("Domain representation doesn't (", domainStr.contains("(define"));
	}
	
	@Test
	public void testDomainTypesExists() {
		Domain problemDomain = classUnderTest.getProblemDomain();
		Types types = new Types();
		types.getType().add("type_1");
		types.getType().add("type_2");
		problemDomain.setTypes(types);
		
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("", domainStr.contains("type_2"));
		Assert.assertTrue("", domainStr.contains("(:types type_1 type_2)"));
	}
	
	@Test
	public void testBuildPredicates() {
		//preparing for building...
		Types types = new Types();
		types.getType().add("type_1");
		
		Domain problemDomain = classUnderTest.getProblemDomain();
		problemDomain.setTypes(types);
		Predicat predicat = new Predicat();
		predicat.setName("predicat_1");
		//add parameter
		Parameter parameterOne = new Parameter();
		parameterOne.setName("?x");
		parameterOne.setType("type_1");
		Parameter parameterTwo = new Parameter();
		parameterTwo.setName("?z");
		parameterTwo.setType("type_2");
		predicat.getParameter().add(parameterOne);
		predicat.getParameter().add(parameterTwo);
		//add predicat
		problemDomain.getPredicates().getPredicat().add(predicat);
		
		//
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("", domainStr.contains("(:predicates"));
		Assert.assertTrue("", domainStr.contains( "( predicat_1 ?x - type_1 ?z - type_2 )" ));
	}

}
