package com.armoz.mindtrapped.presentation.singlegame.module;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.singlegame.presenter.SingleGamePresenter;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.interactor.AnswerQuestionUseCase;
import com.mindtrapped.interactor.LoadSingleGameUseCase;
import com.mindtrapped.interactor.SaveStatisticsUseCase;
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
            SkipQuestionUseCase skipQuestionUseCase){
        return new SingleGamePresenter(loadSingleGameUseCase, answerQuestionUseCase, skipQuestionUseCase);
    }

    @Provides @PerActivity
    LoadSingleGameUseCase loadSingleGameUseCase(
            QuestionRepository questionRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new LoadSingleGameUseCase(questionRepository, threadExecutor, postExecutionThread);
    }

    @Provides @PerActivity
    AnswerQuestionUseCase answerQuestionUseCase(
            QuestionRepository questionRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new AnswerQuestionUseCase(questionRepository, threadExecutor, postExecutionThread);
    }

    @Provides @PerActivity
    SkipQuestionUseCase skipQuestionUseCase(
            QuestionRepository questionRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new SkipQuestionUseCase(questionRepository, threadExecutor, postExecutionThread);
    }

    @Provides @PerActivity
    SaveStatisticsUseCase saveStatisticsUseCase(
            StatisticsRepository statisticsRepository,
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new SaveStatisticsUseCase(statisticsRepository, threadExecutor, postExecutionThread);
    }
}