package com.technion.junits;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Types;
import com.technion.builder.DomainBuilder;

public class DomainBuilderTest {
	
	private DomainBuilder classUnderTest;

	@Before
	public void setUp() throws Exception {
		Domain problemDomain = new Domain();
		problemDomain.setName("junit");
		problemDomain.setTypes(new Types());
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

}
