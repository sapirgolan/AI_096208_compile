package com.technion.junits;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Domain;
import com.technion.ai.wrappers.DomainWrapper;
import com.technion.utils.JunitUtils;

public class DomainWrapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDomainWrapper() {
		int numberOfActions = 8;
		Domain domain = JunitUtils.createDomain(numberOfActions );
		DomainWrapper domainWrapper = new DomainWrapper(domain);
		
		Assert.assertEquals( domain.getEffectsNumber(), domainWrapper.getEffectsNumber() );
		Assert.assertEquals( domain.getName(), domainWrapper.getName() );
		Assert.assertEquals( domain.getAction().size(), domainWrapper.getAction().size() );
		Assert.assertEquals( domain.getPredicat().size(), domain.getPredicat().size() );
		
	}

}
