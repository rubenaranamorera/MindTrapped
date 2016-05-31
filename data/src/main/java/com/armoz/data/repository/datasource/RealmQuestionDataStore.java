package com.armoz.data.repository.datasource;

import android.content.Context;

import com.armoz.data.entity.QuestionEntity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;

public class RealmQuestionDataStore implements QuestionDataStore {

    private Realm realm;

    public RealmQuestionDataStore(Context context) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);

        // Get a Realm instance for this thread
        realm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<QuestionEntity> questionEntity() {
        return Observable.just(realm.where(QuestionEntity.class).findFirst());
    }
}
