package com.armoz.data.questionrepository.datasource.Question;

import com.armoz.data.realmbase.RealmDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link QuestionDataStore}.
 */
@Singleton
public class QuestionDataStoreFactory {

    private RealmDatabase realmDatabase;

    @Inject
    public QuestionDataStoreFactory(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;
    }


    /**
     * Create {@link QuestionDataStore} from a user id.
     */
    public RealmQuestionDataStore create() {
        return new RealmQuestionDataStore(realmDatabase);
    }

}
