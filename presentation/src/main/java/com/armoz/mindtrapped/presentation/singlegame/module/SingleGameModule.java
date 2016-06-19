package com.armoz.mindtrapped.presentation.singlegame.module;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.singlegame.presenter.SingleGamePresenter;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.interactor.AnswerQuestionUseCase;
import com.mindtrapped.interactor.LoadSingleGameUseCase;
import com.mindtrapped.interactor.ResetQuestionStatisticsUseCase;
import com.mindtrapped.interactor.SkipQuestionUseCase;
import com.mindtrapped.repository.QuestionRepository;
import com.mindtrapped.repository.StatisticsRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class SingleGameModule {

    public SingleGameModule() {
    }

    @Provides @PerActivity
    SingleGamePresenter singleGamePresenter(
            LoadSingleGameUseCase loadSingleGameUseCase,
            AnswerQuestionUseCase answerQuestionUseCase,
            SkipQuestionUseCase skipQuestionUseCase,
            ResetQuestionStatisticsUseCase resetQuestionStatisticsUseCase){
        return new SingleGamePresenter(loadSingleGameUseCase, answerQuestionUseCase, skipQuestionUseCase, resetQuestionStatisticsUseCase);
    }

    @Provides @PerActivity
    LoadSingleGameUseCase loadSingleGameUseCase(
            QuestionRepository questionRepository,
            StatisticsRepository statisticsRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new LoadSingleGameUseCase(statisticsRepository, questionRepository, threadExecutor, postExecutionThread);
    }

    @Provides @PerActivity
    AnswerQuestionUseCase answerQuestionUseCase(
            QuestionRepository questionRepository,
            StatisticsRepository statisticsRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new AnswerQuestionUseCase(statisticsRepository, questionRepository, threadExecutor, postExecutionThread);
    }

    @Provides @PerActivity
    SkipQuestionUseCase skipQuestionUseCase(
            QuestionRepository questionRepository,
            StatisticsRepository statisticsRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new SkipQuestionUseCase(statisticsRepository, questionRepository, threadExecutor, postExecutionThread);
    }

    @Provides @PerActivity
    ResetQuestionStatisticsUseCase resetQuestionStatisticsUseCase(
            QuestionRepository questionRepository,
            StatisticsRepository statisticsRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new ResetQuestionStatisticsUseCase(statisticsRepository, questionRepository, threadExecutor, postExecutionThread);
    }
}