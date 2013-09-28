package com.technion.junits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ActionBusinessLayerTest.class,
	ActionWrapperTest.class,
	DomainBuilderTest.class,
	DomainWrapperTest.class,
	EffectWrapperTest.class,
	ObjectsMapTest.class,
	ParameterWrapperTest.class,
	PreconditionBusinessLayerTest.class,
	PredicateMapTest.class,
	PredicateWrapperTest.class,
	XmlToJavaObjectsTest.class 
})

public class AllTests {

}
