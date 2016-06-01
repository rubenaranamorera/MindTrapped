
package com.armoz.mindtrapped.presentation.statistics.presenter;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.statistics.activity.StatisticsActivity;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.mindtrapped.interactor.DefaultSubscriber;
import com.mindtrapped.interactor.UseCase;
import com.mindtrapped.model.Statistics;

@PerActivity
public class StatisticsPresenter {

    private StatisticsActivity activity;

    private final UseCase getStatisticsUseCase;

    public StatisticsPresenter(UseCase getStatisticsUseCase) {
        this.getStatisticsUseCase = getStatisticsUseCase;
    }

    public void setActivity(StatisticsActivity activity) {
        this.activity = activity;
    }

    public void loadStatistics() {
        getStatisticsUseCase.execute(new StatisticsSubscriber());
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
        }

        @Override
        public void onNext(Statistics statistics) {
            activity.onStatisticsLoaded(statistics);
        }

    }
}
