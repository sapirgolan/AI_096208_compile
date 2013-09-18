package com.technion.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

	public static void writeTextFile(String fileName, String fileContent)  {
		BufferedWriter bufferWritter = null;
		
		if (fileName == null) {
			fileName = "textFile.pddl";
		}
		File file = new File(fileName);

		//Delete files it it already exists
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			//true = append file
			FileWriter fileWritter = new FileWriter(file.getName(),true);
			bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(fileContent);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (bufferWritter != null) {
				try {
					bufferWritter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Done");
		} //end finally

	}
}
