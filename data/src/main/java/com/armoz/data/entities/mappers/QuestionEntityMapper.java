package com.armoz.data.entities.mappers;

import com.armoz.data.entities.QuestionEntity;
import com.mindtrapped.model.Question;

import java.util.Collections;
import java.util.List;

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

    public List<Question> transform (List<QuestionEntity> questionEntityList){
        List<Question> questionList = Collections.EMPTY_LIST;
        /*for (QuestionEntity questionEntity: questionEntityList){
            questionList.add(transform(questionEntity));
        }*/
        return questionList;
    }
}
