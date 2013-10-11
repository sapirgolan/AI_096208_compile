package com.technion.compile.businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.technion.ai.dao.Predicat;
import com.technion.ai.wrappers.DomainWrapper;
import com.technion.ai.wrappers.PredicateWrapper;
import com.technion.compile.core.CompilationSession;
import com.technion.compile.ore.PredicateMap;

/**
 * <p>This object contains a map of preconditions {@link Predicat}.
 * for each level: 0,1,2... it contains all original predicates and another new prdicate called 'Open Predicate'
 * @author XPS_Sapir
 *
 */
public class PreconditionBusinessLayer extends AbstractBusinessLayer{

	private HashMap<Integer, List<PredicateWrapper>> predicatMap;
	
	public PreconditionBusinessLayer( ) {
		super();
		this.predicatMap = new HashMap<Integer, List<PredicateWrapper>>();
	}

	public HashMap<Integer, List<PredicateWrapper>> buildNewPredicates (DomainWrapper problemDomain) {
		PredicateMap sessionPredicateMap = CompilationSession.getInstance().getPredicateMap();
		int effectsNumber = problemDomain.getEffectsNumber();
		List<PredicateWrapper> originalPredicats = problemDomain.getPredicat();
		
		for (int i = 0; i <= effectsNumber; i++) {
			ArrayList<PredicateWrapper> list = new ArrayList<PredicateWrapper>();
			Integer index = Integer.valueOf(i);
			predicatMap.put(index, list);
			
			for (PredicateWrapper originalPredicate : originalPredicats) {
				PredicateWrapper newPredicate = originalPredicate.clone();
				//renaming predicates
				String nameSuffix = getLevelString(i);
				newPredicate.setName( originalPredicate.getName() + nameSuffix );
				list.add(newPredicate);
				//add new predicate to session so it will be fetch later on
				sessionPredicateMap.addPredicate(originalPredicate, i, newPredicate);
			}
			PredicateWrapper openPredicate = createOpenPredicate(index);
			sessionPredicateMap.addOpenPredicate(index, openPredicate);
			list.add( openPredicate );
		}
		return predicatMap;
	}

	private PredicateWrapper createOpenPredicate(int index) {
		Predicat openPredicate = new Predicat();
		openPredicate.setName( getOpenActionName(index) );
		PredicateWrapper wrapper = new PredicateWrapper(openPredicate);
		return wrapper;
	}

}
