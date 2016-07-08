package com.armoz.data.entities.mappers;

import com.armoz.data.entities.QuestionContentEntity;
import com.armoz.data.entities.QuestionEntity;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.QuestionTypeEnum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmList;

@Singleton

public class QuestionEntityMapper {

    @Inject
    public QuestionEntityMapper() {
    }

    public Question transformToDomainModel(QuestionEntity questionEntity, QuestionContentEntity questionContentEntity) {
        Question question = new Question();
        question.setId(questionEntity.getId());
        question.setQuestionType(getQuestionTypeEnum(questionEntity.getQuestionType()));
        question.setQuestion(questionContentEntity.getQuestion());
        question.setAnswerA(questionContentEntity.getAnswerA());
        question.setAnswerB(questionContentEntity.getAnswerB());
        question.setAnswerC(questionContentEntity.getAnswerC());
        question.setAnswerD(questionContentEntity.getAnswerD());
        question.setCorrectAnswer(questionContentEntity.getCorrectAnswer());
        return question;
    }

    public Question transformToDomainModel(QuestionEntity questionEntity) {
        Question question = new Question();
        question.setId(questionEntity.getId());
        question.setQuestionType(getQuestionTypeEnum(questionEntity.getQuestionType()));
        return question;
    }

    public Set<Question> transformToDomainModel(List<QuestionEntity> questionEntityList){
        Set<Question> questionList = new HashSet<>();
        for (QuestionEntity questionEntity: questionEntityList){
            questionList.add(transformToDomainModel(questionEntity));
        }
        return questionList;
    }

    public QuestionEntity transformToDataModel(Question question) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(question.getId());
        questionEntity.setQuestionType(question.getQuestionType().toString());
        return questionEntity;
    }

    public RealmList<QuestionEntity> transformToDataModel(Set<Question> questionList){
        RealmList<QuestionEntity> questionEntityList = new RealmList<>();
        for (Question question: questionList){
            questionEntityList.add(transformToDataModel(question));
        }
        return questionEntityList;
    }


    private QuestionTypeEnum getQuestionTypeEnum(String type){
        if (QuestionTypeEnum.GENERAL.toString().equals(type)){
            return QuestionTypeEnum.GENERAL;
        }
        if (QuestionTypeEnum.MATH.toString().equals(type)){
            return QuestionTypeEnum.MATH;
        }
        if (QuestionTypeEnum.LOGIC.toString().equals(type)){
            return QuestionTypeEnum.LOGIC;
        }
        return null;
    }



}
