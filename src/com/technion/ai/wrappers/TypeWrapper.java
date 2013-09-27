package com.technion.ai.wrappers;

import java.util.List;

import com.technion.ai.dao.Types;

public class TypeWrapper implements Cloneable {
	private Types types;
	
	public TypeWrapper(Types types) {
		this.types = types;
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Types#getType()
	 */
	public List<String> getType() {
		return types.getType();
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Types#toString()
	 */
	public String toString() {
		return types.toString();
	}
	
	
}
