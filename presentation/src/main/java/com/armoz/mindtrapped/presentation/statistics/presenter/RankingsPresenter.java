
package com.armoz.mindtrapped.presentation.statistics.presenter;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.armoz.mindtrapped.presentation.statistics.activity.RankingsActivity;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.mindtrapped.interactor.DefaultSubscriber;
import com.mindtrapped.interactor.LoadRankingsUseCase;
import com.mindtrapped.model.Ranking;

@PerActivity
public class RankingsPresenter {

    private RankingsActivity activity;

    private final LoadRankingsUseCase getRankingsUseCase;

    public RankingsPresenter(LoadRankingsUseCase getRankingsUseCase) {
        this.getRankingsUseCase = getRankingsUseCase;
    }

    public void setActivity(RankingsActivity activity) {
        this.activity = activity;
    }

    public void loadRankings() {
        getRankingsUseCase.execute(new RankingsSubscriber());
    }


    @RxLogSubscriber
    private final class RankingsSubscriber extends DefaultSubscriber<Ranking> {

        @Override
        public void onCompleted() {
            //Todo: hide loading
        }

        @Override
        public void onError(Throwable e) {
            //Todo: show error
        }

        @Override
        public void onNext(Ranking ranking) {
            activity.onRankingLoaded(ranking);
        }

    }
}
