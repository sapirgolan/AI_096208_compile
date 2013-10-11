package com.technion.junits;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Predicat;
import com.technion.ai.wrappers.DomainWrapper;
import com.technion.ai.wrappers.PredicateWrapper;
import com.technion.compile.businessLayer.PreconditionBusinessLayer;
import com.technion.utils.JunitUtils;

public class PreconditionBusinessLayerTest extends AbstractTest{

	Domain domain = new Domain();
	Domain problemDomain = spy(domain);
	PreconditionBusinessLayer classUnderTest;
	
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new PreconditionBusinessLayer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildNewPridicates() {
		int numberOfPredicates = 1;
		int highestEffectLevel = 1;
		List<Predicat> predicatesWithParams = JunitUtils.createNPredicatesWithMParam( numberOfPredicates, 2 );
		Assert.assertEquals( numberOfPredicates, predicatesWithParams.size() );
		doReturn( highestEffectLevel ).when(problemDomain).getEffectsNumber();
		doReturn(predicatesWithParams).when(problemDomain).getPredicat();
		DomainWrapper domainWrapper = new DomainWrapper(problemDomain);
		
		HashMap<Integer, List<PredicateWrapper>> newPredicatesMap = classUnderTest.buildNewPredicates(domainWrapper);
		Assert.assertNotNull( newPredicatesMap );
		
		Iterator<Entry<Integer, List<PredicateWrapper>>> iterator = newPredicatesMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, List<PredicateWrapper>> entry = (Map.Entry<Integer, List<PredicateWrapper>>) iterator.next();
			List<PredicateWrapper> list = entry.getValue();
			for (PredicateWrapper predicateWrapper : list) {
				String levelString = getLevelString(entry.getKey());
				String predicateName = predicateWrapper.getName();
				if (!predicateName.startsWith("Open")) {
					Assert.assertTrue(predicateName.contains(levelString));
				}
			}
		}

		//check that there are 'highestEffectLevel + 1' levels of predicates
		Assert.assertEquals( highestEffectLevel + 1, newPredicatesMap.keySet().size() );
		
		for (int i = 0; i <= highestEffectLevel; i++) {
			List<PredicateWrapper> predicatesLevel = newPredicatesMap.get(Integer.valueOf(i));
			//check each level contain all original predicates + one new predicate
			Assert.assertEquals( numberOfPredicates +1, predicatesLevel.size() );
			//check each level contain all  predicate named 'Open'+'level Index
			Assert.assertEquals(getOpenActionName(i), predicatesLevel.get( predicatesLevel.size()-1 ).getName());
		}
	}

}
