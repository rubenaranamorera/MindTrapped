package com.armoz.data.entities.mappers;

import com.armoz.data.entities.StatisticsEntity;
import com.mindtrapped.model.Statistics;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton

public class StatisticsEntityMapper {

    private QuestionEntityMapper questionEntityMapper;

    @Inject
    public StatisticsEntityMapper(QuestionEntityMapper questionEntityMapper) {
        this.questionEntityMapper = questionEntityMapper;
    }

    public Statistics transformToDomainModel(StatisticsEntity statisticsEntity) {
        Statistics statistics = new Statistics();
        statistics.setId(statisticsEntity.getId());
        statistics.setCorrectQuestionList(questionEntityMapper.transformToDomainModel(statisticsEntity.getCorrectQuestionList()));
        statistics.setQuestionSeenList(questionEntityMapper.transformToDomainModel(statisticsEntity.getQuestionSeenList()));
        statistics.setCorrectQuestionsInARow(statisticsEntity.getCorrectQuestionsInARow());
        return statistics;
    }

    public StatisticsEntity transformToDataModel(Statistics statistics) {
        StatisticsEntity statisticsEntity = new StatisticsEntity();
        statisticsEntity.setId(statistics.getId());
        statisticsEntity.setCorrectQuestionList(questionEntityMapper.transformToDataModel(statistics.getCorrectQuestionList()));
        statisticsEntity.setQuestionSeenList(questionEntityMapper.transformToDataModel(statistics.getQuestionSeenList()));
        statisticsEntity.setCorrectQuestionsInARow(statisticsEntity.getCorrectQuestionsInARow());
        return statisticsEntity;
    }
}
