package com.armoz.data.repository;

import com.armoz.data.entity.QuestionEntity;
import com.armoz.data.entity.mapper.QuestionEntityMapper;
import com.armoz.data.repository.datasource.QuestionDataStore;
import com.armoz.data.repository.datasource.QuestionDataStoreFactory;
import com.mindtrapped.model.Question;
import com.mindtrapped.repository.QuestionRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

@Singleton
public class QuestionDataRepository implements QuestionRepository {

    private final QuestionDataStoreFactory questionDataStoreFactory;
    private final QuestionEntityMapper questionEntityMapper;

    private QuestionDataStore questionDataStore;

    @Inject
    public QuestionDataRepository(QuestionDataStoreFactory dataStoreFactory,
                                  QuestionEntityMapper questionEntityMapper) {
        this.questionDataStoreFactory = dataStoreFactory;
        this.questionEntityMapper = questionEntityMapper;
    }

    @Override
    public Observable<Boolean> initializeDatabase() {
        questionDataStore = questionDataStoreFactory.create();
        return questionDataStore.initializeDatabase();
    }

    @Override
    public Observable<Question> question() {
        return questionDataStore.questionEntity().map(new Func1<QuestionEntity, Question>() {
            @Override
            public Question call(QuestionEntity questionEntity) {
                return questionEntityMapper.transform(questionEntity);
            }
        });
    }
}
