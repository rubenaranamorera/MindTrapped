package com.armoz.mindtrapped.presentation.base.module;

import android.content.Context;

import com.armoz.data.executor.JobExecutor;
import com.armoz.data.questionrepository.QuestionDataRepository;
import com.armoz.data.realmbase.RealmDatabase;
import com.armoz.data.statisticsrepository.StatisticsDataRepository;
import com.armoz.mindtrapped.presentation.base.AndroidApplication;
import com.armoz.mindtrapped.presentation.base.UIThread;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.repository.QuestionRepository;
import com.mindtrapped.repository.StatisticsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    QuestionRepository provideQuestionRepository(QuestionDataRepository questionDataRepository) {
        return questionDataRepository;
    }

    @Provides
    @Singleton
    StatisticsRepository provideStatisticsRepository(StatisticsDataRepository statisticsDataRepository) {
        return statisticsDataRepository;
    }

    @Provides
    @Singleton
    RealmDatabase provideRealmDatabase(Context context) {
        return new RealmDatabase(context);
    }
}
