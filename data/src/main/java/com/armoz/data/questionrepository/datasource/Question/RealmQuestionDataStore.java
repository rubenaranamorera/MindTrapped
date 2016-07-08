package com.armoz.data.questionrepository.datasource.Question;

import com.armoz.data.entities.QuestionEntity;
import com.armoz.data.realmbase.RealmDatabase;
import com.mindtrapped.exception.NoMoreQuestionsFoundException;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;

public class RealmQuestionDataStore implements QuestionDataStore {

    private RealmDatabase realmDatabase;

    public RealmQuestionDataStore(RealmDatabase realmDatabase) {
        this.realmDatabase = realmDatabase;
    }

    @Override
    public QuestionEntity getUnseenQuestionEntity(final RealmList<QuestionEntity> questionEntities) throws NoMoreQuestionsFoundException {
        Realm realm = realmDatabase.getRealmInstance();
        RealmQuery<QuestionEntity> query = realm.where(QuestionEntity.class);
        for (QuestionEntity questionEntity : questionEntities) {
            query = query.not().equalTo("id", questionEntity.getId());
        }

        QuestionEntity questionEntity = query.findFirst();
        if (questionEntity == null) {
            throw new NoMoreQuestionsFoundException();
        }
        QuestionEntity questionEntityUnmanaged = realm.copyFromRealm(questionEntity);

        return questionEntityUnmanaged;
    }
}