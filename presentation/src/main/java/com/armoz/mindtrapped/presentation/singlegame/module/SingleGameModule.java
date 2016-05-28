package com.armoz.mindtrapped.presentation.singlegame.module;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.singlegame.presenter.SingleGamePresenter;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.interactor.GetQuestion;
import com.mindtrapped.interactor.UseCase;
import com.mindtrapped.repository.QuestionRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class SingleGameModule {

    public SingleGameModule() {
    }

    @Provides @PerActivity
    SingleGamePresenter singleGamePresenter(UseCase useCase){
        return new SingleGamePresenter(useCase);
    }

    @Provides @PerActivity UseCase provideGetQuestionUseCase(
            QuestionRepository questionRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetQuestion(questionRepository, threadExecutor, postExecutionThread);
    }
}