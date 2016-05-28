package com.armoz.data.repository.datasource;

import com.armoz.data.entity.QuestionEntity;

import rx.Observable;

/**
 * {@link QuestionDataStore} implementation based on file system data store.
 */
public class FakeQuestionDataStore implements QuestionDataStore {

    public FakeQuestionDataStore() {
    }

    @Override
    public Observable<QuestionEntity> questionEntity() {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(1);
        questionEntity.setQuestion("This is a question from sharedPreferencesQuestionDataStore");
        questionEntity.setAnswer("yes");

        return Observable.just(questionEntity);
    }
}
