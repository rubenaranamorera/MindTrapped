package com.armoz.data.statisticsrepository.datasource;

import com.armoz.data.entities.StatisticsEntity;

public interface StatisticsDataStore {

    void saveStatisticsEntity(StatisticsEntity statisticsEntity);

}
