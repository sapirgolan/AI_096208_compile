package com.technion.junits;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.technion.ai.dao.Action;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Predicat;
import com.technion.ai.wrappers.ActionWrapper;
import com.technion.ai.wrappers.DomainWrapper;
import com.technion.ai.wrappers.PredicateWrapper;
import com.technion.compile.businessLayer.ActionBusinessLayer;
import com.technion.compile.businessLayer.PreconditionBusinessLayer;
import com.technion.compile.core.CompilationSession;
import com.technion.compile.ore.PredicateMap;
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
	//test that all actions are replicated
	public void buildNewActionTest(){
		int highestEffectLevel = 4;
		int numberOfActions = 7;
		Domain domain = JunitUtils.createDomain(numberOfActions);
		Domain spyDomain = spy(domain);
		doReturn( highestEffectLevel ).when(spyDomain).getEffectsNumber();
		DomainWrapper domainWrapper = new DomainWrapper(spyDomain);
		
		HashMap<Integer,List<ActionWrapper>> newActionsMap = classUnderTest.buildNewActions(domainWrapper);
		Assert.assertEquals(highestEffectLevel+1, newActionsMap.keySet().size());
		Iterator<Entry<Integer, List<ActionWrapper>>> iterator = newActionsMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, List<ActionWrapper>> entry = (Map.Entry<Integer, List<ActionWrapper>>) iterator.next();
			String levelString = getLevelString(entry.getKey());
			List<ActionWrapper> actionsList = entry.getValue();
			for (ActionWrapper actionWrapper : actionsList) {
				String actionName = actionWrapper.getName();
				Assert.assertTrue(actionName.contains(levelString));
			}
		}
	}
	
	@Test
	public void testGetOpenPredicates(){
		int highestEffectLevel = 3;
		int numberOfOpenPredicates = 3;
		int numberOfActions = 1;
		List<Action> actions = JunitUtils.createActions(numberOfActions);
		//add created actions to mock domain
		domain.getAction().addAll(actions);
		Domain spyDomain = spy(domain);
		doReturn( highestEffectLevel ).when(spyDomain).getEffectsNumber();
		DomainWrapper domainWrapper = new DomainWrapper(spyDomain);
		// Create Open Predicates and store them in Sessions's predicateMap
		PredicateMap predicateMap = CompilationSession.getInstance().getPredicateMap();
		for (int i = 0; i <= numberOfOpenPredicates; i++) {
			Predicat predicat = new Predicat();
			predicat.setName(getOpenActionName(i));
			PredicateWrapper wrapper = new PredicateWrapper(predicat);
			predicateMap.addOpenPredicate(i, wrapper );
		}
		//building new actions according to 'highestEffectLevel'
		HashMap<Integer,List<ActionWrapper>> actionsMap = classUnderTest.buildNewActions(domainWrapper);
		Assert.assertNotNull(actionsMap);
		Iterator<Entry<Integer, List<ActionWrapper>>> iterator = actionsMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, List<ActionWrapper>> entry = (Map.Entry<Integer, List<ActionWrapper>>) iterator.next();
			//taking only the first action since at the begining crated only a single action
			Assert.assertEquals(numberOfActions, entry.getValue().size());
			ActionWrapper action = entry.getValue().get(0);
			Assert.assertNotNull(action);
			Integer levelIndex = entry.getKey();
			List<PredicateWrapper> predicates = action.getPredicat();
			int expectedNumberOfOpenPredicates = numberOfOpenPredicates-levelIndex+1;
			Assert.assertEquals(expectedNumberOfOpenPredicates, predicates.size());
			int numberOfNegativePreds = 0, numberOfPositivePreds = 0;
			for (PredicateWrapper predicateWrapper : predicates) {
				if (predicateWrapper.isIsPositive()) {
					numberOfPositivePreds++;
				} else {
					numberOfNegativePreds++;
				}
			}
			Assert.assertEquals(1, numberOfPositivePreds);
			Assert.assertEquals(numberOfOpenPredicates-levelIndex, numberOfNegativePreds);
		}
	}

	@Ignore
	@Test
	public void buildNewActionstest() {
		int highestEffectLevel = 3;
		int numberOfActions = 6;
		
		Domain mockDomain = JunitUtils.createDomain(numberOfActions);
		List<Action> actions = mockDomain.getAction();
		List<Predicat> predicates = mockDomain.getPredicat();
		
		doReturn( highestEffectLevel ).when(problemDomain).getEffectsNumber();
		//mock actions
		doReturn(actions).when(problemDomain).getAction();
		//mock predicates
		doReturn(predicates).when(problemDomain).getPredicat();
		DomainWrapper domainWrapper = new DomainWrapper(problemDomain);
		
		PreconditionBusinessLayer preconditionBusinessLayer = new PreconditionBusinessLayer();
		preconditionBusinessLayer.buildNewPredicates(domainWrapper);
		
		//The following method is being tested
		HashMap<Integer, List<ActionWrapper>> newActionsMap = classUnderTest.buildNewActions(domainWrapper);
		Assert.assertEquals(highestEffectLevel + 1 , newActionsMap.keySet().size());
		
		for (int i = 0; i <= highestEffectLevel; i++) {
			List<ActionWrapper> listActions = newActionsMap.get(Integer.valueOf(i));
			//check each level contains all original actions
			Assert.assertEquals(numberOfActions, listActions.size());
			//check each level contains original actions with new names
			Assert.assertEquals(actions.get(i).getName() + i, listActions.get(i).getName());
			
//			List<PredicateWrapper> predicats = listActions.get(i).getPredicat();
			for (ActionWrapper actionWrapper : listActions) {
				List<PredicateWrapper> predicats = actionWrapper.getPredicat();
				List<String> predicatesNames = getPredicatesNames(predicats);
				for (String predicatesName : predicatesNames) {
					Assert.assertTrue( predicatesName.contains( String.valueOf(i) ) );
				}
				
				//check each action has a precondition named 'Open + indexLevel' 
				Assert.assertTrue(predicatesNames.contains(getOpenActionName(i)));
			}
		}
	}

	private List<String> getPredicatesNames(List<PredicateWrapper> predicats) {
		ArrayList<String> strings = new ArrayList<String>();
		for (PredicateWrapper predicat : predicats) {
			strings.add( predicat.getName() );
		}
		return strings;
	}

}
