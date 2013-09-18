package com.technion.compile.businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rits.cloning.Cloner;
import com.technion.ai.dao.Action;
import com.technion.ai.dao.Domain;

public class ActionBusinessLayer extends AbstractBusinessLayer {

	private HashMap<Integer, List<Action>> actionMap;
	private Cloner cloner; 
	
	public ActionBusinessLayer( ) {
		this.actionMap = new HashMap<Integer, List<Action>>();
		this.cloner = new Cloner();
	}
	
	
	public HashMap<Integer,List<Action>> buildNewActions(Domain domain) {
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
			}
		}
		
		return actionMap;
	}

}
