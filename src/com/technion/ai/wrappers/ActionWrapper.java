package com.technion.ai.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.technion.ai.dao.Action;

public class ActionWrapper implements Cloneable {
	private Action action;
	private List<PredicateWrapper> predicateWrappers;
	private List<EffectWrapper> effectWrappers;
	
	public ActionWrapper(Action action) {
		this.action = action;
		this.predicateWrappers = PredicateWrapper.convertPredicatesToPredicateWappers(action.getPredicat());
		this.effectWrappers = EffectWrapper.convertEffectsToEffectsWappers( action.getEffect() );
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Action#getName()
	 */
	public String getName() {
		return action.getName();
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Action#setName(java.lang.String)
	 */
	public void setName(String value) {
		action.setName(value);
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Action#getPredicat()
	 */
	public List<PredicateWrapper> getPredicat() {
		return predicateWrappers;
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Action#getEffect()
	 */
	public List<EffectWrapper> getEffect() {
		return effectWrappers;
	}
	
	public static List<ActionWrapper> convertActionToActionWrappers (List<Action> actions) {
		List<ActionWrapper> list = new ArrayList<ActionWrapper>();
		for (Action action : actions) {
			ActionWrapper actionWrapper = new ActionWrapper(action);
			list.add(actionWrapper);
		}
		return list;
	}
	
}
