package com.armoz.data.repository.datasource;

import android.content.Context;

import com.armoz.data.entity.QuestionEntity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;

public class RealmQuestionDataStore implements QuestionDataStore {

    public RealmQuestionDataStore(Context context) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    @Override
    public Observable<QuestionEntity> questionEntity() {
        Realm realm = Realm.getDefaultInstance();
        return Observable.just(realm.copyFromRealm(realm.where(QuestionEntity.class).findFirst()));
    }

    @Override
    public Observable<Boolean> initializeDatabase() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(1);
        questionEntity.setQuestion("This is a question from RealmQuestionDataStore");
        questionEntity.setAnswer("yes");
        realm.copyToRealmOrUpdate(questionEntity);
        realm.commitTransaction();
        return Observable.just(true);
    }
}
