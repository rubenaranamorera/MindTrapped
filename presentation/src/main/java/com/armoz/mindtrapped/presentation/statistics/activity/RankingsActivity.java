package com.armoz.mindtrapped.presentation.statistics.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.armoz.mindtrapped.R;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
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

    @BindView(R.id.rankings_correct_questions_size)
    TextView correctQuestions;

    @BindView(R.id.rankings_questions_in_a_row)
    TextView correctQuestionsInARow;

    @BindView(R.id.rankings_questions_seen_size)
    TextView seenQuestions;

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
       /* int seenQuestionsNum = statistics.getSeenQuestionSet().size();
        seenQuestions.setText(Integer.toString(seenQuestionsNum));

        int correctQuestionsNum = statistics.getSeenQuestionSet().size();
        correctQuestions.setText(Integer.toString(correctQuestionsNum));

        int correctQuestionsInARowNum =statistics.getCorrectQuestionsInARow();
        correctQuestionsInARow.setText(Integer.toString(correctQuestionsInARowNum));*/
    }
}
