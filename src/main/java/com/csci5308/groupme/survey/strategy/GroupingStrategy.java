package com.csci5308.groupme.survey.strategy;

import java.util.List;

import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.model.SurveyResponse;

public interface GroupingStrategy {

	public List<Group> group(List<SurveyResponse> surveyResponses, Integer groupSize);
}
