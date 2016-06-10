package com.armoz.data.statisticsrepository;

import com.armoz.data.entities.StatisticsEntity;
import com.armoz.data.entities.mappers.StatisticsEntityMapper;
import com.armoz.data.statisticsrepository.datasource.StatisticsDataStoreFactory;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.StatisticsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

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
    public Observable<Statistics> getStatistics() {
        return statisticsDataStoreFactory.create().getStatisticsEntity().map(new Func1<StatisticsEntity, Statistics>() {
            @Override
            public Statistics call(StatisticsEntity statisticsEntity) {
                return statisticsEntityMapper.transformToDomainModel(statisticsEntity);
            }
        });
    }

    @Override
    public Observable<Void> updateStatistics(Statistics statistics) {
        return statisticsDataStoreFactory.create().updateStatistics(statisticsEntityMapper.transformToDataModel(statistics));
    }
}
