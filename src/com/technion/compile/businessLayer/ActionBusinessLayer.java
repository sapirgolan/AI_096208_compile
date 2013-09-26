package com.technion.compile.businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rits.cloning.Cloner;
import com.technion.ai.dao.Action;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Predicat;
import com.technion.compile.core.CompilationSession;
import com.technion.compile.ore.PredicateMap;

public class ActionBusinessLayer extends AbstractBusinessLayer {

	private HashMap<Integer, List<Action>> actionMap;
	private Cloner cloner; 
	
	public ActionBusinessLayer( ) {
		this.actionMap = new HashMap<Integer, List<Action>>();
		this.cloner = new Cloner();
	}
	
	
	public HashMap<Integer,List<Action>> buildNewActions(Domain domain) {
		PredicateMap predicateMap = CompilationSession.getInstance().getPredicateMap();
		int effectsNumber = domain.getEffectsNumber();
		List<Action> originalActions = domain.getAction();
		
		for (int i = 0; i <= effectsNumber; i++) {
			Integer level = Integer.valueOf(i);
			ArrayList<Action> newActions = new ArrayList<Action>();
			actionMap.put(level, newActions);
			
			for (Action oldAction : originalActions) {
				Action newAction = cloner.deepClone(oldAction);
				//renaming action
				newAction.setName( oldAction.getName()+i );
				newActions.add(newAction);
				//replacing The new action old predicate with a new predicate
				List<Predicat> newActionOldPrediList = newAction.getPredicat();
				List<Predicat> newActionNewPrediList = new ArrayList<Predicat>();
				for (Predicat predicat : newActionOldPrediList) {
					Predicat newPredicate = predicateMap.getPredicate(predicat, level);
					newActionNewPrediList.add(newPredicate);
				}
				newActionOldPrediList = newActionNewPrediList;
			}
		}
		
		return actionMap;
	}

}
