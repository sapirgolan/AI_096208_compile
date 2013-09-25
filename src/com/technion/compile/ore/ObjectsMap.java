package com.technion.compile.ore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>This class store a map of pairs (Integer, List of {@link Object}). 
 * For each level : 0,1,2... you can obtain all the predicates</p>
 * @author XPS_Sapir
 * @param <T>
 *
 */
public class ObjectsMap<T> {
	
	private Map<Integer, List<T> > map;

	public ObjectsMap( ) {
		this.map = new HashMap<Integer, List<T>>();
	}
	
	public void addObjects(Integer level, List<T> list) {
		map.put(level, list);
	}
	
	@SuppressWarnings("unchecked")
	public void addObject(Integer index, T predicat) {
		this.addObjects(index,  Arrays.asList(predicat));
	}
	
	public List<T> getObjects(Integer index) {
		List<T> list = map.get(index);
		if (list==null) {
			list = new ArrayList<T>();
		}
		return list;
	}

}
