package com.technion.junits;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Predicat;
import com.technion.ai.wrappers.PredicateWrapper;
import com.technion.compile.ore.PredicateMap;

public class PredicateMapTest {

	private PredicateMap classUnderTest;
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new PredicateMap();
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testGetPredicates() {
		List<Predicat> list;
		list = classUnderTest.getPredicates(null, null);
		Assert.assertTrue(list.isEmpty());
		
		list = classUnderTest.getPredicates(null, 1);
		Assert.assertTrue(list.isEmpty());
		
		list = classUnderTest.getPredicates(new Predicat(), null);
		Assert.assertTrue(list.isEmpty());
		
		list = classUnderTest.getPredicates(new Predicat(), 4);
		Assert.assertTrue(list.isEmpty());
	}*/
	
	@Test
	public void testGetPredicates() {
		PredicateWrapper Predicte;
		Predicte = classUnderTest.getPredicate(null, null);
		Assert.assertNull(Predicte);
		
		Predicte = classUnderTest.getPredicate(null, 1);
		Assert.assertNull(Predicte);
		
		Predicte = classUnderTest.getPredicate(new PredicateWrapper(new Predicat()), null);
		Assert.assertNull(Predicte);
		
		Predicte = classUnderTest.getPredicate(new PredicateWrapper(new Predicat()), 4);
		Assert.assertNull(Predicte);
	}

/*	@Test
	public void testAddPredicates() {
		classUnderTest.addPredicates(null, null, null);
		classUnderTest.addPredicates(null, null, Arrays.asList(new Predicat()));
		classUnderTest.addPredicates(null, 0, null);
		classUnderTest.addPredicates(new Predicat(), null, null);
		
		Predicat key = new Predicat();
		Predicat predicat1 = new Predicat();
		Predicat predicat2 = new Predicat();
		classUnderTest.addPredicates(key, 0, Arrays.asList(predicat1, predicat2));
		List<Predicat> list = classUnderTest.getPredicates(key, 0);
		Assert.assertTrue(list.size()==2);
		Assert.assertTrue(list.contains(predicat1));
		Assert.assertTrue(list.contains(predicat2));
	}*/

	@Test
	public void testAddPredicate() {
		classUnderTest.addPredicate(null, null, null);
		classUnderTest.addPredicate(null, null, new PredicateWrapper(new Predicat()));
		classUnderTest.addPredicate(null, 0, null);
		classUnderTest.addPredicate(new PredicateWrapper(new Predicat()), null, null);
		
		Predicat key = new Predicat();
		PredicateWrapper keyWrapper = new PredicateWrapper(key);
		Predicat predicat1 = new Predicat();
		PredicateWrapper wrapper = new PredicateWrapper(predicat1);
		
		classUnderTest.addPredicate(keyWrapper, 0, wrapper);
		PredicateWrapper retrivedPredicate = classUnderTest.getPredicate(keyWrapper,0);
		Assert.assertEquals(retrivedPredicate, wrapper);
	}
	
	@Test
	public void testAddOpenPredicate(){
		classUnderTest.addOpenPredicate(null, null);
		Predicat predicat = new Predicat();
		PredicateWrapper wrapper = new PredicateWrapper(predicat);
		classUnderTest.addOpenPredicate(0, wrapper);
		PredicateWrapper openPredicate = classUnderTest.getOpenPredicate(0);
		Assert.assertEquals(wrapper, openPredicate);
	}
	
	@Test
	public void testGetOpenPredicate(){
		PredicateWrapper openPredicate;
		openPredicate = classUnderTest.getOpenPredicate(null);
		Assert.assertNull(openPredicate);
		openPredicate = classUnderTest.getOpenPredicate(0);
		Assert.assertNull(openPredicate);
	}
	
	@Test
	public void getOpenPredicatesAboveLevel(){
		List<PredicateWrapper> predicatesAboveLevel;
		predicatesAboveLevel = classUnderTest.getOpenPredicatesAboveLevel(null);
		Assert.assertNotNull(predicatesAboveLevel);
		Assert.assertTrue(predicatesAboveLevel.isEmpty());
		//Adds 3 predicates
		classUnderTest.addOpenPredicate(0, new PredicateWrapper(new Predicat()));
		classUnderTest.addOpenPredicate(1, new PredicateWrapper(new Predicat()));
		classUnderTest.addOpenPredicate(2, new PredicateWrapper(new Predicat()));
		
		predicatesAboveLevel = classUnderTest.getOpenPredicatesAboveLevel(0);
		Assert.assertEquals(2, predicatesAboveLevel.size());
		predicatesAboveLevel = classUnderTest.getOpenPredicatesAboveLevel(1);
		Assert.assertEquals(1, predicatesAboveLevel.size());
		predicatesAboveLevel = classUnderTest.getOpenPredicatesAboveLevel(88);
		Assert.assertTrue(predicatesAboveLevel.isEmpty());
	}

}
