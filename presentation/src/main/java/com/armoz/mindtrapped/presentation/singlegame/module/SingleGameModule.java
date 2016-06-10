package com.armoz.mindtrapped.presentation.singlegame.module;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.singlegame.presenter.SingleGamePresenter;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.interactor.GetUnseenQuestionUseCase;
import com.mindtrapped.interactor.GetStatisticsUseCase;
import com.mindtrapped.interactor.UpdateStatisticsUseCase;
import com.mindtrapped.repository.QuestionRepository;
import com.mindtrapped.repository.StatisticsRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class SingleGameModule {

    public SingleGameModule() {
    }

    @Provides @PerActivity
    SingleGamePresenter singleGamePresenter(GetUnseenQuestionUseCase questionUseCase, GetStatisticsUseCase statisticsUseCase, UpdateStatisticsUseCase updateStatisticsUseCase){
        return new SingleGamePresenter(questionUseCase, statisticsUseCase, updateStatisticsUseCase);
    }

    @Provides @PerActivity
    GetUnseenQuestionUseCase provideGetQuestionUseCase(
            QuestionRepository questionRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetUnseenQuestionUseCase(questionRepository, threadExecutor, postExecutionThread);
    }

    @Provides @PerActivity GetStatisticsUseCase getStatisticsUseCase(
            StatisticsRepository statisticsRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetStatisticsUseCase(statisticsRepository, threadExecutor, postExecutionThread);
    }

    @Provides @PerActivity
    UpdateStatisticsUseCase updateStatisticsUseCase(
            StatisticsRepository statisticsRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new UpdateStatisticsUseCase(statisticsRepository, threadExecutor, postExecutionThread);
    }
}