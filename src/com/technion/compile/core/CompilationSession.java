package com.technion.compile.core;

import com.technion.compile.ore.PredicateMap;


public class CompilationSession {
	
	private PredicateMap predicateMap;
	/****** SingleTone area  ****/
	private CompilationSession() {
		this.predicateMap = new PredicateMap();
	}
	
	private static CompilationSession instance;
	
	public static CompilationSession getInstance() {
		if (instance == null) {
			instance = new CompilationSession();
		}
		return instance;
	}
	
	/***** Rest of code *****/
	
	public PredicateMap getPredicateMap() {
		return predicateMap;
	}
}
