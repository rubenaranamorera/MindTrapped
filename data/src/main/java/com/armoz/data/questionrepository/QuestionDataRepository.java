package com.armoz.data.questionrepository;

import com.armoz.data.entities.QuestionEntity;
import com.armoz.data.entities.mappers.QuestionEntityMapper;
import com.armoz.data.questionrepository.datasource.QuestionDataStore;
import com.armoz.data.questionrepository.datasource.QuestionDataStoreFactory;
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

    @Inject
    public QuestionDataRepository(QuestionDataStoreFactory dataStoreFactory,
                                  QuestionEntityMapper questionEntityMapper) {
        this.questionDataStoreFactory = dataStoreFactory;
        this.questionEntityMapper = questionEntityMapper;
    }

    @Override
    public Observable<Question> question() {
        final QuestionDataStore questionDataStore = this.questionDataStoreFactory.create();
        return questionDataStore.questionEntity().map(new Func1<QuestionEntity, Question>() {
            @Override
            public Question call(QuestionEntity questionEntity) {
                return questionEntityMapper.transform(questionEntity);
            }
        });
    }
}
