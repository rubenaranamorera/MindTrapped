
package com.armoz.mindtrapped.presentation.singlegame.presenter;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.singlegame.activity.SingleGameActivity;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.mindtrapped.interactor.DefaultSubscriber;
import com.mindtrapped.interactor.UseCase;
import com.mindtrapped.model.Question;

@PerActivity
public class SingleGamePresenter {

    private SingleGameActivity activity;

    private final UseCase getQuestionUseCase;

    public SingleGamePresenter(UseCase getQuestionUseCase) {
        this.getQuestionUseCase = getQuestionUseCase;
    }

    public void setActivity(SingleGameActivity activity) {
        this.activity = activity;
    }

    public void loadQuestion() {
        getQuestionUseCase.execute(new QuestionSubscriber());
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
}
