
package com.mindtrapped.interactor;

import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.StatisticsRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class UpdateStatisticsUseCase extends UseCase {

    private final StatisticsRepository statisticsRepository;
    private Statistics statistics;

    @Inject
    public UpdateStatisticsUseCase(StatisticsRepository statisticsRepository,
                                   ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statisticsRepository = statisticsRepository;
    }

    public void execute(Subscriber UseCaseSubscriber, Statistics statistics) {
        this.statistics = statistics;
        super.execute(UseCaseSubscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        statisticsRepository.updateStatistics(statistics);
        return Observable.just(null);
    }
}
