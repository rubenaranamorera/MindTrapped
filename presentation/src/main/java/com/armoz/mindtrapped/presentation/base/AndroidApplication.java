package com.armoz.mindtrapped.presentation.base;

import android.app.Application;

import com.armoz.mindtrapped.presentation.base.component.ApplicationComponent;
import com.armoz.mindtrapped.presentation.base.component.DaggerApplicationComponent;
import com.armoz.mindtrapped.presentation.base.module.ApplicationModule;

public class AndroidApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    this.initializeInjector();
  }

  private void initializeInjector() {
    this.applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }
}
