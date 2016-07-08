package com.armoz.data.questionrepository;

import android.content.Context;

import com.armoz.data.entities.QuestionContentEntity;
import com.armoz.data.entities.QuestionEntity;
import com.armoz.data.entities.mappers.QuestionEntityMapper;
import com.armoz.data.questionrepository.datasource.Question.QuestionDataStore;
import com.armoz.data.questionrepository.datasource.Question.QuestionDataStoreFactory;
import com.armoz.data.questionrepository.datasource.QuestionContent.QuestionContentDataStore;
import com.armoz.data.questionrepository.datasource.QuestionContent.QuestionContentDataStoreFactory;
import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.model.Question;
import com.mindtrapped.repository.QuestionRepository;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class QuestionDataRepository implements QuestionRepository {

    private Context context;

    private final QuestionDataStoreFactory questionDataStoreFactory;
    private final QuestionContentDataStoreFactory questionContentDataStoreFactory;

    private final QuestionEntityMapper questionEntityMapper;

    @Inject
    public QuestionDataRepository(Context context,
                                  QuestionDataStoreFactory questionDataStoreFactory,
                                  QuestionContentDataStoreFactory questionContentDataStoreFactory,
                                  QuestionEntityMapper questionEntityMapper) {
        this.context = context;
        this.questionDataStoreFactory = questionDataStoreFactory;
        this.questionContentDataStoreFactory = questionContentDataStoreFactory;
        this.questionEntityMapper = questionEntityMapper;
    }

    @Override
    public Question getUnseenQuestion(Set<Question> questionSeenList) throws NoMoreQuestionsFoundException {
        final QuestionDataStore questionDataStore = this.questionDataStoreFactory.create();
        final QuestionContentDataStore questionContentDataStore = this.questionContentDataStoreFactory.create(context);

        QuestionEntity questionEntity = questionDataStore.getUnseenQuestionEntity(questionEntityMapper.transformToDataModel(questionSeenList));
        QuestionContentEntity questionContentEntity = questionContentDataStore.getQuestionContent(questionEntity.getId());

        return questionEntityMapper.transformToDomainModel(questionEntity, questionContentEntity);
    }
}
