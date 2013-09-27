package com.technion.ai.wrappers;

import java.util.List;

import com.technion.ai.dao.Action;
import com.technion.ai.dao.Effect;
import com.technion.ai.dao.Predicat;

public class ActionWrapper implements Cloneable {
	private Action action;
	
	public ActionWrapper(Action action) {
		this.action = action;
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
	public List<Predicat> getPredicat() {
		return action.getPredicat();
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Action#getEffect()
	 */
	public List<Effect> getEffect() {
		return action.getEffect();
	}
	
}
