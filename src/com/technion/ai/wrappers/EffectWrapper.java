package com.technion.ai.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.technion.ai.dao.Effect;

public class EffectWrapper implements Cloneable {
	private Effect effect;
	private List<PredicateWrapper> predicateWrappers;
	
	public EffectWrapper (Effect effect) {
		this.effect = effect;
		this.predicateWrappers = PredicateWrapper.convertPredicatesToPredicateWappers(effect.getPredicat());
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
	public List<PredicateWrapper> getPredicat() {
		return predicateWrappers;
	}

	public static List<EffectWrapper> convertEffectsToEffectsWappers( List<Effect> effects) {
		ArrayList<EffectWrapper> list = new ArrayList<EffectWrapper>();
		for (Effect effect : effects) {
			EffectWrapper effectWrapper = new EffectWrapper(effect);
			list.add(effectWrapper);
		}
		return list;
	}
	
}
