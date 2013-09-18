package com.technion.junits;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Predicat;
import com.technion.compile.businessLayer.PreconditionBusinessLayer;
import com.technion.utils.JunitUtils;

public class PreconditionBusinessLayerTest {

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
		Assert.assertEquals(1, predicatesWithParams.size());
		doReturn( highestEffectLevel ).when(problemDomain).getEffectsNumber();
		doReturn(predicatesWithParams).when(problemDomain).getPredicat();
		HashMap<Integer, List<Predicat>> newPredicates = classUnderTest.buildNewPredicates(problemDomain);
		Assert.assertNotNull( newPredicates );

		//check that there are 'highestEffectLevel + 1' levels of predicates
		Assert.assertEquals( highestEffectLevel + 1, newPredicates.keySet().size() );
		
		for (int i = 0; i <= highestEffectLevel; i++) {
			List<Predicat> predicatesLevel = newPredicates.get(Integer.valueOf(i));
			//check each level contain all original predicates + one new predicate
			Assert.assertEquals( numberOfPredicates +1, predicatesLevel.size() );
			//check each level contain all  predicate named 'Open'+'level Index
			Assert.assertEquals("Open"+i, predicatesLevel.get( predicatesLevel.size()-1 ).getName());
		}
	}

}
