package com.csci5308.groupme.survey.strategy.greedy;

import com.csci5308.groupme.survey.model.SurveyResponse;

public class Candidate {
	private SurveyResponse response;
	private Float fitness;	
	
	public Candidate(SurveyResponse response) {
		this.response = response;
	}

	public Float getFitness() {
		return fitness;
	}

	public void setFitness(Float fitness) {
		this.fitness = fitness;
	}
}
