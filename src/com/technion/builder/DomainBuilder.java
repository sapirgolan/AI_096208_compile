package com.technion.builder;

import java.util.List;
import java.util.Properties;

import com.technion.ai.dao.Domain;
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

	private void buildTypes() {
		String typesLine = prop.getProperty("types");
		String typesValues = problemDomain.getTypes().toString();
		typesLine = String.format(typesLine, typesValues);
		this.addStringInNewLine(typesLine);
	}

	private void addStringInNewLine(String newLine) {
		stringBuilder.append("\n" + newLine);
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
