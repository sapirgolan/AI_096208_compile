package com.technion.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.technion.ai.dao.Action;
import com.technion.ai.dao.Actions;
import com.technion.ai.dao.Domain;
import com.technion.ai.dao.Parameter;
import com.technion.ai.dao.Predicat;
import com.technion.utils.Utils;

public class DomainBuilder {

	private StringBuilder stringBuilder;
	private Domain problemDomain;
	private Properties prop;
	private static final String PROPERTIES_PATH = "/com/technion/properties/strings.properties";
;
	
	public DomainBuilder(Domain problemDomain) {
		this.stringBuilder = new StringBuilder();
		this.problemDomain = problemDomain;
	}

	public StringBuilder buildDomain() {
		this.prop = Utils.initPropertiesFile(PROPERTIES_PATH);
		if ( this.prop != null ) {
			buildDomainDefenitions();
			buildDomainPrefix();
			buildTypes();
			this.addEmptyLine();
			buildPredicates();
			this.addEmptyLine();
			this.buldActions();
		}
		return this.stringBuilder;
	}
	
	private void buildPredicates() {
		String predicatPrefix = prop.getProperty("predicatPrefix");
		this.addStringInNewLine(predicatPrefix + "\n");
		
		List<Predicat> predicats = problemDomain.getPredicates().getPredicat();
		for (Predicat predicat : predicats) {
			this.addStringInNewLine(predicat.toString());
		}
		this.addStringInNewLine(")");
	}
	
	private void buldActions() {
		this.addEmptyLine();
		String actionPrefix = prop.getProperty("actionPrefix");
		String preconditionPrefix = prop.getProperty("preconditionPrefix");
		String effectPrefix = prop.getProperty("effectPrefix");
		
		List<Action> actions = problemDomain.getActions().getAction();
		for (Action action : actions) {
			this.addStringInNewLine(actionPrefix);
			this.addStringInLine(action.getName());
			this.addStringInNewLine(preconditionPrefix);
			List<Predicat> predicats = action.getPrecondition().getPredicat();
			writeActionParameters(predicats);
		}
	}

	private void writeActionParameters(List<Predicat> predicats) {
		String parametersPrefix = prop.getProperty("parametersPrefix");
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
		
		this.addStringInNewLine(parametersPrefix);

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
		String typesLine = prop.getProperty("types");
		String typesValues = problemDomain.getTypes().toString();
		typesLine = String.format(typesLine, typesValues);
		this.addStringInNewLine(typesLine);
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
		String string = prop.getProperty("domainPrefix");
		addStringInNewLine(string);
	}

	/**
	 * This method insert the static definition of the domain and its name
	 */
	private void buildDomainDefenitions() {
		stringBuilder.append("(");
		String domainDefenition = prop.getProperty("domainDefenition");
		domainDefenition = String.format(domainDefenition, problemDomain.getName());
		stringBuilder.append(domainDefenition + "\n");
	}

	public Domain getProblemDomain() {
		return problemDomain;
	}
}
