package com.armoz.data.entity.mapper;

import com.armoz.data.entity.QuestionEntity;
import com.mindtrapped.model.Question;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton

public class QuestionEntityMapper {

    @Inject
    public QuestionEntityMapper() {
    }

    public Question transform(QuestionEntity questionEntity) {
        Question question = new Question();
        question.setId(questionEntity.getId());
        question.setAnswer(questionEntity.getAnswer());
        question.setQuestion(questionEntity.getQuestion());
        return question;
    }
}
