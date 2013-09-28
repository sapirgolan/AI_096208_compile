package com.technion.junits;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Predicat;
import com.technion.ai.wrappers.PredicateWrapper;
import com.technion.utils.JunitUtils;

public class PredicateWrapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertPredicatesToPredicateWappers() {
		int numberOfPredicates = 3;
		List<Predicat> predicates = JunitUtils.createNPredicatesWithMParam(numberOfPredicates, numberOfPredicates);
		List<PredicateWrapper> predicateWappers = PredicateWrapper.convertPredicatesToPredicateWappers(predicates);
		for (int i = 0; i < numberOfPredicates; i++) {
			Predicat predicat = predicates.get(i);
			PredicateWrapper predicateWrapper = predicateWappers.get(i);
			
			Assert.assertEquals( predicat.getParameter().size() , predicateWrapper.getParameter().size() );
			Assert.assertEquals( predicat.getName(), predicateWrapper.getName() );
		}
	}

}
