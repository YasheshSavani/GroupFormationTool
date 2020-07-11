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
		List<Group> groups = new ArrayList<Group>();
		Set<Candidate> allCandidates = new LinkedHashSet<Candidate>(candidates);
		pivotCandidates = selectPivotCandidates(candidates);
		if (pivotCandidates.isEmpty()) {
			groups = getGroupsUsingArbitraryPivots(allCandidates, groupSize);
		} else {
			groups = getGroupsUsingObtainedPivots(allCandidates, groupSize);
		}

		// Consider having Set<Candidate>

		return groups;
	}

	private List<Group> getGroupsUsingArbitraryPivots(Set<Candidate> allCandidates, Integer groupSize) {
		List<Group> groups = new ArrayList<Group>();
		int totalNumberOfCandidates = allCandidates.size();
		for (int i = 0; i < totalNumberOfCandidates / groupSize; i++) {
			Group group = generateOneGreedyGroup(allCandidates, groupSize);
			groups.add(group);
			allCandidates.removeAll(group.getCandidates());
		}
		return groups;
	}

	private List<Group> getGroupsUsingObtainedPivots(Set<Candidate> allCandidates, Integer groupSize) {
		return null;
	}

	private Group generateOneGreedyGroup(Set<Candidate> candidates, Integer groupSize) {
		List<Candidate> allCandidates = new ArrayList<Candidate>(candidates);
		groupingHeuristic.setCandidates(allCandidates);
		Map<Candidate, List<Candidate>> pivotAndGroupMatesMap = groupingHeuristic.compute();
		Map.Entry<Candidate, List<Candidate>> entry = pivotAndGroupMatesMap.entrySet().iterator().next();
		Candidate pivotCandidate = entry.getKey();
		List<Candidate> groupMateCandidates = entry.getValue();
		groupMateCandidates
				.sort((candidate1, candidate2) -> Math.round(candidate2.getFitness() - candidate1.getFitness()));
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
