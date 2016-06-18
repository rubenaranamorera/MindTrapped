package com.armoz.data.statisticsrepository.datasource;

import com.armoz.data.entities.StatisticsEntity;
import com.armoz.data.realmbase.RealmDatabase;

import io.realm.Realm;

public class RealmStatisticsDataStore implements StatisticsDataStore {

    RealmDatabase realmDatabase;

    public RealmStatisticsDataStore(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;
    }

    @Override
    public StatisticsEntity getStatisticsEntity() {
        Realm realm = realmDatabase.getRealmInstance();
        StatisticsEntity statisticsEntity = realm.where(StatisticsEntity.class).findFirst();
        StatisticsEntity statisticsEntityUnmanaged = realm.copyFromRealm(statisticsEntity);
        return statisticsEntityUnmanaged;
    }

    public void updateStatistics(final StatisticsEntity statisticsEntity) {
        Realm realm = realmDatabase.getRealmInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(statisticsEntity);
            }
        });
    }
}
