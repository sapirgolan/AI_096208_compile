package com.technion.ai.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.technion.ai.dao.Predicat;

public class PredicateWrapper implements Cloneable  {
	private Predicat predicate;
	private String predicateName;
	private List<ParameterWrapper> parameterWrappers; 

	public PredicateWrapper( Predicat predicat) {
		this.predicate = predicat;
		this.predicateName = predicat.getName();
		this.parameterWrappers = ParameterWrapper.convertParametersToParameterWrappers( predicat.getParameter() );
	}
	
	public PredicateWrapper clone() {
		PredicateWrapper clone = null;
		try {
			clone = (PredicateWrapper) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
	
	public String getName() {
		return this.predicateName;
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Predicat#setName(java.lang.String)
	 */
	public void setName(String value) {
		this.predicateName = value;
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
	public List<ParameterWrapper> getParameter() {
		return parameterWrappers;
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

	public static List<PredicateWrapper> convertPredicatesToPredicateWappers(List<Predicat> predicats) {
		ArrayList<PredicateWrapper> list = new ArrayList<PredicateWrapper>();
		for (Predicat predicat : predicats) {
			PredicateWrapper predicateWrapper = new PredicateWrapper(predicat);
			list.add(predicateWrapper);
		}
		return list;
	}

}
