package com.technion.junits;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Action;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Parameter;
import com.technion.ai.dao.Predicat;
import com.technion.ai.dao.Types;
import com.technion.builder.DomainBuilder;

public class DomainBuilderTest {
	
	private DomainBuilder classUnderTest;

	@Before
	public void setUp() throws Exception {
		Domain problemDomain = new Domain();
		problemDomain.setName("junit");
		problemDomain.setTypes(new Types());
//		problemDomain.setActions(new Actions());
//		problemDomain.setPredicates(new Predicates());
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
		Parameter parameterOne = createParmeter("?x", "type_1");
		Parameter parameterTwo = createParmeter("?z", "type_2");
		Predicat predicat = createPredicat(Arrays.asList(parameterOne, parameterTwo),"predicat_1");
		//add predicat
		problemDomain.getPredicats().add(predicat);
		
		//
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("", domainStr.contains("(:predicates"));
		Assert.assertTrue("", domainStr.contains( "( predicat_1 ?x - type_1 ?z - type_2 )" ));
	}

	private Predicat createPredicat(List<Parameter> parameters, String predicatName) {
		Predicat predicat = new Predicat();
		predicat.setName(predicatName);
		//add parameter
		for (Parameter parameter : parameters) {
			predicat.getParameter().add(parameter);
		}
		return predicat;
	}

	private Parameter createParmeter(String name, String type) {
		Parameter parameterOne = new Parameter();
		parameterOne.setName(name);
		parameterOne.setType(type);
		return parameterOne;
	}

	@Test
	//test writeActionParameters()
	public void testBuldActions() {
		Action action = new Action();
		action.setName("fix");
		classUnderTest.getProblemDomain().getActions().add(action);
		
		Parameter parameterOne = createParmeter("?w", "type_1");
		Parameter parameterTwo = createParmeter("?x", "type_1");
		
		Parameter parameterThree = createParmeter("?y", "type_2");
		Parameter parameterFour = createParmeter("?z", "type_1");
		
		Parameter parameterFive = createParmeter("?a", "type_2");
		
		Predicat predicatOne = createPredicat(Arrays.asList(parameterOne, parameterTwo),"predicat_1");
		Predicat predicatTwo = createPredicat(Arrays.asList(parameterThree, parameterFour),"predicat_1");
		Predicat predicatThree = createPredicat(Arrays.asList(parameterFive),"predicat_1");
		action.getPreconditions().addAll(Arrays.asList(predicatOne, predicatTwo, predicatThree));
		
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("", domainStr.contains("(:action fix"));
		Assert.assertTrue("", domainStr.contains( ":parameters(" ));
		Assert.assertTrue("", domainStr.contains( "?w ?x ?z - type_1" ));
		Assert.assertTrue("", domainStr.contains( "?y ?a - type_2" ));
	}
	
	@Test
	//WriteActionPrecondition()
	public void testBuldActions2(){
		Action action = new Action();
		action.setName("fix");
		classUnderTest.getProblemDomain().getActions().add(action);
		
		Parameter parameterOne = createParmeter("?w", "soldier");
		Parameter parameterTwo = createParmeter("?x", "soldier");
		
		Parameter parameterThree = createParmeter("?y", "policeman");
		
		Parameter parameterFive = createParmeter("?a", "man");
		
		Predicat predicatOne = createPredicat(Arrays.asList(parameterOne, parameterTwo),"move");
		Predicat predicatTwo = createPredicat(Arrays.asList(parameterThree),"drop");
		Predicat predicatThree = createPredicat(Arrays.asList(parameterFive),"delete");
		predicatThree.setIsPositive(false);
		action.getPreconditions().addAll(Arrays.asList(predicatOne, predicatTwo, predicatThree));
		
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("", domainStr.contains("(:action fix"));
		Assert.assertTrue("", domainStr.contains( ":precondition ( AND" ));
		Assert.assertTrue("", domainStr.contains( "( move ?w ?x )" ));
		Assert.assertTrue("", domainStr.contains( "( drop ?y )" ));
		Assert.assertTrue("", domainStr.contains( "(NOT ( delete ?a ) )" ));
	}
	
	@Test
	//writeActionEffects()
	public void testBuldActions3(){
		fail("Not yet implimented");
	}
	
}

