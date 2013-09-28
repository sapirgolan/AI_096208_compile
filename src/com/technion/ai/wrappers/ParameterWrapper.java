package com.technion.ai.wrappers;

import java.util.ArrayList;
import java.util.List;

import com.technion.ai.dao.Parameter;

public class ParameterWrapper implements Cloneable {
	private Parameter parameter;
	
	public ParameterWrapper (Parameter parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Parameter#getName()
	 */
	public String getName() {
		return parameter.getName();
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Parameter#setName(java.lang.String)
	 */
	public void setName(String value) {
		parameter.setName(value);
	}

	/**
	 * @return
	 * @see com.technion.ai.dao.Parameter#getType()
	 */
	public String getType() {
		return parameter.getType();
	}

	/**
	 * @param value
	 * @see com.technion.ai.dao.Parameter#setType(java.lang.String)
	 */
	public void setType(String value) {
		parameter.setType(value);
	}

	public static List<ParameterWrapper> convertParametersToParameterWrappers(List<Parameter> parameters) {
		ArrayList<ParameterWrapper> parameterWrappers = new ArrayList<ParameterWrapper>();
		for (Parameter parameter : parameters) {
			ParameterWrapper parameterWrapper = new ParameterWrapper(parameter);
			parameterWrappers.add(parameterWrapper);
		}
		return parameterWrappers;
	}
	
	
}
