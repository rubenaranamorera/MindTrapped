package com.armoz.mindtrapped.presentation.base.component;

import android.content.Context;

import com.armoz.data.realmbase.RealmDatabase;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
import com.armoz.mindtrapped.presentation.base.module.ApplicationModule;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.repository.QuestionRepository;
import com.mindtrapped.repository.StatisticsRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    QuestionRepository questionRepository();

    StatisticsRepository statisticsRepository();

    RealmDatabase realmDatabase();
}
