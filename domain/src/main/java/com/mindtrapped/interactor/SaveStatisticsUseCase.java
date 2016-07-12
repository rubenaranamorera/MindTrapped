
package com.mindtrapped.interactor;

import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.StatisticsRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class SaveStatisticsUseCase extends UseCase {

    private final StatisticsRepository statisticsRepository;

    private Statistics statistics;

    @Inject
    public SaveStatisticsUseCase(StatisticsRepository statisticsRepository,
                                 ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statisticsRepository = statisticsRepository;
    }

    public void execute(Subscriber subscriber,Statistics statistics) {
        this.statistics = statistics;
        super.execute(subscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        statisticsRepository.saveStatistics(statistics);
        return Observable.empty();
    }
}
