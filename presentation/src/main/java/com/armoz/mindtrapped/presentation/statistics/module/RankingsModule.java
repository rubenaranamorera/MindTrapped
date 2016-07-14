package com.armoz.mindtrapped.presentation.statistics.module;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.statistics.presenter.RankingsPresenter;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.interactor.LoadRankingsUseCase;
import com.mindtrapped.repository.StatisticsRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RankingsModule {

    public RankingsModule() {
    }

    @Provides @PerActivity
    RankingsPresenter statisticsPresenter(LoadRankingsUseCase loadRankingsUseCase){
        return new RankingsPresenter(loadRankingsUseCase);
    }

    @Provides @PerActivity LoadRankingsUseCase loadRankingsUseCase(
            StatisticsRepository statisticsRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new LoadRankingsUseCase(statisticsRepository, threadExecutor, postExecutionThread);
    }
}