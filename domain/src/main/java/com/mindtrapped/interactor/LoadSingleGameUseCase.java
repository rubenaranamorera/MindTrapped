
package com.mindtrapped.interactor;

import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.QuestionStatistics;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.QuestionRepository;
import com.mindtrapped.repository.StatisticsRepository;

import javax.inject.Inject;

import rx.Observable;

public class LoadSingleGameUseCase extends UseCase {

    private final StatisticsRepository statisticsRepository;
    private final QuestionRepository questionRepository;

    @Inject
    public LoadSingleGameUseCase(StatisticsRepository statisticsRepository, QuestionRepository questionRepository,
                                 ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statisticsRepository = statisticsRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
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
        return Observable.just(questionStatistics);
    }

}
