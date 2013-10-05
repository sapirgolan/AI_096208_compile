package com.technion.junits;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.technion.ai.dao.Effect;
import com.technion.ai.dao.Predicat;
import com.technion.compile.ore.ObjectsMap;

public class ObjectsMapTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObjects() {
		ObjectsMap<Predicat> objectsMap = new ObjectsMap<Predicat>();
		objectsMap.addObjects(0, Arrays.asList(new Predicat()));

		List<Predicat> list = objectsMap.getObjects(0);
		Assert.assertTrue(list.size()==1);
		
		//add new predicate, make sure old predicate are removed
		objectsMap.addObjects(0, Arrays.asList(new Predicat()));
		Assert.assertTrue(list.size()==1);
		
		objectsMap.addObjects(1, Arrays.asList(new Predicat(), new Predicat()));
		list = objectsMap.getObjects(1);
		Assert.assertTrue(list.size()==2);
		objectsMap.addObjects(1, null);
		list = objectsMap.getObjects(1);
		Assert.assertTrue(list.isEmpty());
		
		objectsMap.addObjects(null, Arrays.asList(new Predicat()));
		objectsMap.addObjects(3, null);
		list = objectsMap.getObjects(3);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	public void testAddObject() {
		ObjectsMap<Effect> objectsMap = new ObjectsMap<Effect>();
		objectsMap.addObject(0, new Effect());
		
		List<Effect> list = objectsMap.getObjects(0);
		Assert.assertTrue(list.size()==1);
	}

	@Test
	public void testGetObjects() {
		ObjectsMap<Predicat> objectsMap = new ObjectsMap<Predicat>();
		List<Predicat> list = objectsMap.getObjects(null);
		
		Assert.assertTrue(list.isEmpty());
		list = objectsMap.getObjects(1);
		Assert.assertTrue(list.isEmpty());
	}
	
	@Test
	public void testGetObjectsWithKeyGreaterThan(){
		ObjectsMap<Integer> objectsMap = new ObjectsMap<Integer>();
		int limit = 5;
		for (int i = 0; i < limit; i++) {
			objectsMap.addObject(i, i);
		}
		
		for (int i = 0; i < limit; i++) {
			List<Integer> list = objectsMap.getObjectsWithKeyGreaterThan(i);
			Assert.assertEquals(limit -(i+1), list.size());
		}
	}

}
