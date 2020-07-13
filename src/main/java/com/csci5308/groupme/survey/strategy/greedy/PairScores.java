package com.csci5308.groupme.survey.strategy.greedy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.LoggerFactory;

import com.csci5308.groupme.survey.constants.Criteria;
import com.csci5308.groupme.survey.model.Candidate;

import ch.qos.logback.classic.Logger;

public class PairScores implements GroupingHeuristic {

	private final Logger logger = (Logger) LoggerFactory.getLogger(PairScores.class);
	
	private int PIVOT_INDEX;
	private final int SIMILARITY = 1;
	private final int DISSIMILARITY = 2;
	private final int ATLEAST_ONE_WITH_MINIMUM = 3;
	private final int ATLEAST_ONE_WITH_MAXIMUM = 4;
	private Map<?, ?> pivotResponses;
	private Map<?, ?> groupMateCandidateResponses;
	private final double resolver = 1.25;
	    
	@Override
	public List<Candidate> compute(List<Candidate> candidates, Candidate... predicates) {
		if (candidates != null) {
			double pairScore;
			Candidate pivotCandidate = predicates[0];
			for (Candidate candidate : candidates) {
				candidate.setFitness(0.0);
			}
			for (Candidate candidate : candidates) {
				pairScore = calculatePairScore(pivotCandidate, candidate);
				candidate.setFitness(pairScore);
			}
		}
		return candidates;
	}

	public double calculatePairScore(Candidate pivotCandidate, Candidate groupMateCandidate) {
		double score = 0.0;
		this.pivotResponses = pivotCandidate.getQuestionResponsesMap();
		this.groupMateCandidateResponses = groupMateCandidate.getQuestionResponsesMap();
		List<String> questionIDs = new ArrayList<String>();
		for (Map.Entry<?, ?> entry : pivotResponses.entrySet()) {
			questionIDs.add((String) entry.getKey());
			// System.out.println(entry.getKey() + "=" + entry.getValue());
			// System.out.println(questionIDs);
		}
		for (String questionID : questionIDs) {
		    score += weight(questionID) * gain(questionID);
			//System.out.println((gain(questionID)));
		}
		System.out.println(score);
		return score;
	}

	private double gain(String questionID) {
		int criterion = Integer.parseInt((String) ((Map<?, ?>) pivotResponses.get(questionID)).get("criterion"));
		int expectedCriterion = 0;
		double gain = 0.00;
		if (criterion == Criteria.DISSIMILARITY) {
			expectedCriterion = DISSIMILARITY;
		} else {
			expectedCriterion = SIMILARITY;
		}
		double pivotAnswer = Double.parseDouble((String) ((Map<?, ?>) pivotResponses.get(questionID)).get("answer"));
		double candidateAnswer = Double.parseDouble((String) ((Map<?, ?>) groupMateCandidateResponses.get(questionID)).get("answer"));
		
		//Numeric
		if (expectedCriterion == SIMILARITY && (pivotAnswer - candidateAnswer) == 0) {
			gain = 1;
		}
		else {
		gain = Math.pow(resolver*Math.abs(pivotAnswer - candidateAnswer), Math.pow(-1, expectedCriterion));
		}
		
		//MCQ Choose one
		
		
		//MCQ Choose many
		
		
		return gain;
	}

	private int weight(String questionID) {
		// String weight = (String) ((Map<?, ?>)
		// pivotResponses.get(questionID)).get("weight");
		// return Integer.parseInt(weight);
		return 1;
	}

}
