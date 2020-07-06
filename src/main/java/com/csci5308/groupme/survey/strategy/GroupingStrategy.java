package com.csci5308.groupme.survey.strategy;

import java.util.List;

import com.csci5308.groupme.survey.model.Group;

public interface GroupingStrategy {

	public List<Group> group();
}
