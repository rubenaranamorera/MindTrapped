package com.armoz.data.statisticsrepository.datasource;

import com.armoz.data.entities.StatisticsEntity;
import com.armoz.data.realmbase.RealmDatabase;

import io.realm.Realm;
import rx.Observable;
import rx.functions.Func0;

public class RealmStatisticsDataStore implements StatisticsDataStore {

    RealmDatabase realmDatabase;

    public RealmStatisticsDataStore(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;
    }

    @Override
    public Observable<StatisticsEntity> getStatisticsEntity() {

        return Observable.defer(new Func0<Observable<StatisticsEntity>>() {
            @Override
            public Observable<StatisticsEntity> call() {
                try {
                    Realm realm = realmDatabase.getRealmInstance();
                    StatisticsEntity statisticsEntity = realm.where(StatisticsEntity.class).findFirst();
                    StatisticsEntity statisticsEntityUnmanaged = realm.copyFromRealm(statisticsEntity);

                    return Observable.just(statisticsEntityUnmanaged);
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }

    public Observable<Void> updateStatistics(final StatisticsEntity statisticsEntity) {

        return Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                try {
                    Realm realm = realmDatabase.getRealmInstance();
                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(statisticsEntity);
                        }
                    });
                    return Observable.empty();
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }
        });
    }
}
