package com.technion.compile.ore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.technion.ai.dao.Predicat;

public class PredicateMap {
	private Map<Predicat, ObjectsMap<Predicat>> predicatesMap;
	private ObjectsMap<Predicat> openPredicates;
	
	public PredicateMap () {
		this.predicatesMap = new HashMap<Predicat, ObjectsMap<Predicat>>();
		this.openPredicates = new ObjectsMap<Predicat>();
	}

	private List<Predicat> getPredicates(Predicat predicat, Integer index) {
		ObjectsMap<Predicat> map = predicatesMap.get(predicat);
		if (map==null) {
			return new ArrayList<Predicat>();
		}
		return map.getObjects(index);
	}
	
	public Predicat getPredicate(Predicat predicat, Integer index) {
		List<Predicat> predicates = this.getPredicates(predicat, index);
		if (predicates.size()>0) {
			return predicates.get(0);
		}
		return null;
	}
	
	private void addPredicates(Predicat predicat, Integer level, List<Predicat> list) {
		ObjectsMap<Predicat> map = predicatesMap.get(predicat);
		if (map == null) {
			map = new ObjectsMap<Predicat>();
			predicatesMap.put(predicat, map);
		}
		map.addObjects(level, list);
	}
	
	public void addPredicate(Predicat predicat, Integer level, Predicat newPredicate) {
		this.addPredicates(predicat, level, Arrays.asList(newPredicate));
	}

	public void addOpenPredicate(Integer level, Predicat predicat) {
		openPredicates.addObject(level, predicat);
	}

	public Predicat getOpenPredicate(Integer level) {
		List<Predicat> list = openPredicates.getObjects(level);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
