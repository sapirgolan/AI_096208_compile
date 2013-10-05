package com.technion.compile.ore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.technion.ai.wrappers.PredicateWrapper;

public class PredicateMap {
	private Map<PredicateWrapper, ObjectsMap<PredicateWrapper>> predicatesMap;
	private ObjectsMap<PredicateWrapper> openPredicates;
	
	public PredicateMap () {
		this.predicatesMap = new HashMap<PredicateWrapper, ObjectsMap<PredicateWrapper>>();
		this.openPredicates = new ObjectsMap<PredicateWrapper>();
	}

	private List<PredicateWrapper> getPredicates(PredicateWrapper predicat, Integer index) {
		ObjectsMap<PredicateWrapper> map = predicatesMap.get(predicat);
		if (map==null) {
			return new ArrayList<PredicateWrapper>();
		}
		return map.getObjects(index);
	}
	
	public PredicateWrapper getPredicate(PredicateWrapper predicat, Integer index) {
		List<PredicateWrapper> predicates = this.getPredicates(predicat, index);
		if (predicates.size()>0) {
			return predicates.get(0);
		}
		return null;
	}
	
	private void addPredicates(PredicateWrapper predicat, Integer level, List<PredicateWrapper> list) {
		ObjectsMap<PredicateWrapper> map = predicatesMap.get(predicat);
		if (map == null) {
			map = new ObjectsMap<PredicateWrapper>();
			predicatesMap.put(predicat, map);
		}
		map.addObjects(level, list);
	}
	
	public void addPredicate(PredicateWrapper predicat, Integer level, PredicateWrapper newPredicate) {
		this.addPredicates(predicat, level, Arrays.asList(newPredicate));
	}

	public void addOpenPredicate(Integer level, PredicateWrapper predicat) {
		openPredicates.addObject(level, predicat);
	}

	public PredicateWrapper getOpenPredicate(Integer level) {
		List<PredicateWrapper> list = openPredicates.getObjects(level);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<PredicateWrapper> getOpenPredicatesAboveLevel(Integer level) {
		List<PredicateWrapper> objectsWithKeyGreaterThan = openPredicates.getObjectsWithKeyGreaterThan(level);
		return objectsWithKeyGreaterThan;
	}
}
