package com.technion.junits;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Action;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Predicat;
import com.technion.compile.businessLayer.ActionBusinessLayer;
import com.technion.compile.businessLayer.PreconditionBusinessLayer;
import com.technion.utils.JunitUtils;

public class ActionBusinessLayerTest extends AbstractTest{

	private Domain domain = new Domain();
	private Domain problemDomain = spy(domain);
	private ActionBusinessLayer classUnderTest;
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new ActionBusinessLayer();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void buildNewActionstest() {
		int highestEffectLevel = 3;
		int numberOfActions = 6;
		
		Domain mockDomain = JunitUtils.createDomain(numberOfActions);
		List<Action> actions = mockDomain.getAction();
		List<Predicat> predicates = mockDomain.getPredicat();
		
		doReturn( highestEffectLevel ).when(problemDomain).getEffectsNumber();
		doReturn(actions).when(problemDomain).getAction();
		doReturn(predicates).when(problemDomain).getPredicat();
		
		PreconditionBusinessLayer preconditionBusinessLayer = new PreconditionBusinessLayer();
		preconditionBusinessLayer.buildNewPredicates(problemDomain);
		
		HashMap<Integer,List<Action>> map = classUnderTest.buildNewActions(problemDomain);
		Assert.assertEquals(highestEffectLevel + 1 , map.keySet().size());
		
		for (int i = 0; i <= highestEffectLevel; i++) {
			List<Action> listActions = map.get(Integer.valueOf(i));
			//check each level contains all original actions
			Assert.assertEquals(numberOfActions, listActions.size());
			//check each level contains original actions with new names
			Assert.assertEquals(actions.get(i).getName() + i, listActions.get(i).getName());
			
			List<Predicat> predicats = listActions.get(i).getPredicat();
			List<String> predicatesNames = getPredicatesNames(predicats);
			for (String predicatesName : predicatesNames) {
				Assert.assertTrue( predicatesName.contains( String.valueOf(i) ) );
			}
			//check each action has a precondition named 'Open + indexLevel' 
			Assert.assertTrue(predicatesNames.contains(getOpenActionName(i)));
		}
	}

	private List<String> getPredicatesNames(List<Predicat> predicats) {
		ArrayList<String> strings = new ArrayList<String>();
		for (Predicat predicat : predicats) {
			strings.add( predicat.getName() );
		}
		return strings;
	}

}
