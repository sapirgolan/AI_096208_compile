package com.technion.compile.ore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.technion.ai.dao.Predicat;

public class PredicateMap {
	private Map<Predicat, ObjectsMap<Predicat>> predicatesMap;
	
	public PredicateMap () {
		this.predicatesMap = new HashMap<Predicat, ObjectsMap<Predicat>>();
	}

	public List<Predicat> getPredicates(Predicat predicat, Integer index) {
		ObjectsMap<Predicat> map = predicatesMap.get(predicat);
		if (map==null) {
			return new ArrayList<Predicat>();
		}
		return map.getObjects(index);
	}
	
	public void addPredicates(Predicat predicat, Integer level, List<Predicat> list) {
		ObjectsMap<Predicat> map = predicatesMap.get(predicat);
		if (map == null) {
			map = new ObjectsMap<Predicat>();
			predicatesMap.put(predicat, map);
		}
		map.addObjects(level, list);
	}
	
	public void addPredicate(Predicat predicat, Integer level, Predicat newPredicat) {
		this.addPredicates(predicat, level, Arrays.asList(newPredicat));
	}
}
