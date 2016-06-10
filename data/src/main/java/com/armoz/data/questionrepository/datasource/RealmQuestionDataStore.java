package com.armoz.data.questionrepository.datasource;

import com.armoz.data.entities.QuestionEntity;
import com.armoz.data.realmbase.RealmDatabase;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import rx.Observable;
import rx.functions.Func0;

public class RealmQuestionDataStore implements QuestionDataStore {

    private RealmDatabase realmDatabase;

    public RealmQuestionDataStore(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;
    }

    @Override
    public Observable<QuestionEntity> getUnseenQuestionEntity(final RealmList<QuestionEntity> questionEntities) {

        return Observable.defer(new Func0<Observable<QuestionEntity>>() {
            @Override
            public Observable<QuestionEntity> call() {
                try {
                    Realm realm = realmDatabase.getRealmInstance();

                    RealmQuery<QuestionEntity> query = realm.where(QuestionEntity.class);
                    for (QuestionEntity questionEntity : questionEntities) {
                        query = query.not().equalTo("id", questionEntity.getId());
                    }

                    QuestionEntity questionEntity = query.findFirst();
                    QuestionEntity questionEntityUnmanaged = realm.copyFromRealm(questionEntity);

                    return Observable.just(questionEntityUnmanaged);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }
}