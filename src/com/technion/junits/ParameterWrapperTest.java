package com.technion.junits;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Parameter;
import com.technion.ai.wrappers.ParameterWrapper;
import com.technion.utils.JunitUtils;

public class ParameterWrapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertParametersToParameterWrappers() {
		int numberOfParameters = 5;
		List<Parameter> parameters = JunitUtils.createNParameters(numberOfParameters);
		List<ParameterWrapper> parameterWrappers = ParameterWrapper.convertParametersToParameterWrappers(parameters);
		for (int i = 0; i < numberOfParameters; i++) {
			Parameter parameter = parameters.get(i);
			ParameterWrapper parameterWrapper = parameterWrappers.get(i);
			
			Assert.assertEquals(parameter.getName(), parameterWrapper.getName());
			Assert.assertEquals(parameter.getType(), parameterWrapper.getType());
		}
	}

}
