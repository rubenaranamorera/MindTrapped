package com.armoz.data.questionrepository.datasource;

import com.armoz.data.entities.QuestionEntity;
import com.armoz.data.realmbase.RealmDatabase;

import io.realm.Realm;
import rx.Observable;

public class RealmQuestionDataStore implements QuestionDataStore {

    private RealmDatabase realmDatabase;

    public RealmQuestionDataStore(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;
    }

    @Override
    public Observable<QuestionEntity> questionEntity() {
        Realm realm = realmDatabase.getRealmInstance();
        QuestionEntity questionEntity = realm.where(QuestionEntity.class).findFirst();
        QuestionEntity questionEntityUnmanaged = realm.copyFromRealm(questionEntity);
        return Observable.just(questionEntityUnmanaged);
    }

    @Override
    public Observable<Boolean> initializeDatabase() {
        /*String json = readJsonFromRawResource(R.raw.questions);
        Type type = new TypeToken<List<QuestionEntity>>() {}.getType();
        List<QuestionEntity> questionEntityList = new Gson().fromJson(json, type);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(questionEntityList);
        realm.commitTransaction();*/
        return Observable.just(true);
    }


}
