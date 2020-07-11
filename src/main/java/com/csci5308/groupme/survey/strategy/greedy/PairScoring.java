package com.csci5308.groupme.survey.strategy.greedy;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.csci5308.groupme.survey.model.Candidate;

import ch.qos.logback.classic.Logger;

@SuppressWarnings("unchecked")
public class PairScoring implements GroupingHeuristic {

	private final Logger logger = (Logger) LoggerFactory.getLogger(PairScoring.class);
	private List<Candidate> candidates;
	private Float score;

	@Override
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	@Override
	public Map<Candidate, List<Candidate>> compute() {
		if (candidates != null) {
			Candidate pivotCandidate = candidates.get(candidates.size() - 1);

		}

		return null;
	}

}
