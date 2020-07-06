package com.csci5308.groupme.survey.service;

import java.util.List;

import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.model.SurveyResponse;
import com.csci5308.groupme.survey.strategy.GroupingStrategy;

public class GroupFormationServiceImpl implements GroupFormationService{

	private GroupingStrategy groupingStrategy;
	
	public void setGroupingStrategy(GroupingStrategy groupingStrategy) {
		this.groupingStrategy = groupingStrategy;
	}
	
	public GroupingStrategy getGroupingStrategy() {
		return groupingStrategy;
	}

	@Override
	public List<Group> formGroups(List<SurveyResponse> surveyResponses, Integer groupSize) {
		
		return null;
	}
	
}
