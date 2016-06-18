
package com.armoz.mindtrapped.presentation.singlegame.presenter;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.interactor.DefaultSubscriber;
import com.mindtrapped.interactor.GetStatisticsUseCase;
import com.mindtrapped.interactor.GetUnseenQuestionUseCase;
import com.mindtrapped.interactor.UpdateStatisticsUseCase;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.Statistics;

import java.util.HashSet;
import java.util.Set;

@PerActivity
public class SingleGamePresenter {

    private View view;

    private final GetUnseenQuestionUseCase getUnseenQuestionUseCase;
    private final GetStatisticsUseCase getStatisticsUseCase;
    private final UpdateStatisticsUseCase updateStatisticsUseCase;

    private Question question;
    private Statistics statistics;

    public SingleGamePresenter(GetUnseenQuestionUseCase getUnseenQuestionUseCase, GetStatisticsUseCase getStatisticsUseCase, UpdateStatisticsUseCase updateStatisticsUseCase) {
        this.getUnseenQuestionUseCase = getUnseenQuestionUseCase;
        this.getStatisticsUseCase = getStatisticsUseCase;
        this.updateStatisticsUseCase = updateStatisticsUseCase;
    }

    public void setView(View view) {
        this.view = view;
    }


    public void loadUnseenQuestion() {
        getUnseenQuestionUseCase.execute(new UnseenQuestionSubscriber(), statistics.getSeenQuestionSet());
    }

    public void loadStatistics() {
        getStatisticsUseCase.execute(new StatisticsSubscriber());
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equals(question.getAnswer());
    }

    public void updateStatistics(Statistics statistics) {
        updateStatisticsUseCase.execute(new UpdateStatisticsSubscriber(), statistics);
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void updateStatisticsWithSeenQuestion() {
        Set<Question> questionSeenList = statistics.getSeenQuestionSet();
        questionSeenList.add(question);
        statistics.setSeenQuestionSet(questionSeenList);
    }

    public void updateStatisticsWithMissedQuestion() {
        updateStatisticsWithSeenQuestion();
        statistics.setCorrectQuestionsInARow(0);
        updateStatistics(statistics);
    }

    public void updateStatisticsWithCorrectQuestion() {
        updateStatisticsWithSeenQuestion();
        int questionsInARow = statistics.getCorrectQuestionsInARow() + 1;
        statistics.setCorrectQuestionsInARow(questionsInARow);

        Set<Question> correctQuestionsList = statistics.getCorrectQuestionSet();
        correctQuestionsList.add(question);
        statistics.setCorrectQuestionSet(correctQuestionsList);
        updateStatistics(statistics);
    }


    @RxLogSubscriber
    private final class UnseenQuestionSubscriber extends DefaultSubscriber<Question> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            view.showError(e.getMessage());

            if (e instanceof NoMoreQuestionsFoundException){
                restartQuestionStatistics();
            }
        }

        @Override
        public void onNext(Question question) {
            setQuestion(question);
            view.onQuestionLoaded(question);
        }

    }

    private void restartQuestionStatistics() {
        statistics.setCorrectQuestionSet(new HashSet());
        statistics.setSeenQuestionSet(new HashSet());
        updateStatistics(statistics);
        view.onStatisticsLoaded(statistics);
    }

    @RxLogSubscriber
    private final class StatisticsSubscriber extends DefaultSubscriber<Statistics> {

        @Override
        public void onError(Throwable e) {
            view.showError(e.getMessage());
        }

        @Override
        public void onNext(Statistics statistics) {
            setStatistics(statistics);
            view.onStatisticsLoaded(statistics);
        }

    }

    @RxLogSubscriber
    private final class UpdateStatisticsSubscriber extends DefaultSubscriber<Void> {

        @Override
        public void onError(Throwable e) {
            view.showError(e.getMessage());
        }


    }

    public interface View {
        void showError(String message);

        void onStatisticsLoaded(Statistics statistics);

        void onQuestionLoaded(Question question);
    }
}
