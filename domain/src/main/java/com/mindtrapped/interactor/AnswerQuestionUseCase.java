
package com.mindtrapped.interactor;

import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.executor.PostExecutionThread;
import com.mindtrapped.executor.ThreadExecutor;
import com.mindtrapped.model.AnswerEnum;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.QuestionStatistics;
import com.mindtrapped.model.QuestionStatus;
import com.mindtrapped.model.Statistics;
import com.mindtrapped.repository.QuestionRepository;

import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class AnswerQuestionUseCase extends UseCase {

    private final QuestionRepository questionRepository;

    private Question question;
    private Statistics statistics;
    private AnswerEnum userAnswer;

    @Inject
    public AnswerQuestionUseCase(QuestionRepository questionRepository,
                                 ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.questionRepository = questionRepository;
    }

    public void execute(Subscriber subscriber, Question question, Statistics statistics, AnswerEnum userAnswer) {
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
        } else {
            updateStatisticsWithMissedQuestion();
            questionStatistics.setQuestionStatus(QuestionStatus.MISSED);
        }

        try {
            question = questionRepository.getUnseenQuestion(statistics.getSeenQuestionSet());
        } catch (NoMoreQuestionsFoundException e) {
            questionStatistics.setFinishGame(true);
        }

        questionStatistics.setQuestion(question);
        questionStatistics.setStatistics(statistics);

        return Observable.just(questionStatistics);
    }

    private boolean isCorrectAnswer(AnswerEnum userAnswer) {
        return userAnswer.equals(question.getCorrectAnswer());
    }

    public void updateStatisticsWithCorrectQuestion() {
        updateStatisticsWithSeenQuestion();

        int correctQuestionsInARow = statistics.getCorrectQuestionsInARow() + 1;
        statistics.setCorrectQuestionsInARow(correctQuestionsInARow);

        int correctQuestions = statistics.getCorrectQuestions() + 1;
        statistics.setCorrectQuestions(correctQuestions);
    }

    public void updateStatisticsWithMissedQuestion() {
        updateStatisticsWithSeenQuestion();

        statistics.setCorrectQuestionsInARow(0);
    }

    private void updateStatisticsWithSeenQuestion() {
        Set<Question> questionSeenList = statistics.getSeenQuestionSet();
        questionSeenList.add(question);
        statistics.setSeenQuestionSet(questionSeenList);
    }
}
