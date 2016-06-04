package com.armoz.data.statisticsrepository.datasource;

import com.armoz.data.realmbase.RealmDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StatisticsDataStoreFactory {

    private RealmDatabase realmDatabase;

    @Inject
    public StatisticsDataStoreFactory(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;
    }

    public RealmStatisticsDataStore create() {
        return new RealmStatisticsDataStore(realmDatabase);
    }

}
