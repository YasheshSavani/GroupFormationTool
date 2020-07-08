package com.csci5308.groupme.survey.strategy.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.model.SurveyResponse;
import com.csci5308.groupme.survey.strategy.GroupingStrategy;

public class GreedyGroupingWithPairScores implements GroupingStrategy{

	private GroupingHeuristic groupingHeuristic;
	private List<Group> groups;
	
	@Override
	public void setGroupingHeuristic(GroupingHeuristic groupingHeuristic) {
		this.groupingHeuristic = groupingHeuristic;
	}
	
	@Override
	public List<Group> group(List<Candidate> candidates, Integer groupSize) {
		groupingHeuristic.setCandidates(candidates);
		Map<Candidate, List<Candidate>> candidateGroupMatesMap =  groupingHeuristic.compute();
		Map.Entry<Candidate,List<Candidate>> entry = candidateGroupMatesMap.entrySet().iterator().next();
		Candidate alphaCandidate = entry.getKey();
		List<Candidate> groupMateCandidates = entry.getValue();
		//Collections.sort(groupMateCandidates, (candidate1, candidate2) -> candidate2.getFitness() - candidate1.getFitness());
		//Arrays.sort();
		return null;
	}

}
