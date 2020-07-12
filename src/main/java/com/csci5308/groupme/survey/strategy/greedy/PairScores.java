package com.csci5308.groupme.survey.strategy.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.LoggerFactory;

import com.csci5308.groupme.survey.model.Candidate;

import ch.qos.logback.classic.Logger;

//@SuppressWarnings("unchecked")
public class PairScores implements GroupingHeuristic {

	private final Logger logger = (Logger) LoggerFactory.getLogger(PairScores.class);
	//private List<Candidate> candidates;
	private Float pairScore;
	private int PIVOT_INDEX;
	private final int SIMILARITY = 1;
	private final int DISSIMILARITY = 2;
	private final int ATLEAST_ONE_WITH_MINIMUM = 3;
	private final int ATLEAST_ONE_WITH_MAXIMUM = 4;


	@Override
	public List<Candidate> compute(List<Candidate> candidates, Candidate... predicates) {
		if (candidates != null) {
			Candidate pivotCandidate = predicates[0];
			for (Candidate candidate : candidates) {
				candidate.setFitness(0.0f);
			}
			for (Candidate candidate : candidates) {
				pairScore = calculatePairScore(pivotCandidate, candidate);
				candidate.setFitness(pairScore);
			}
		}
		return candidates;
	}

	private Float calculatePairScore(Candidate pivotCandidate, Candidate candidate) {
		int score = 0;
		//String pivotResponses = pivotCandidate.getResponses();
		//String candidateResponses = candidate.getResponses();
		//Map<String, Integer> 
		//Map<Integer, Map<String, Integer>> pivotResponses = new HashMap<Integer, Map<String,Integer>>();
	//	pivotResponses.put(1, );
		List<Map<String, Integer>> pivotResponses = new ArrayList<Map<String,Integer>>();
		List<Map<String, Integer>> candidateResponses = new ArrayList<Map<String,Integer>>();
	//	pivotResponses.add(Map.of("criterion", 1, "weight", 4, ));
		Integer[] r1 = { 8, 3, 300 };
		Integer[] r2 = { 100, 200, 300 };
		//for()
		return null;
	}

	

}
