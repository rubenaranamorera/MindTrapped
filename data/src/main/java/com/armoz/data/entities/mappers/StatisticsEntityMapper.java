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

    public Statistics transform(StatisticsEntity statisticsEntity) {
        Statistics statistics = new Statistics();
        statistics.setCorrectQuestionList(questionEntityMapper.transform(statisticsEntity.getCorrectQuestionList()));
        statistics.setQuestionSeenList(questionEntityMapper.transform(statisticsEntity.getQuestionSeenList()));
        statistics.setCorrectQuestionsInARow(statisticsEntity.getCorrectQuestionsInARow());
        return statistics;
    }
}
