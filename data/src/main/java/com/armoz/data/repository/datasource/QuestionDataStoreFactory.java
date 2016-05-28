package com.armoz.data.repository.datasource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link QuestionDataStore}.
 */
@Singleton
public class QuestionDataStoreFactory {

    @Inject
    public QuestionDataStoreFactory() {
    }


    /**
     * Create {@link QuestionDataStore} from a user id.
     */
    public QuestionDataStore create() {
        return new FakeQuestionDataStore();
    }

}
