package com.armoz.data.statisticsrepository.datasource;

import com.armoz.data.entities.StatisticsEntity;
import com.armoz.data.realmbase.RealmDatabase;

import java.util.List;

import io.realm.Realm;

public class RealmStatisticsDataStore implements StatisticsDataStore {

    RealmDatabase realmDatabase;

    public RealmStatisticsDataStore(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;
    }

    @Override
    public void saveStatisticsEntity(final StatisticsEntity statisticsEntity) {
        Realm realm = realmDatabase.getRealmInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(statisticsEntity);
            }
        });
    }

    @Override
    public List<StatisticsEntity> getTopTotalCorrectAnswers(int limit) {
        Realm realm = realmDatabase.getRealmInstance();
        List<StatisticsEntity> statisticsEntityList =
                realm.where(StatisticsEntity.class).findAllSorted("correctQuestions");

        return realm.copyFromRealm(statisticsEntityList, limit);
    }

    @Override
    public List<StatisticsEntity> getTopConsecutiveCorrectAnswers(int limit) {
        Realm realm = realmDatabase.getRealmInstance();
        List<StatisticsEntity> statisticsEntityList =
                realm.where(StatisticsEntity.class).findAllSorted("correctQuestionsInARow");

        return realm.copyFromRealm(statisticsEntityList, limit);    }
}
