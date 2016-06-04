package com.armoz.data.statisticsrepository.datasource;

import com.armoz.data.entities.StatisticsEntity;

import rx.Observable;

public interface StatisticsDataStore {

    Observable<StatisticsEntity> getStatisticsEntity();

}
