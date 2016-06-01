
package com.mindtrapped.interactor;

import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.repository.StatisticsRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetStatisticsUseCase extends UseCase {

    private final StatisticsRepository statisticsRepository;

    @Inject
    public GetStatisticsUseCase(StatisticsRepository statisticsRepository,
                                ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.statisticsRepository.getStatistics();
    }
}
