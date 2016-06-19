
package com.mindtrapped.interactor;

import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.QuestionStatistics;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.QuestionRepository;
import com.mindtrapped.repository.StatisticsRepository;

import java.util.HashSet;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class ResetQuestionStatisticsUseCase extends UseCase {

    private final StatisticsRepository statisticsRepository;
    private final QuestionRepository questionRepository;

    private Statistics statistics;

    @Inject
    public ResetQuestionStatisticsUseCase(StatisticsRepository statisticsRepository, QuestionRepository questionRepository,
                                          ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statisticsRepository = statisticsRepository;
        this.questionRepository = questionRepository;
    }

    public void execute(Subscriber subscriber, Statistics statistics) {
        this.statistics = statistics;
        super.execute(subscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        resetStatistics();
        return Observable.just(createQuestionStatistics());
    }

    private void resetStatistics() {
        statistics.setCorrectQuestionSet(new HashSet());
        statistics.setSeenQuestionSet(new HashSet());
        statisticsRepository.updateStatistics(statistics);
    }

    private QuestionStatistics createQuestionStatistics() {
        QuestionStatistics questionStatistics = new QuestionStatistics();
        Question question = new Question();
        Statistics statistics = statisticsRepository.getStatistics();
        try {
            question = questionRepository.getUnseenQuestion(statistics.getSeenQuestionSet());
        } catch (NoMoreQuestionsFoundException e) {
            questionStatistics.setResetQuestions(true);
        }

        questionStatistics.setQuestion(question);
        questionStatistics.setStatistics(statistics);
        return questionStatistics;
    }
}
