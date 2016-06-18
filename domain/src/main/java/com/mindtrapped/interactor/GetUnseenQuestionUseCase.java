
package com.mindtrapped.interactor;

import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.Question;
import com.mindtrapped.repository.QuestionRepository;

import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class GetUnseenQuestionUseCase extends UseCase {

    private final QuestionRepository questionRepository;
    private Set<Question> questionSeenList;

    @Inject
    public GetUnseenQuestionUseCase(QuestionRepository questionRepository,
                                    ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.questionRepository = questionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        try {
            return Observable.just(questionRepository.getUnseenQuestion(questionSeenList));
        } catch (Exception e) {
            return Observable.error(e);
        }
    }

    public void execute(Subscriber UseCaseSubscriber, Set<Question> questionSeenList) {
        this.questionSeenList = questionSeenList;
        super.execute(UseCaseSubscriber);
    }
}
