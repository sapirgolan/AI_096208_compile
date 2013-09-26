package com.technion.utils;

import java.util.ArrayList;
import java.util.List;

import com.technion.ai.dao.Action;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Parameter;
import com.technion.ai.dao.Predicat;

public class JunitUtils {
	

	public static Predicat createPredicat(List<Parameter> parameters, String predicatName) {
		Predicat predicat = new Predicat();
		predicat.setName(predicatName);
		//add parameter
		for (Parameter parameter : parameters) {
			predicat.getParameter().add(parameter);
		}
		return predicat;
	}

	public static Parameter createParmeter(String name, String type) {
		Parameter parameterOne = new Parameter();
		parameterOne.setName(name);
		parameterOne.setType(type);
		return parameterOne;
	}
	
	public static List<Predicat> createNPredicatesWithMParam(int numberOfPredicates, int numberOfParameters) {
		ArrayList<Predicat> predicats = new ArrayList<Predicat>();
		for (int i = 0; i < numberOfPredicates; i++) {
			ArrayList<Parameter> parameters = new ArrayList<Parameter>();
			for (int j = 0; j < numberOfParameters; j++) {
				parameters.add( createParmeter("parameter_" + j, "type" + j) );
			}
			predicats.add( createPredicat(parameters, "predicate_" + i) );
		}
		return predicats;
	}
	
	public static List<Action> createActions(int numberOfActions) {
		ArrayList<Action> actions = new ArrayList<Action>();
		for (int i = 0; i < numberOfActions; i++) {
			Action action = new Action();
			action.setName("Action_" + i);
			actions.add( action );
		}
		return actions;
	}
	
	public static Domain createDomain(int numberOfActions) {
		Domain problemDomain = new Domain();
		List<Action> actions = createActions(numberOfActions);
		problemDomain.getAction().addAll(actions);
		List<Predicat> predicates = createNPredicatesWithMParam(numberOfActions, numberOfActions);
		problemDomain.getPredicat().addAll(predicates);
		
		for (int i = 0; i < numberOfActions; i++) {
			Action action = actions.get(i);
			Predicat predicat = predicates.get(i);
			action.getPredicat().add(predicat);
		}
		return problemDomain;
	}
	
}
