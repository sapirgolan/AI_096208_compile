package com.technion.ai.wrappers;

import java.util.List;

import com.technion.ai.dao.Parameter;
import com.technion.ai.dao.Predicat;

public class PredicateWrapper implements Cloneable  {
	private Predicat predicate;

	public String getName() {
		return predicate.getName();
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Predicat#setName(java.lang.String)
	 */
	public void setName(String value) {
		predicate.setName(value);
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Predicat#isIsPositive()
	 */
	public Boolean isIsPositive() {
		return predicate.isIsPositive();
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Predicat#setIsPositive(java.lang.Boolean)
	 */
	public void setIsPositive(Boolean value) {
		predicate.setIsPositive(value);
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Predicat#getParameter()
	 */
	public List<Parameter> getParameter() {
		return predicate.getParameter();
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Predicat#toString()
	 */
	public String toString() {
		return predicate.toString();
	}

	/**
	 * @param withType
	 * @return
	 * @see com.technion.ai.dao.Predicat#toString(boolean)
	 */
	public String toString(boolean withType) {
		return predicate.toString(withType);
	}

}
