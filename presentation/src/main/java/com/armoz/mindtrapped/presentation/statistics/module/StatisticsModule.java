package com.armoz.mindtrapped.presentation.statistics.module;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.statistics.presenter.StatisticsPresenter;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.interactor.SaveStatisticsUseCase;
import com.mindtrapped.interactor.UseCase;
import com.mindtrapped.repository.StatisticsRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class StatisticsModule {

    public StatisticsModule() {
    }

    @Provides @PerActivity
    StatisticsPresenter statisticsPresenter(UseCase useCase){
        return new StatisticsPresenter(useCase);
    }

    @Provides @PerActivity UseCase getStatisticsUseCase(
            StatisticsRepository statisticsRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new SaveStatisticsUseCase(statisticsRepository, threadExecutor, postExecutionThread);
    }
}