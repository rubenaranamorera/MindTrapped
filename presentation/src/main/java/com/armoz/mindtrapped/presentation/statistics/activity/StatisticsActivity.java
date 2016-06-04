package com.armoz.mindtrapped.presentation.statistics.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.armoz.mindtrapped.R;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
import com.armoz.mindtrapped.presentation.statistics.component.DaggerStatisticsComponent;
import com.armoz.mindtrapped.presentation.statistics.module.StatisticsModule;
import com.armoz.mindtrapped.presentation.statistics.presenter.StatisticsPresenter;
import com.mindtrapped.model.Statistics;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends BaseActivity {

    @Inject
    StatisticsPresenter presenter;

    @BindView(R.id.statistics_correct_questions_size)
    TextView correctQuestions;

    @BindView(R.id.statistics_questions_in_a_row)
    TextView correctQuestionsInARow;

    @BindView(R.id.statistics_questions_seen_size)
    TextView seenQuestions;

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
                .statisticsModule(new StatisticsModule())
                .build()
                .inject(this);
    }

    public void onStatisticsLoaded(Statistics statistics) {
        int seenQuestionsNum = statistics.getQuestionSeenList().size();
        seenQuestions.setText(Integer.toString(seenQuestionsNum));

        int correctQuestionsNum = statistics.getQuestionSeenList().size();
        correctQuestions.setText(Integer.toString(correctQuestionsNum));

        int correctQuestionsInARowNum =statistics.getCorrectQuestionsInARow();
        correctQuestionsInARow.setText(Integer.toString(correctQuestionsInARowNum));
    }
}
