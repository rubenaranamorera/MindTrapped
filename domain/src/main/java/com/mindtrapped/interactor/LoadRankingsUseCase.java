
package com.mindtrapped.interactor;

import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.Ranking;
import com.mindtrapped.model.RankingBuilder;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.StatisticsRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class LoadRankingsUseCase extends UseCase {

    public static final int TOP_CORRECT_ANSWERS_NUM = 5;
    public static final int TOP_CONSECUTIVE_ANSWERS_NUM = 5;

    private final StatisticsRepository statisticsRepository;

    @Inject
    public LoadRankingsUseCase(StatisticsRepository statisticsRepository,
                               ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        List<Statistics> topTotalCorrectAnswersStatistics =
                statisticsRepository.getTopTotalCorrectAnswers(TOP_CORRECT_ANSWERS_NUM);
        List<Statistics> topConsecutiveCorrectAnswersStatistics =
                statisticsRepository.getTopConsecutiveCorrectAnswers(TOP_CONSECUTIVE_ANSWERS_NUM);

        Ranking ranking = RankingBuilder.rankingBuilder()
                .setTopConsecutiveCorrectAnswersStatistics(topConsecutiveCorrectAnswersStatistics)
                .setTopTotalCorrectAnswersStatistics(topTotalCorrectAnswersStatistics)
                .build();
        return Observable.just(ranking);
    }
}
