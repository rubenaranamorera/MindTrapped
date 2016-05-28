package com.armoz.data.repository.datasource;

import com.armoz.data.entity.QuestionEntity;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface QuestionDataStore {

  /**
   * Get an {@link rx.Observable} which will emit a {@link QuestionEntity}
   */
  Observable<QuestionEntity> questionEntity();
}
