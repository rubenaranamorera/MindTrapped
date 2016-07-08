package com.armoz.data.questionrepository.datasource.QuestionContent;

import com.armoz.data.entities.QuestionContentEntity;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface QuestionContentDataStore {

    QuestionContentEntity getQuestionContent(int id);

}
