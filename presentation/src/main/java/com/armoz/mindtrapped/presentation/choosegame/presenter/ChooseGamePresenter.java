
package com.armoz.mindtrapped.presentation.choosegame.presenter;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.choosegame.activity.ChooseGameActivity;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.mindtrapped.interactor.DefaultSubscriber;
import com.mindtrapped.interactor.UseCase;

@PerActivity
public class ChooseGamePresenter {

    private ChooseGameActivity activity;

    private final UseCase initializeDatabaseUseCase;

    public ChooseGamePresenter(UseCase initializeDatabaseUseCase) {
        this.initializeDatabaseUseCase = initializeDatabaseUseCase;
    }

    public void setActivity(ChooseGameActivity activity) {
        this.activity = activity;
    }

    public void initializeDatabase() {
        initializeDatabaseUseCase.execute(new InitializationSubscriber());
    }


    @RxLogSubscriber
    private final class InitializationSubscriber extends DefaultSubscriber<Boolean> {

        @Override
        public void onCompleted() {
            //Todo: hide loading
        }

        @Override
        public void onError(Throwable e) {
            //Todo: show error
        }

        @Override
        public void onNext(Boolean initialized) {
            activity.onDatabaseInitialized();
        }

    }
}
