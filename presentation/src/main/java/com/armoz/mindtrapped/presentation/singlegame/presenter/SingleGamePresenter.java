
package com.armoz.mindtrapped.presentation.singlegame.presenter;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.singlegame.activity.SingleGameActivity;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.mindtrapped.interactor.DefaultSubscriber;
import com.mindtrapped.interactor.GetQuestionUseCase;
import com.mindtrapped.interactor.GetStatisticsUseCase;
import com.mindtrapped.interactor.UpdateStatisticsUseCase;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.Statistics;

import java.util.List;

@PerActivity
public class SingleGamePresenter {

    private SingleGameActivity activity;

    private final GetQuestionUseCase getQuestionUseCase;
    private final GetStatisticsUseCase getStatisticsUseCase;
    private final UpdateStatisticsUseCase updateStatisticsUseCase;

    public SingleGamePresenter(GetQuestionUseCase getQuestionUseCase, GetStatisticsUseCase getStatisticsUseCase, UpdateStatisticsUseCase updateStatisticsUseCase) {
        this.getQuestionUseCase = getQuestionUseCase;
        this.getStatisticsUseCase = getStatisticsUseCase;
        this.updateStatisticsUseCase = updateStatisticsUseCase;
    }

    public void setActivity(SingleGameActivity activity) {
        this.activity = activity;
    }


    public void loadQuestion(List<Question> questionSeenList, List<Question> correctQuestionList) {
        getQuestionUseCase.execute(new QuestionSubscriber());
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
    private final class QuestionSubscriber extends DefaultSubscriber<Question> {

        @Override
        public void onCompleted() {
            //Todo: hide loading
        }

        @Override
        public void onError(Throwable e) {
            //Todo: show error
            /*UserListPresenter.this.hideViewLoading();
            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserListPresenter.this.showViewRetry();*/
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
            //Todo: hide loading
        }

        @Override
        public void onError(Throwable e) {
            //Todo: show error
            /*UserListPresenter.this.hideViewLoading();
            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserListPresenter.this.showViewRetry();*/
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
            //Todo: hide loading
        }

        @Override
        public void onError(Throwable e) {
            //Todo: show error
            /*UserListPresenter.this.hideViewLoading();
            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            UserListPresenter.this.showViewRetry();*/
        }

        @Override
        public void onNext(Void empty) {
        }

    }
}
