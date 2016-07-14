package com.armoz.mindtrapped.presentation.singlegame.component;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.base.component.ApplicationComponent;
import com.armoz.mindtrapped.presentation.singlegame.activity.SingleGameActivity;
import com.armoz.mindtrapped.presentation.singlegame.module.SingleGameModule;
import com.armoz.mindtrapped.presentation.singlegame.presenter.SingleGamePresenter;
import com.mindtrapped.interactor.AnswerQuestionUseCase;
import com.mindtrapped.interactor.LoadSingleGameUseCase;
import com.mindtrapped.interactor.SaveStatisticsUseCase;
import com.mindtrapped.interactor.SkipQuestionUseCase;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {SingleGameModule.class})
public interface SingleGameComponent {
    void inject(SingleGameActivity singleGameActivity);

    SingleGamePresenter singleGamePresenter();

    LoadSingleGameUseCase provideLoadSingleGameUseCase();

    AnswerQuestionUseCase provideAnswerQuestionUseCase();

    SkipQuestionUseCase provideSkipQuestionUseCase();

    SaveStatisticsUseCase provideSaveStatisticsUseCase();

}
