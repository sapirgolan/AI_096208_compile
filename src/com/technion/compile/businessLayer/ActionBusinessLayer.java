package com.technion.compile.businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.technion.ai.wrappers.ActionWrapper;
import com.technion.ai.wrappers.DomainWrapper;
import com.technion.ai.wrappers.PredicateWrapper;
import com.technion.compile.core.CompilationSession;
import com.technion.compile.ore.PredicateMap;

public class ActionBusinessLayer extends AbstractBusinessLayer {

	private HashMap<Integer, List<ActionWrapper>> actionMap;
	
	public ActionBusinessLayer( ) {
		this.actionMap = new HashMap<Integer, List<ActionWrapper>>();
	}
	
	
	public HashMap<Integer, List<ActionWrapper>> buildNewActions(DomainWrapper domain) {
		PredicateMap sessionPredicateMap = CompilationSession.getInstance().getPredicateMap();
		int effectsNumber = domain.getEffectsNumber();
		List<ActionWrapper> originalActions = domain.getAction();
		
		//for each effect level need to create a new action
		//The new action will be a duplicate of existing action
		for (int i = 0; i <= effectsNumber; i++) {
			Integer level = Integer.valueOf(i);
			ArrayList<ActionWrapper> newActions = new ArrayList<ActionWrapper>();
			actionMap.put(level, newActions);
			String levelString = getLevelString(i);
			
			//cloning actions and renaming them
			for (ActionWrapper oldAction : originalActions) {
				ActionWrapper newAction = oldAction.clone();
				//renaming action
				newAction.setName( oldAction.getName() + levelString );
				newActions.add(newAction);
				//replacing The new action's old predicate with a new predicate
				List<PredicateWrapper> oldPrediList = newAction.getPredicat();
				List<PredicateWrapper> newPrediList = new ArrayList<PredicateWrapper>();
				for (PredicateWrapper predicat : oldPrediList) {
					//Obtains from the predicate map a new predicate for this action.
					//If for example we are at level2 & predicate is p0 then we should obtain predicate called p0_level2
					PredicateWrapper newPredicate = sessionPredicateMap.getPredicate(predicat, level);
					newPrediList.add(newPredicate);
				}
				//After all predicates were added it's time to add open predicates
				List<PredicateWrapper> openPredicates = getOpenPredicates( sessionPredicateMap, i );
//				PredicateWrapper openPredicate = predicateMap.getOpenPredicate(i);
				newPrediList.addAll(openPredicates);
				newAction.setPredicat(newPrediList);
//				newAction.getPredicat().clear();
//				newAction.getPredicat().addAll(newPrediList);
			}
		}
		
		return actionMap;
	}


	private List<PredicateWrapper> getOpenPredicates(PredicateMap predicateMap, int level) {
		ArrayList<PredicateWrapper> result = new ArrayList<PredicateWrapper>();
		PredicateWrapper positiveOpenPredicate = predicateMap.getOpenPredicate(level);
		result.add(positiveOpenPredicate);
		
		//
		List<PredicateWrapper> openPredicatesAbove = predicateMap.getOpenPredicatesAboveLevel(level);
		for (PredicateWrapper preds : openPredicatesAbove) {
			PredicateWrapper negPred = preds.clone();
			negPred.setIsPositive(false);
			result.add(negPred);
		}
		
		return result;
	}

}
