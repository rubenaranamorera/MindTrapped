
package com.mindtrapped.interactor;

import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.QuestionStatistics;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.QuestionRepository;

import java.util.HashSet;

import javax.inject.Inject;

import rx.Observable;

public class LoadSingleGameUseCase extends UseCase {

    private final QuestionRepository questionRepository;

    @Inject
    public LoadSingleGameUseCase(QuestionRepository questionRepository,
                                 ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.questionRepository = questionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        QuestionStatistics questionStatistics = new QuestionStatistics();
        Question question = new Question();
        try {
            question = questionRepository.getUnseenQuestion(new HashSet<Question>());
        } catch (NoMoreQuestionsFoundException e) {
            questionStatistics.setFinishGame(true);
        }

        questionStatistics.setQuestion(question);
        questionStatistics.setStatistics(new Statistics());
        return Observable.just(questionStatistics);
    }

}
