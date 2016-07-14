package com.armoz.mindtrapped.presentation.statistics.component;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.base.component.ApplicationComponent;
import com.armoz.mindtrapped.presentation.statistics.activity.RankingsActivity;
import com.armoz.mindtrapped.presentation.statistics.module.RankingsModule;
import com.armoz.mindtrapped.presentation.statistics.presenter.RankingsPresenter;
import com.mindtrapped.interactor.LoadRankingsUseCase;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {RankingsModule.class})
public interface RankingsComponent {
    void inject(RankingsActivity rankingsActivity);

    RankingsPresenter rankingsPresenter();

    LoadRankingsUseCase loadRankingsUseCase();
}
