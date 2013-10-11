package com.technion.ai.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.technion.ai.dao.Action;

public class ActionWrapper implements Cloneable {
	private Action action;
	private String actionName;
	private List<PredicateWrapper> predicateWrappers;
	private List<EffectWrapper> effectWrappers;
	
	public ActionWrapper(Action action) {
		this.action = action;
		this.actionName = action.getName();
		this.predicateWrappers = PredicateWrapper.convertPredicatesToPredicateWappers(action.getPredicat());
		this.effectWrappers = EffectWrapper.convertEffectsToEffectsWappers( action.getEffect() );
	}
	
	public ActionWrapper clone() {
		ActionWrapper clone = null;
		try {
			clone = (ActionWrapper) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Action#getName()
	 */
	public String getName() {
		return actionName;
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Action#setName(java.lang.String)
	 */
	public void setName(String value) {
		actionName = value;
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Action#getPredicat()
	 */
	public List<PredicateWrapper> getPredicat() {
		return predicateWrappers;
	}

	public void setPredicat(List<PredicateWrapper> predicateWrappers) {
		this.predicateWrappers = predicateWrappers;
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
