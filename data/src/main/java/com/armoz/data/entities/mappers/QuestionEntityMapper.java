package com.armoz.data.entities.mappers;

import com.armoz.data.entities.QuestionEntity;
import com.mindtrapped.model.Question;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmList;

@Singleton

public class QuestionEntityMapper {

    @Inject
    public QuestionEntityMapper() {
    }

    public Question transformToDomainModel(QuestionEntity questionEntity) {
        Question question = new Question();
        question.setId(questionEntity.getId());
        question.setAnswer(questionEntity.getAnswer());
        question.setQuestion(questionEntity.getQuestion());
        return question;
    }

    public List<Question> transformToDomainModel(List<QuestionEntity> questionEntityList){
        List<Question> questionList = new ArrayList<>();
        for (QuestionEntity questionEntity: questionEntityList){
            questionList.add(transformToDomainModel(questionEntity));
        }
        return questionList;
    }

    public QuestionEntity transformToDataModel(Question question) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(question.getId());
        questionEntity.setAnswer(question.getAnswer());
        questionEntity.setQuestion(question.getQuestion());
        return questionEntity;
    }

    public RealmList<QuestionEntity> transformToDataModel(List<Question> questionList){
        RealmList<QuestionEntity> questionEntityList = new RealmList<>();
        for (Question question: questionList){
            questionEntityList.add(transformToDataModel(question));
        }
        return questionEntityList;
    }




}
