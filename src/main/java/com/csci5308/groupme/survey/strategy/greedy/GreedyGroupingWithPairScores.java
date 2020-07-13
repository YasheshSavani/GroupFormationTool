package com.csci5308.groupme.survey.strategy.greedy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.csci5308.groupme.survey.model.Candidate;
import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.strategy.GroupingStrategy;

@SuppressWarnings("unchecked")
public class GreedyGroupingWithPairScores implements GroupingStrategy {

	private GroupingHeuristic groupingHeuristic;
	private List<Group> groups;
	private List<Candidate> pivotCandidates;

	@Override
	public void setGroupingHeuristic(GroupingHeuristic groupingHeuristic) {
		this.groupingHeuristic = groupingHeuristic;
	}

	@Override
	public List<Group> getGroups(List<Candidate> candidates, Integer groupSize) {
		// Set<Candidate> allCandidates = new LinkedHashSet<Candidate>(candidates);
		pivotCandidates = selectPivotCandidates(candidates);
		if (pivotCandidates.isEmpty()) {
			groups = getGroupsUsingArbitraryPivots(candidates, groupSize);
		} else {
			groups = getGroupsUsingObtainedPivots(candidates, groupSize);
		}
		return groups;
	}

	private List<Group> getGroupsUsingArbitraryPivots(List<Candidate> allCandidates, Integer groupSize) {
		int totalNumberOfCandidates = allCandidates.size();
		List<Candidate> pivotCandidates = new ArrayList<Candidate>();
		int groupNo = 1;
		for (int i = 0; i < totalNumberOfCandidates / groupSize; i++) {
			pivotCandidates.add(allCandidates.remove(i));
		}
		for (Candidate pivotCandidate : pivotCandidates) {
			Group group = generateOneGreedyGroup(allCandidates, pivotCandidate, groupSize);
			group.setGroupNo(groupNo++);
			group.setPivotCandidate(pivotCandidate);
			groups.add(group);
			allCandidates.removeAll(group.getCandidates());
		}
		return groups;
	}

	private List<Group> getGroupsUsingObtainedPivots(List<Candidate> allCandidates, Integer groupSize) {
		return null;
	}

	private Group generateOneGreedyGroup(List<Candidate> allCandidates, Candidate pivotCandidate, Integer groupSize) {
		List<Candidate> groupMateCandidates = (List<Candidate>) groupingHeuristic.compute(allCandidates,
				pivotCandidate);
		groupMateCandidates
				.sort((candidate1, candidate2) -> Double.compare(candidate2.getFitness(), candidate1.getFitness()));
		Group group = new Group();
		group.add(pivotCandidate);
		for (int i = 0; i < groupSize - 1; i++) {
			group.add(groupMateCandidates.get(i));
		}
		return group;
	}

	private List<Candidate> selectPivotCandidates(List<Candidate> candidates) {
		List<Candidate> pivots = new ArrayList<Candidate>();
		for (Candidate candidate : candidates) {
			if (meetsBoundedCriteria(candidate)) {
				pivots.add(candidate);
			}
		}
		return pivots;
	}

	private boolean meetsBoundedCriteria(Candidate candidate) {

		return false;
	}
}
