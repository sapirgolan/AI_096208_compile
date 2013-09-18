package com.technion.compile.businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rits.cloning.Cloner;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Predicat;

public class PreconditionBusinessLayer {

	private HashMap<Integer, List<Predicat>> predicatMap;
	
	public PreconditionBusinessLayer( ) {
		this.predicatMap = new HashMap<Integer, List<Predicat>>();
	}
	
	public HashMap<Integer, List<Predicat>> buildNewPredicates (Domain problemDomain) {
		int effectsNumber = problemDomain.getEffectsNumber();
		List<Predicat> originalPredicats = problemDomain.getPredicat();
		Cloner cloner = new Cloner();
		for (int i = 0; i <= effectsNumber; i++) {
			ArrayList<Predicat> list = new ArrayList<Predicat>();
			Integer index = Integer.valueOf(i);
			predicatMap.put(index, list);
			
			for (Predicat originalPredicate : originalPredicats) {
				Predicat newPredicate = cloner.deepClone(originalPredicate);
				//renaming predicates
				newPredicate.setName( originalPredicate.getName()+i );
				list.add(newPredicate);
			}
			list.add( createOpenPredicate(index) );
		}
		return predicatMap;
	}

	private Predicat createOpenPredicate(int index) {
		Predicat openPredicate = new Predicat();
		openPredicate.setName("Open" + index);
		return openPredicate;
	}

}
