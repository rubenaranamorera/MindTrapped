package com.armoz.mindtrapped.presentation.choosegame.component;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.base.component.ApplicationComponent;
import com.armoz.mindtrapped.presentation.choosegame.activity.ChooseGameActivity;
import com.armoz.mindtrapped.presentation.choosegame.module.ChooseGameModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ChooseGameModule.class})
public interface ChooseGameComponent{
    void inject(ChooseGameActivity chooseGameActivity);
}
