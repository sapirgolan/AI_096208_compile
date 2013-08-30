package com.technion.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	public static Properties initPropertiesFile(String propertiesPath) {
		Properties prop = new Properties(); 
		InputStream resourceAsStream = prop.getClass().getResourceAsStream(propertiesPath);
		//Can also use:
		//java.io.FileInputStream file = new java.io.FileInputStream("resources/com/technion/properties/strings.properties");
		try {
			prop.load(resourceAsStream);
		} catch (IOException e) {
			System.err.println("Failed to load properties file");
			e.printStackTrace();
			return null;
		}
		return prop;
	}
}
