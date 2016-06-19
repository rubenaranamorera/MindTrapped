
package com.mindtrapped.interactor;

import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.QuestionStatistics;
import com.mindtrapped.model.QuestionStatus;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.QuestionRepository;
import com.mindtrapped.repository.StatisticsRepository;

import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class AnswerQuestionUseCase extends UseCase {

    private final StatisticsRepository statisticsRepository;
    private final QuestionRepository questionRepository;

    private Question question;
    private Statistics statistics;
    private String userAnswer;

    @Inject
    public AnswerQuestionUseCase(StatisticsRepository statisticsRepository, QuestionRepository questionRepository,
                                 ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statisticsRepository = statisticsRepository;
        this.questionRepository = questionRepository;
    }

    public void execute(Subscriber subscriber, Question question, Statistics statistics, String userAnswer) {
        this.question = question;
        this.statistics = statistics;
        this.userAnswer = userAnswer;
        super.execute(subscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        QuestionStatistics questionStatistics = new QuestionStatistics();

        if (isCorrectAnswer(userAnswer)) {
            updateStatisticsWithCorrectQuestion();
            questionStatistics.setQuestionStatus(QuestionStatus.OK);

            try {
                question = questionRepository.getUnseenQuestion(statistics.getSeenQuestionSet());
            } catch (NoMoreQuestionsFoundException e) {
                questionStatistics.setResetQuestions(true);
            }

        } else {
            updateStatisticsWithMissedQuestion();
            questionStatistics.setQuestionStatus(QuestionStatus.MISSED);
        }

        questionStatistics.setQuestion(question);
        questionStatistics.setStatistics(statistics);

        return Observable.just(questionStatistics);
    }

    private boolean isCorrectAnswer(String userAnswer) {
        return userAnswer.equals(question.getAnswer());
    }

    public void updateStatisticsWithCorrectQuestion() {
        updateStatisticsWithSeenQuestion();

        int questionsInARow = statistics.getCorrectQuestionsInARow() + 1;
        statistics.setCorrectQuestionsInARow(questionsInARow);

        Set<Question> correctQuestionsList = statistics.getCorrectQuestionSet();
        correctQuestionsList.add(question);
        statistics.setCorrectQuestionSet(correctQuestionsList);

        statisticsRepository.updateStatistics(statistics);
    }

    public void updateStatisticsWithMissedQuestion() {
        updateStatisticsWithSeenQuestion();

        statistics.setCorrectQuestionsInARow(0);
        statisticsRepository.updateStatistics(statistics);
    }

    private void updateStatisticsWithSeenQuestion() {
        Set<Question> questionSeenList = statistics.getSeenQuestionSet();
        questionSeenList.add(question);
        statistics.setSeenQuestionSet(questionSeenList);
    }


}
