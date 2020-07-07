package com.csci5308.groupme.survey.strategy.greedy;

import com.csci5308.groupme.survey.model.SurveyResponse;

@SuppressWarnings("unchecked")
public class PairScore implements Heuristic {

	private SurveyResponse response1, response2;
	Float score;
		
	public PairScore(SurveyResponse response1, SurveyResponse response2) {
		this.response1 = response1;
		this.response2 = response2;
	}
		
	@Override
	public Float calculate() {
		
		return 0.0f;
	}

}
