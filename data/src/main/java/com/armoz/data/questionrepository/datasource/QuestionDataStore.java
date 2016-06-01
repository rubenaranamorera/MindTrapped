package com.armoz.data.questionrepository.datasource;

import com.armoz.data.entities.QuestionEntity;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface QuestionDataStore {

  Observable<QuestionEntity> questionEntity();

  Observable<Boolean> initializeDatabase();
}
