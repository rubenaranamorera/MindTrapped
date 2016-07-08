package com.armoz.data.questionrepository.datasource.QuestionContent;

import android.content.Context;

import com.armoz.data.questionrepository.datasource.Question.QuestionDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link QuestionContentDataStore}.
 */
@Singleton
public class QuestionContentDataStoreFactory {

    @Inject
    public QuestionContentDataStoreFactory() {
    }


    /**
     * Create {@link QuestionDataStore} from a user id.
     */
    public InMemoryQuestionContentDataStore create(Context context) {
        return new InMemoryQuestionContentDataStore(context);
    }

}
