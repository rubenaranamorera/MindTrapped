package com.armoz.mindtrapped.presentation.base.module;

import android.content.Context;

import com.armoz.data.executor.JobExecutor;
import com.armoz.data.repository.QuestionDataRepository;
import com.armoz.mindtrapped.presentation.base.AndroidApplication;
import com.armoz.mindtrapped.presentation.base.UIThread;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.repository.QuestionRepository;

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

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    QuestionRepository provideQuestionRepository(QuestionDataRepository questionDataRepository) {
        return questionDataRepository;
    }
}
