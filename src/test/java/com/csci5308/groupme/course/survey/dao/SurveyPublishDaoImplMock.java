package com.csci5308.groupme.course.survey.dao;

import com.csci5308.groupme.course.survey.dao.SurveyPublishDao;

public class SurveyPublishDaoImplMock implements SurveyPublishDao {

    @Override
    public int publishSurveyForStudents(String roleName, String courseCode) {
        return 1;
    }
}
