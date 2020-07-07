package com.csci5308.groupme.survey.strategy.greedy;

import java.util.List;

import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.model.SurveyResponse;
import com.csci5308.groupme.survey.strategy.GroupingStrategy;

public class GreedyGrouping implements GroupingStrategy{

	Heuristic heuristic;
	
	public GreedyGrouping(Heuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	@Override
	public List<Group> group(List<SurveyResponse> surveyResponses, Integer groupSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
