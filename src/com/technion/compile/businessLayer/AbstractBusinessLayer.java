package com.technion.compile.businessLayer;

import java.util.Properties;

import com.technion.utils.Utils;

public abstract class AbstractBusinessLayer {
	private static final String PROPERTIES_PATH = "/com/technion/properties/strings.properties";
	private Properties prop;
	private String openActionName;
	
	public AbstractBusinessLayer(){
		init();
	}
	
	private void init() {
		this.prop = Utils.initPropertiesFile(PROPERTIES_PATH);
		if (openActionName == null) {
			openActionName = prop.getProperty("openActionName","Open%d");
		}
	}
	
	protected String getOpenActionName(int indexLevel) {
		return String.format(openActionName, indexLevel);
	}

}
