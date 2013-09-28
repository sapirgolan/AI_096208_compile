package com.technion.junits;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Action;
import com.technion.ai.wrappers.ActionWrapper;
import com.technion.utils.JunitUtils;

public class ActionWrapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertActionToActionWrappers() {
		int numberOfActions = 3;
		List<Action> actions = JunitUtils.createActions(numberOfActions);
		List<ActionWrapper> actionWrappers = ActionWrapper.convertActionToActionWrappers(actions);
		for (int i = 0; i < numberOfActions; i++) {
			ActionWrapper actionWrapper = actionWrappers.get(i);
			Action action = actions.get(i);
			
			Assert.assertEquals(action.getEffect().size() , actionWrapper.getEffect().size() );
			Assert.assertEquals( action.getName(), actionWrapper.getName() );
			Assert.assertEquals( action.getPredicat().size(), actionWrapper.getPredicat().size() );
		}
	}

}
