package com.armoz.data.statisticsrepository;

import com.armoz.data.entities.mappers.StatisticsEntityMapper;
import com.armoz.data.statisticsrepository.datasource.StatisticsDataStoreFactory;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.StatisticsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StatisticsDataRepository implements StatisticsRepository {

    private final StatisticsDataStoreFactory statisticsDataStoreFactory;
    private final StatisticsEntityMapper statisticsEntityMapper;

    @Inject
    public StatisticsDataRepository(StatisticsDataStoreFactory statisticsDataStoreFactory,
                                    StatisticsEntityMapper statisticsEntityMapper) {
        this.statisticsDataStoreFactory = statisticsDataStoreFactory;
        this.statisticsEntityMapper = statisticsEntityMapper;
    }

    @Override
    public void saveStatistics(Statistics statistics) {
        statisticsDataStoreFactory.create().saveStatisticsEntity(statisticsEntityMapper.transformToDataModel(statistics));
    }
}
