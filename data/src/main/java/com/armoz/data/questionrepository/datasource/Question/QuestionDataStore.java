package com.armoz.data.questionrepository.datasource.Question;

import com.armoz.data.entities.QuestionEntity;
import com.mindtrapped.exception.NoMoreQuestionsFoundException;

import io.realm.RealmList;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface QuestionDataStore {

    QuestionEntity getUnseenQuestionEntity(RealmList<QuestionEntity> seenQuestionEntityList) throws NoMoreQuestionsFoundException;

}
