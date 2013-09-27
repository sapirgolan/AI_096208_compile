package com.technion.ai.wrappers;

import java.util.List;

import com.technion.ai.dao.Action;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Predicat;
import com.technion.ai.dao.Types;

public class DomainWrapper implements Cloneable {
	private Domain domain;
	
	public DomainWrapper ( Domain domain) {
		this.domain = domain;
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
	public Types getTypes() {
		return domain.getTypes();
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
	public List<Predicat> getPredicat() {
		return domain.getPredicat();
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Domain#getAction()
	 */
	public List<Action> getAction() {
		return domain.getAction();
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Domain#getEffectsNumber()
	 */
	public int getEffectsNumber() {
		return domain.getEffectsNumber();
	}
	
	

}
