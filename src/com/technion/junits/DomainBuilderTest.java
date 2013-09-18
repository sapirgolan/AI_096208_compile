package com.technion.junits;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Action;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Effect;
import com.technion.ai.dao.Parameter;
import com.technion.ai.dao.Predicat;
import com.technion.ai.dao.Types;
import com.technion.builder.DomainBuilder;
import com.technion.utils.JunitUtils;

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
		
		Assert.assertTrue("Number of opening parentheses is diffrent than number of closeing parentheses", countParentheses(domainStr) );
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
		
		Assert.assertTrue("Number of opening parentheses is diffrent than number of closeing parentheses", countParentheses(domainStr) );
	}
	
	@Test
	public void testBuildPredicates() {
		//preparing for building...
		Types types = new Types();
		types.getType().add("type_1");
		
		Domain problemDomain = classUnderTest.getProblemDomain();
		problemDomain.setTypes(types);
		Parameter parameterOne = JunitUtils.createParmeter("?x", "type_1");
		Parameter parameterTwo = JunitUtils.createParmeter("?z", "type_2");
		Predicat predicat = JunitUtils.createPredicat(Arrays.asList(parameterOne, parameterTwo),"predicat_1");
		//add predicate
		problemDomain.getPredicat().add(predicat);
		
		//
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("", domainStr.contains("(:predicates"));
		Assert.assertTrue("", domainStr.contains( "( predicat_1 ?x - type_1 ?z - type_2 )" ));
		
		Assert.assertTrue("Number of opening parentheses is diffrent than number of closeing parentheses", countParentheses(domainStr) );
	}

	@Test
	//test writeActionParameters()
	public void testBuldActions() {
		Action action = new Action();
		action.setName("fix");
		classUnderTest.getProblemDomain().getAction().add(action);
		
		Parameter parameterOne = JunitUtils.createParmeter("?w", "type_1");
		Parameter parameterTwo = JunitUtils.createParmeter("?x", "type_1");
		
		Parameter parameterThree = JunitUtils.createParmeter("?y", "type_2");
		Parameter parameterFour = JunitUtils.createParmeter("?z", "type_1");
		
		Parameter parameterFive = JunitUtils.createParmeter("?a", "type_2");
		
		Predicat predicatOne = JunitUtils.createPredicat(Arrays.asList(parameterOne, parameterTwo),"predicat_1");
		Predicat predicatTwo = JunitUtils.createPredicat(Arrays.asList(parameterThree, parameterFour),"predicat_1");
		Predicat predicatThree = JunitUtils.createPredicat(Arrays.asList(parameterFive),"predicat_1");
		action.getPredicat().addAll(Arrays.asList(predicatOne, predicatTwo, predicatThree));
		
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("", domainStr.contains("(:action fix"));
		Assert.assertTrue("", domainStr.contains( ":parameters(" ));
		Assert.assertTrue("", domainStr.contains( "?w ?x ?z - type_1" ));
		Assert.assertTrue("", domainStr.contains( "?y ?a - type_2" ));
		
		Assert.assertTrue("Number of opening parentheses is diffrent than number of closeing parentheses", countParentheses(domainStr) );
	}
	
	@Test
	//WriteActionPrecondition()
	public void testBuldActions2(){
		Action action = new Action();
		action.setName("fix");
		classUnderTest.getProblemDomain().getAction().add(action);
		
		Parameter parameterOne = JunitUtils.createParmeter("?w", "soldier");
		Parameter parameterTwo = JunitUtils.createParmeter("?x", "soldier");
		
		Parameter parameterThree = JunitUtils.createParmeter("?y", "policeman");
		
		Parameter parameterFive = JunitUtils.createParmeter("?a", "man");
		
		Predicat predicatOne = JunitUtils.createPredicat(Arrays.asList(parameterOne, parameterTwo),"move");
		Predicat predicatTwo = JunitUtils.createPredicat(Arrays.asList(parameterThree),"drop");
		Predicat predicatThree = JunitUtils.createPredicat(Arrays.asList(parameterFive),"delete");
		predicatThree.setIsPositive(false);
		action.getPredicat().addAll(Arrays.asList(predicatOne, predicatTwo, predicatThree));
		
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("", domainStr.contains("(:action fix"));
		Assert.assertTrue("", domainStr.contains( ":precondition ( AND" ));
		Assert.assertTrue("", domainStr.contains( "( move ?w ?x )" ));
		Assert.assertTrue("", domainStr.contains( "( drop ?y )" ));
		Assert.assertTrue("", domainStr.contains( "(NOT ( delete ?a ) )" ));
		
		Assert.assertTrue("Number of opening parentheses is diffrent than number of closeing parentheses", countParentheses(domainStr) );
	}
	
	@Test
	//writeActionEffects()
	public void testBuldActions3(){
		Action fixAction = new Action(),
				moveAction = new Action();
		fixAction.setName("fix");
		moveAction.setName("move");
		
		classUnderTest.getProblemDomain().getAction().addAll(Arrays.asList(fixAction, moveAction));
		
		Effect fixEffectOne = new Effect(),
				fixEffectTwo = new Effect(),
				moveEffectOne = new Effect();
		fixAction.getEffect().addAll(Arrays.asList(fixEffectOne, fixEffectTwo));
		moveAction.getEffect().add(moveEffectOne);
		fixEffectOne.setFValue(0);
		fixEffectTwo.setFValue(1);
		
		Parameter parameterOne = JunitUtils.createParmeter("?w", "soldier");
		Predicat predicatOne = JunitUtils.createPredicat(Arrays.asList(parameterOne),"move");
		Parameter parameterThree = JunitUtils.createParmeter("?y", "policeman");
		Predicat predicatTwo = JunitUtils.createPredicat(Arrays.asList(parameterThree),"drop");
		predicatTwo.setIsPositive(false);
		moveEffectOne.getPredicat().addAll(Arrays.asList(predicatOne, predicatTwo));
		
		Parameter parameterFive = JunitUtils.createParmeter("?a", "man");
		Predicat predicatThree = JunitUtils.createPredicat(Arrays.asList(parameterFive),"delete");
		fixEffectOne.getPredicat().add(predicatThree);
		
		Parameter parameterTwo = JunitUtils.createParmeter("?x", "soldier");
		Predicat predicatFour = JunitUtils.createPredicat(Arrays.asList(parameterTwo),"jump");
		fixEffectTwo.getPredicat().add(predicatFour);
		
		StringBuilder buildDomain = classUnderTest.buildDomain();
		String domainStr = buildDomain.toString();
		Assert.assertTrue("", domainStr.contains("(:action fix"));
		Assert.assertTrue("", domainStr.contains( ":effect ( AND" ));
		Assert.assertTrue("", domainStr.contains( "( move ?w )" ));
		Assert.assertTrue("", domainStr.contains( "(NOT ( drop ?y ) )" ));
		
		Assert.assertTrue("", domainStr.contains("(:action move"));
		Assert.assertTrue("", domainStr.contains( ":effect ( AND" ));
		Assert.assertTrue("", domainStr.contains( "( delete ?a )" ));
		Assert.assertTrue("", domainStr.contains( "( jump ?x )" ));
		
		Assert.assertTrue("Number of opening parentheses is diffrent than number of closeing parentheses", countParentheses(domainStr) );
	}
	
	private boolean countParentheses(String domainStr) {
		int countOpeningParentheses = 0,
				countCloseingParentheses = 0;
		for (int i = 0; i < domainStr.length(); i++) {
			if ( domainStr.charAt(i) == '(' ) {
				countOpeningParentheses++;
			} else if ( domainStr.charAt(i) == ')' ) {
				countCloseingParentheses++;
			}
		}
		return countCloseingParentheses == countOpeningParentheses;
	}
	
	@Test
	public void getEffectsNumberTest() {
		Action fixAction = new Action(),
				moveAction = new Action();
		fixAction.setName("fix");
		moveAction.setName("move");
		
		classUnderTest.getProblemDomain().getAction().addAll(Arrays.asList(fixAction, moveAction));
		
		Effect fixEffectOne = new Effect(),
				fixEffectTwo = new Effect(),
				moveEffectOne = new Effect();
		fixAction.getEffect().addAll(Arrays.asList(fixEffectOne, fixEffectTwo));
		moveAction.getEffect().add(moveEffectOne);
		fixEffectOne.setFValue(0);
		fixEffectTwo.setFValue(1);
		
		moveEffectOne.setFValue(4);
		int effectsNumber = classUnderTest.getProblemDomain().getEffectsNumber();
		Assert.assertEquals("", 4, effectsNumber);
	}
}

