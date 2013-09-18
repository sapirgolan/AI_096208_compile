package com.technion.junits;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

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
		List<Action> actions = JunitUtils.createActions(numberOfActions);
		doReturn( highestEffectLevel ).when(problemDomain).getEffectsNumber();
		doReturn(actions).when(problemDomain).getAction();
		
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
			//check each action has a precondition named 'Open + indexLevel' 
			Assert.assertTrue(predicatesNames.contains(getOpenActionName(i)));
		}
	}

	private List<String> getPredicatesNames(List<Predicat> predicats) {
		// TODO Auto-generated method stub
		return null;
	}

}
