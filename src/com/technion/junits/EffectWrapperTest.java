package com.technion.junits;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Effect;
import com.technion.ai.wrappers.EffectWrapper;
import com.technion.utils.JunitUtils;

public class EffectWrapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertEffectsToEffectsWappers() {
		int numberOfEffects = 4;
		List<Effect> effects = JunitUtils.createEffects( numberOfEffects );
		List<EffectWrapper> effectsWappers = EffectWrapper.convertEffectsToEffectsWappers(effects);
		for (int i = 0; i < numberOfEffects; i++) {
			Effect effect = effects.get(i);
			EffectWrapper effectWrapper = effectsWappers.get(i);
			
			Assert.assertEquals(effect.getName(), effectWrapper.getName() );
			Assert.assertEquals(effect.getFValue(), effectWrapper.getFValue() );
			Assert.assertEquals(effect.getPredicat().size(), effectWrapper.getPredicat().size());
		}
	}

}
