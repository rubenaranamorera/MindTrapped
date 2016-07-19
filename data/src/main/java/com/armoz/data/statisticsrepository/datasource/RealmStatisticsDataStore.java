package com.armoz.data.statisticsrepository.datasource;

import com.armoz.data.entities.StatisticsEntity;
import com.armoz.data.realmbase.RealmDatabase;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.Sort;

public class RealmStatisticsDataStore implements StatisticsDataStore {

    RealmDatabase realmDatabase;

    public RealmStatisticsDataStore(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;
    }

    @Override
    public void saveStatisticsEntity(final StatisticsEntity statisticsEntity) {
        Realm realm = realmDatabase.getRealmInstance();
        statisticsEntity.setId(UUID.randomUUID().toString());

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(statisticsEntity);
            }
        });
    }

    @Override
    public List<StatisticsEntity> getTopTotalCorrectAnswers(int limit) {
        Realm realm = realmDatabase.getRealmInstance();
        List<StatisticsEntity> statisticsEntityList =
                realm.where(StatisticsEntity.class).findAllSorted("correctQuestions", Sort.DESCENDING);

        return realm.copyFromRealm(statisticsEntityList.subList(0, limit));
    }

    @Override
    public List<StatisticsEntity> getTopConsecutiveCorrectAnswers(int limit) {
        Realm realm = realmDatabase.getRealmInstance();
        List<StatisticsEntity> statisticsEntityList =
                realm.where(StatisticsEntity.class).findAllSorted("correctQuestionsInARow", Sort.DESCENDING);

        return realm.copyFromRealm(statisticsEntityList.subList(0, limit));
    }
}
