package com.armoz.data.questionrepository;

import com.armoz.data.entities.QuestionEntity;
import com.armoz.data.entities.mappers.QuestionEntityMapper;
import com.armoz.data.questionrepository.datasource.QuestionDataStore;
import com.armoz.data.questionrepository.datasource.QuestionDataStoreFactory;
import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.model.Question;
import com.mindtrapped.repository.QuestionRepository;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

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
    public Question getUnseenQuestion(Set<Question> questionSeenList) throws NoMoreQuestionsFoundException {
        final QuestionDataStore questionDataStore = this.questionDataStoreFactory.create();
        QuestionEntity questionEntity = questionDataStore.getUnseenQuestionEntity(questionEntityMapper.transformToDataModel(questionSeenList));
        return questionEntityMapper.transformToDomainModel(questionEntity);
    }
}
