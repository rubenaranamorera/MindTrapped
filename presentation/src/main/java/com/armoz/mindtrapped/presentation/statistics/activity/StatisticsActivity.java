package com.armoz.mindtrapped.presentation.statistics.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.armoz.mindtrapped.R;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
import com.armoz.mindtrapped.presentation.choosegame.module.ChooseGameModule;
import com.armoz.mindtrapped.presentation.singlegame.activity.SingleGameActivity;
import com.armoz.mindtrapped.presentation.statistics.presenter.StatisticsPresenter;
import com.mindtrapped.model.Statistics;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class StatisticsActivity extends BaseActivity {

    @Inject
    StatisticsPresenter presenter;

    public static Intent buildIntent(Context context) {
        return new Intent(context, StatisticsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadistics);
        ButterKnife.bind(this);
        initializeInjector();
        presenter.setActivity(this);
        presenter.loadStatistics();
    }

    private void initializeInjector() {
        DaggerStatisticsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .chooseGameModule(new ChooseGameModule())
                .build()
                .inject(this);
    }

    public void onStatisticsLoaded(Statistics statistics) {

    }
}
