package com.technion.junits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ActionBusinessLayerTest.class, 
	DomainBuilderTest.class, 
	ObjectsMapTest.class,
	PreconditionBusinessLayerTest.class, 
	PredicateMapTest.class,
	XmlToJavaObjectsTest.class 
})

public class AllTests {

}
