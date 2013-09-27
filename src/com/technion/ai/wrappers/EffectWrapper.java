package com.technion.ai.wrappers;

import java.util.List;

import com.technion.ai.dao.Effect;
import com.technion.ai.dao.Predicat;

public class EffectWrapper implements Cloneable {
	private Effect effect;
	
	public EffectWrapper (Effect effect) {
		this.effect = effect;
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Effect#getName()
	 */
	public String getName() {
		return effect.getName();
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Effect#setName(java.lang.String)
	 */
	public void setName(String value) {
		effect.setName(value);
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Effect#getFValue()
	 */
	public Integer getFValue() {
		return effect.getFValue();
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Effect#setFValue(java.lang.Integer)
	 */
	public void setFValue(Integer value) {
		effect.setFValue(value);
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Effect#getPredicat()
	 */
	public List<Predicat> getPredicat() {
		return effect.getPredicat();
	}
	
}
