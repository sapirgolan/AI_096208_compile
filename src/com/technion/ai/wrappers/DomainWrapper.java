package com.technion.ai.wrappers;

import java.util.List;

import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Types;

public class DomainWrapper implements Cloneable {
	private Domain domain;
	private List<ActionWrapper> actionWrappers;
	private List<PredicateWrapper> predicateWrappers;
	private TypeWrapper typeWrapper;
	
	public DomainWrapper ( Domain domain ) {
		this.domain = domain;
		this.actionWrappers = ActionWrapper.convertActionToActionWrappers( domain.getAction() );
		this.predicateWrappers = PredicateWrapper.convertPredicatesToPredicateWappers( domain.getPredicat() );
		this.typeWrapper = new TypeWrapper(domain.getTypes());
	}

	public Domain getDomain() {
		return domain;
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Domain#getName()
	 */
	public String getName() {
		return domain.getName();
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Domain#setName(java.lang.String)
	 */
	public void setName(String value) {
		domain.setName(value);
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Domain#getTypes()
	 */
	public TypeWrapper getTypes() {
		return typeWrapper;
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Domain#setTypes(com.technion.ai.dao.Types)
	 */
	public void setTypes(Types value) {
		domain.setTypes(value);
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Domain#getPredicat()
	 */
	public List<PredicateWrapper> getPredicat() {
		return predicateWrappers;
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Domain#getAction()
	 */
	public List<ActionWrapper> getAction() {
		return actionWrappers;
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Domain#getEffectsNumber()
	 */
	public int getEffectsNumber() {
		return domain.getEffectsNumber();
	}

}
