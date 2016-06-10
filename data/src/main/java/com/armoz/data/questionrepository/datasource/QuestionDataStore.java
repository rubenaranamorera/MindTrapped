package com.armoz.data.questionrepository.datasource;

import com.armoz.data.entities.QuestionEntity;

import io.realm.RealmList;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface QuestionDataStore {

    Observable<QuestionEntity> getUnseenQuestionEntity(RealmList<QuestionEntity> seenQuestionEntityList);

}
