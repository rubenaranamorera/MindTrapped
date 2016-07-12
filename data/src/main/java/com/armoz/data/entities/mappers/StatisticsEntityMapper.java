package com.armoz.data.entities.mappers;

import com.armoz.data.entities.StatisticsEntity;
import com.mindtrapped.model.Statistics;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StatisticsEntityMapper {

    @Inject
    public StatisticsEntityMapper() {
    }

    public Statistics transformToDomainModel(StatisticsEntity statisticsEntity) {
        Statistics statistics = new Statistics();
        statistics.setId(statisticsEntity.getId());
        statistics.setCorrectQuestions(statisticsEntity.getCorrectQuestions());
        statistics.setCorrectQuestionsInARow(statisticsEntity.getCorrectQuestionsInARow());
        statistics.setDate(statisticsEntity.getDate());
        return statistics;
    }

    public StatisticsEntity transformToDataModel(Statistics statistics) {
        StatisticsEntity statisticsEntity = new StatisticsEntity();
        statisticsEntity.setId(statistics.getId());
        statisticsEntity.setCorrectQuestions(statistics.getCorrectQuestions());
        statisticsEntity.setCorrectQuestionsInARow(statistics.getCorrectQuestionsInARow());
        statisticsEntity.setDate(statistics.getDate());
        return statisticsEntity;
    }
}
