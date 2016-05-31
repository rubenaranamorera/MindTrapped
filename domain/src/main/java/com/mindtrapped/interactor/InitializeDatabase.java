
package com.mindtrapped.interactor;

import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.repository.QuestionRepository;

import javax.inject.Inject;

import rx.Observable;

public class InitializeDatabase extends UseCase {

    private final QuestionRepository questionRepository;

    @Inject
    public InitializeDatabase(QuestionRepository questionRepository,
                              ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.questionRepository = questionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.questionRepository.initializeDatabase();
    }
}
