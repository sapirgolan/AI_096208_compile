package com.technion.builder;

import java.util.Properties;

import com.technion.ai.dao.Domain;
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
		}
		return this.stringBuilder;
	}
	
	
	/**
	 * This method insert the static definition of the domain and its name
	 */
	private void buildDomainDefenitions() {
		stringBuilder.append("(");
		String domainDefenition = prop.getProperty("domainDefenition");
		domainDefenition = String.format(domainDefenition, problemDomain.getName());
		stringBuilder.append(domainDefenition);
	}
}
