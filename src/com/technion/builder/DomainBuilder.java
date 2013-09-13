package com.technion.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.technion.ai.dao.Action;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Effect;
import com.technion.ai.dao.Parameter;
import com.technion.ai.dao.Predicat;
import com.technion.utils.Utils;

public class DomainBuilder {

	private StringBuilder stringBuilder;
	private Domain problemDomain;
	private Properties prop;
	private static final String PROPERTIES_PATH = "/com/technion/properties/strings.properties";
	private static String PRECONDITION_PREFIX;
	private static String PREDICAT_PREFIX;
	private static String AND_OPERATOR;
	private static String NOT_OPERATOR;
	private static String ACTION_PREFIX;
	private static String EFFECT_PREFIX;
	private static String PARAMETERS_PREFIX;
	private static String TYPES_LINE;
	private static String DOMAIN_DEFENITION;
	private static String DOMAIN_PREFIX;
;
	
	public DomainBuilder(Domain problemDomain) {
		this.stringBuilder = new StringBuilder();
		this.problemDomain = problemDomain;
	}

	public StringBuilder buildDomain() {
		this.prop = Utils.initPropertiesFile(PROPERTIES_PATH);
		if ( this.prop != null ) {
			getStrings();
			buildDomainDefenitions();
			buildDomainPrefix();
			buildTypes(); this.addEmptyLine();
			buildPredicates(); this.addEmptyLine();
			this.buldActions();
			this.addStringInNewLine(")");
		}
		return this.stringBuilder;
	}
	
	/**
	 * This method populate strings from properties file
	 */
	private void getStrings() {
		PARAMETERS_PREFIX = prop.getProperty("parametersPrefix");
		PRECONDITION_PREFIX = prop.getProperty("preconditionPrefix");
		PREDICAT_PREFIX = prop.getProperty("predicatPrefix");
		AND_OPERATOR = prop.getProperty("andOperator");
		NOT_OPERATOR = prop.getProperty("notOperator");
		ACTION_PREFIX = prop.getProperty("actionPrefix");
		EFFECT_PREFIX = prop.getProperty("effectPrefix");
		TYPES_LINE = prop.getProperty("types");
		DOMAIN_DEFENITION = prop.getProperty("domainDefenition");
		DOMAIN_PREFIX = prop.getProperty("domainPrefix");
	}

	private void buildPredicates() {
		this.addStringInNewLine(PREDICAT_PREFIX + "\n");
		
		List<Predicat> predicats = problemDomain.getPredicats();
		for (Predicat predicat : predicats) {
			this.addStringInNewLine(predicat.toString(true));
		}
		this.addStringInNewLine(")");
	}
	
	private void buldActions() {
		this.addEmptyLine();
		
		List<Action> actions = problemDomain.getActions();
		for (Action action : actions) {
			this.addStringInNewLine(ACTION_PREFIX);
			this.addStringInLine(action.getName());
			List<Predicat> preconditionsPredicats = action.getPreconditions();
			writeActionParameters(preconditionsPredicats);
			writeActionPrecondition(preconditionsPredicats);
			writeActionEffects(action.getEffect());
			this.addStringInLine(")");
		}
	}

	private void writeActionEffects(List<Effect> effects) {
		this.addStringInNewLine(EFFECT_PREFIX);
		this.addStringInLine(AND_OPERATOR);
		for (Effect effect : effects) {
			List<Predicat> predicats = effect.getPredicat();
			for (Predicat predicat : predicats) {
				writePredicatWithFalseOrPositive(predicat);
			}
		}
		this.addStringInNewLine(")");
	}
	
	private void writeActionPrecondition(List<Predicat> preconditionsPredicats) {
		
		this.addStringInNewLine(PRECONDITION_PREFIX);
		if ( preconditionsPredicats.size() > 1 ) {
			this.addStringInLine(AND_OPERATOR);
		}
		//predicat is the a condition like "(at ?x ?a)"
		for (Predicat predicat : preconditionsPredicats) {
			writePredicatWithFalseOrPositive(predicat);
		}
		this.addStringInNewLine(")");
	}

	/**
	 * <p>This method write a short string presentation version of a predicate. The result is similar to:
	 * <pre>(at ?w ?a)</pre> OR <pre>(NOT (at ?x ?a))</pre>
	 * </p>
	 * It does it as followed:
	 * <ol>
	 * <li>If the predicate is not positive it add NOT before it</li>
	 * <li>It drops the predicate type</li>
	 * </ol>
	 * @param predicat
	 */
	private void writePredicatWithFalseOrPositive(Predicat predicat) {
		//parameters are the name of variable.  like "?x"
		if ( predicat.isPositive()!=null && !predicat.isPositive() ) {
			this.addStringInNewLine("(" + NOT_OPERATOR);
			this.addStringInLine( predicat.toString(false) );
			this.addStringInLine(")");
		} else {
			this.addStringInNewLine( predicat.toString(false) );
		}
	}

	private void writeActionParameters(List<Predicat> predicats) {
		List<Parameter> parameters = new ArrayList<Parameter>();
		
		//collect all the parameters of all predicates
		for (Predicat predicat : predicats) {
			parameters.addAll( predicat.getParameter() );
		}
		
		//create MAP. Key: parameters type; value: parameters name of this type
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (Parameter parameter : parameters) {
			String paramName = parameter.getName();
			String paramType = parameter.getType();
			//if  we don't have any parameter of this type yet create a new entry for the type
			//otherwise add the name to already existing type
			if ( !map.containsKey(paramType) )
			{
				ArrayList<String> parametersNames = new ArrayList<String>( Arrays.asList(paramName) );
				map.put(paramType, parametersNames);
			} else {
				map.get(paramType).add(paramName);
			}
		}
		
		this.addStringInNewLine(PARAMETERS_PREFIX);

		//iterate over the map and prints its content
		for (String type : map.keySet()) {
			List<String> list = map.get(type);
			for (String name : list) {
				this.addStringInLine(name);
			}
			this.addStringInLine("- " + type);
		}
		this.addStringInLine(")");
		
		
	}

	private void buildTypes() {
		String typesValues = problemDomain.getTypes().toString();
		TYPES_LINE = String.format(TYPES_LINE, typesValues);
		this.addStringInNewLine(TYPES_LINE);
	}

	private void addStringInNewLine(String newLine) {
		stringBuilder.append("\n" + newLine);
	}
	
	private void addStringInLine(String text) {
		stringBuilder.append(" " + text);
	}
	
	private void addEmptyLine() {
		this.addStringInNewLine("");
	}
	
	private void buildDomainPrefix() {
		addStringInNewLine(DOMAIN_PREFIX);
	}

	/**
	 * This method insert the static definition of the domain and its name
	 */
	private void buildDomainDefenitions() {
		stringBuilder.append("(");
		DOMAIN_DEFENITION = String.format(DOMAIN_DEFENITION, problemDomain.getName());
		stringBuilder.append(DOMAIN_DEFENITION + "\n");
	}

	public Domain getProblemDomain() {
		return problemDomain;
	}
}
