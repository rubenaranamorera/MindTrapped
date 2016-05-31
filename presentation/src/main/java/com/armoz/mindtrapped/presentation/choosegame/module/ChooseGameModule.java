package com.armoz.mindtrapped.presentation.choosegame.module;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.choosegame.presenter.ChooseGamePresenter;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.interactor.InitializeDatabase;
import com.mindtrapped.interactor.UseCase;
import com.mindtrapped.repository.QuestionRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ChooseGameModule {

    public ChooseGameModule() {
    }

    @Provides @PerActivity
    ChooseGamePresenter chooseGamePresenter(UseCase useCase){
        return new ChooseGamePresenter(useCase);
    }

    @Provides @PerActivity UseCase provideInitializeDatabaseUseCase(
            QuestionRepository questionRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new InitializeDatabase(questionRepository, threadExecutor, postExecutionThread);
    }
}