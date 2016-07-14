package com.armoz.mindtrapped.presentation.statistics.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.armoz.mindtrapped.R;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
import com.armoz.mindtrapped.presentation.statistics.adapter.RankingsAdapter;
import com.armoz.mindtrapped.presentation.statistics.component.DaggerRankingsComponent;
import com.armoz.mindtrapped.presentation.statistics.module.RankingsModule;
import com.armoz.mindtrapped.presentation.statistics.presenter.RankingsPresenter;
import com.mindtrapped.model.Ranking;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankingsActivity extends BaseActivity {

    @Inject
    RankingsPresenter presenter;

    @BindView(R.id.rankings_total_correct_list)
    ListView totalStatisticsList;

    public static Intent buildIntent(Context context) {
        return new Intent(context, RankingsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        ButterKnife.bind(this);
        initializeInjector();
        presenter.setActivity(this);
        presenter.loadRankings();
    }

    private void initializeInjector() {
        DaggerRankingsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .rankingsModule(new RankingsModule())
                .build()
                .inject(this);
    }

    public void onRankingLoaded(Ranking ranking) {
        RankingsAdapter rankingsAdapter =
                new RankingsAdapter(this, R.layout.rankings_list_item, ranking.getTopTotalCorrectAnswersStatistics());
        totalStatisticsList.setAdapter(rankingsAdapter);
    }
}
