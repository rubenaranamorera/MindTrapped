
package com.armoz.mindtrapped.presentation.singlegame.presenter;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.singlegame.activity.SingleGameActivity;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.interactor.DefaultSubscriber;
import com.mindtrapped.interactor.GetUnseenQuestionUseCase;
import com.mindtrapped.interactor.GetStatisticsUseCase;
import com.mindtrapped.interactor.UpdateStatisticsUseCase;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.Statistics;

import java.util.List;

@PerActivity
public class SingleGamePresenter {

    private SingleGameActivity activity;

    private final GetUnseenQuestionUseCase getUnseenQuestionUseCase;
    private final GetStatisticsUseCase getStatisticsUseCase;
    private final UpdateStatisticsUseCase updateStatisticsUseCase;

    public SingleGamePresenter(GetUnseenQuestionUseCase getUnseenQuestionUseCase, GetStatisticsUseCase getStatisticsUseCase, UpdateStatisticsUseCase updateStatisticsUseCase) {
        this.getUnseenQuestionUseCase = getUnseenQuestionUseCase;
        this.getStatisticsUseCase = getStatisticsUseCase;
        this.updateStatisticsUseCase = updateStatisticsUseCase;
    }

    public void setActivity(SingleGameActivity activity) {
        this.activity = activity;
    }


    public void loadUnseenQuestion(List<Question> questionSeenList) {
        getUnseenQuestionUseCase.execute(new UnseenQuestionSubscriber(), questionSeenList);
    }

    public void loadStatistics() {
        getStatisticsUseCase.execute(new StatisticsSubscriber());
    }

    public boolean checkAnswer(String userAnswer, String correctAnwser) {
        return userAnswer.equals(correctAnwser);
    }

    public void updateStatistics(Statistics statistics) {
        updateStatisticsUseCase.execute(new UpdateStatisticsSubscriber(), statistics);
    }


    @RxLogSubscriber
    private final class UnseenQuestionSubscriber extends DefaultSubscriber<Question> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            activity.showError(e.getMessage());

            if (e.getClass().equals(NoMoreQuestionsFoundException.class)){
                activity.restartQuestionStatistics();
            }
        }

        @Override
        public void onNext(Question question) {
            activity.onQuestionLoaded(question);
        }

    }

    @RxLogSubscriber
    private final class StatisticsSubscriber extends DefaultSubscriber<Statistics> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            activity.showError(e.getMessage());
        }

        @Override
        public void onNext(Statistics statistics) {
            activity.onStatisticsLoaded(statistics);
        }

    }

    @RxLogSubscriber
    private final class UpdateStatisticsSubscriber extends DefaultSubscriber<Void> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            activity.showError(e.getMessage());
        }

        @Override
        public void onNext(Void empty) {
        }

    }
}
