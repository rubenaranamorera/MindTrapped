package com.armoz.mindtrapped.presentation.statistics.component;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.base.component.ApplicationComponent;
import com.armoz.mindtrapped.presentation.statistics.activity.StatisticsActivity;
import com.armoz.mindtrapped.presentation.statistics.module.StatisticsModule;
import com.armoz.mindtrapped.presentation.statistics.presenter.StatisticsPresenter;
import com.mindtrapped.interactor.UseCase;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {StatisticsModule.class})
public interface StatisticsComponent {
    void inject(StatisticsActivity statisticsActivity);

    StatisticsPresenter statisticsPresenter();

    UseCase getStatisticsUseCase();
}
