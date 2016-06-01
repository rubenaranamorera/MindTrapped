package com.armoz.data.questionrepository.datasource;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link QuestionDataStore}.
 */
@Singleton
public class QuestionDataStoreFactory {

    private Context context;
    @Inject
    public QuestionDataStoreFactory(Context context) {
        this.context = context;
    }


    /**
     * Create {@link QuestionDataStore} from a user id.
     */
    public RealmQuestionDataStore create() {
        return new RealmQuestionDataStore(context);
    }

}
