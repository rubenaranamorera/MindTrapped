package com.armoz.data.statisticsrepository.datasource;

import com.armoz.data.entities.StatisticsEntity;
import com.armoz.data.realmbase.RealmDatabase;

import io.realm.Realm;
import rx.Observable;

public class RealmStatisticsDataStore implements StatisticsDataStore {

    RealmDatabase realmDatabase;

    public RealmStatisticsDataStore(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;

        /*RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).
                initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        StatisticsEntity statisticsEntity = new StatisticsEntity();
                        statisticsEntity.setCorrectQuestionsInARow(0);
                        statisticsEntity.setQuestionSeenList(new RealmList<QuestionEntity>());
                        statisticsEntity.setCorrectQuestionList(new RealmList<QuestionEntity>());
                        realm.copyToRealm(statisticsEntity);
                    }
                })
                .build();
        Realm.setDefaultConfiguration(realmConfig);*/
    }

    @Override
    public Observable<StatisticsEntity> getStatisticsEntity() {
        Realm realm = realmDatabase.getRealmInstance();
        StatisticsEntity statisticsEntity = realm.where(StatisticsEntity.class).findFirst();
        StatisticsEntity statisticsEntityUnmanaged = realm.copyFromRealm(statisticsEntity);

        return Observable.just(statisticsEntityUnmanaged);
    }
}
