package com.armoz.data.statisticsrepository;

import com.armoz.data.entities.StatisticsEntity;
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
    public Statistics getStatistics() {
        StatisticsEntity statisticsEntity = statisticsDataStoreFactory.create().getStatisticsEntity();
        return statisticsEntityMapper.transformToDomainModel(statisticsEntity);
    }

    @Override
    public void updateStatistics(Statistics statistics) {
        statisticsDataStoreFactory.create().updateStatistics(statisticsEntityMapper.transformToDataModel(statistics));
    }
}
