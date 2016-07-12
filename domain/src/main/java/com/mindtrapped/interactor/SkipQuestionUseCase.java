
package com.mindtrapped.interactor;

import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.QuestionStatistics;
import com.mindtrapped.model.QuestionStatus;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.QuestionRepository;

import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class SkipQuestionUseCase extends UseCase {

    private final QuestionRepository questionRepository;

    private Question question;
    private Statistics statistics;

    @Inject
    public SkipQuestionUseCase(QuestionRepository questionRepository,
                               ThreadExecutor threadExecutor,
                               PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.questionRepository = questionRepository;
    }

    public void execute(Subscriber subscriber, Question question, Statistics statistics) {
        this.question = question;
        this.statistics = statistics;
        super.execute(subscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        updateStatisticsWithSeenQuestion();
        QuestionStatistics questionStatistics = new QuestionStatistics();
        questionStatistics.setQuestionStatus(QuestionStatus.SKIPPED);

        try {
            question = questionRepository.getUnseenQuestion(statistics.getSeenQuestionSet());
        } catch (NoMoreQuestionsFoundException e) {
            questionStatistics.setFinishGame(true);
        }

        questionStatistics.setQuestion(question);
        questionStatistics.setStatistics(statistics);

        return Observable.just(questionStatistics);
    }

    private void updateStatisticsWithSeenQuestion() {
        Set<Question> questionSeenList = statistics.getSeenQuestionSet();
        questionSeenList.add(question);
        statistics.setSeenQuestionSet(questionSeenList);
    }
}
