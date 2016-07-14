package com.armoz.data.statisticsrepository.datasource;

import com.armoz.data.entities.StatisticsEntity;

import java.util.List;

public interface StatisticsDataStore {

    void saveStatisticsEntity(StatisticsEntity statisticsEntity);

    List<StatisticsEntity> getTopTotalCorrectAnswers(int limit);

    List<StatisticsEntity> getTopConsecutiveCorrectAnswers(int limit);
}
