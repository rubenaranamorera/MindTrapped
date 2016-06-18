package com.armoz.mindtrapped.presentation.singlegame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.armoz.mindtrapped.R;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
import com.armoz.mindtrapped.presentation.singlegame.component.DaggerSingleGameComponent;
import com.armoz.mindtrapped.presentation.singlegame.module.SingleGameModule;
import com.armoz.mindtrapped.presentation.singlegame.presenter.SingleGamePresenter;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.Statistics;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleGameActivity extends BaseActivity implements SingleGamePresenter.View{

    @BindView(R.id.single_game_question)
    TextView questionView;

    @BindView(R.id.single_game_response)
    TextView responseView;

    @BindView(R.id.single_game_answer_button)
    Button answerButton;

    @BindView(R.id.single_game_correct_questions)
    TextView correctQuestionsView;

    @BindView(R.id.single_game_seen_questions)
    TextView seenQuestionsView;

    @BindView(R.id.single_game_questions_in_a_row)
    TextView correctQuestionsInARowView;

    @Inject
    SingleGamePresenter presenter;

    public static Intent buildIntent(Context context) {
        return new Intent(context, SingleGameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);
        setView(findViewById(R.id.single_game_layout));
        ButterKnife.bind(this);
        initializeInjector();

        presenter.setView(this);
        presenter.loadStatistics();
    }

    public void onStatisticsLoaded(Statistics statistics) {
        presenter.loadUnseenQuestion();
        correctQuestionsView.setText(String.valueOf(statistics.getCorrectQuestionSet().size()));
        seenQuestionsView.setText(String.valueOf(statistics.getSeenQuestionSet().size() - 1));
        correctQuestionsInARowView.setText(String.valueOf(statistics.getCorrectQuestionsInARow()));

        //TODO: show total lives
    }

    public void onQuestionLoaded(Question question) {
        questionView.setText(question.getQuestion());
        seenQuestionsView.setText(String.valueOf(presenter.getStatistics().getSeenQuestionSet().size()));
        correctQuestionsInARowView.setText(String.valueOf(presenter.getStatistics().getCorrectQuestionsInARow()));
        answerButton.setEnabled(true);
    }

    @OnClick(R.id.single_game_answer_button)
    public void checkAnswer() {
        String answer = responseView.getText().toString();
        if (isCorrectAnswer(answer)) {
            presenter.updateStatisticsWithCorrectQuestion();
            presenter.loadUnseenQuestion();
            answerButton.setEnabled(false);
            responseView.setText("");
            correctQuestionsView.setText(String.valueOf(presenter.getStatistics().getCorrectQuestionSet().size()));
            correctQuestionsInARowView.setText(String.valueOf(presenter.getStatistics().getCorrectQuestionsInARow()));
            showConfirmation(getString(R.string.single_game_right_answer));
        } else {
            presenter.updateStatisticsWithMissedQuestion();
            correctQuestionsInARowView.setText("0");
            showError(getString(R.string.single_game_wrong_answer));
        }
    }

    private boolean isCorrectAnswer(String answer) {
        return presenter.checkAnswer(answer);
    }

    private void initializeInjector() {
        DaggerSingleGameComponent.builder()
                .applicationComponent(getApplicationComponent())
                .singleGameModule(new SingleGameModule())
                .build()
                .inject(this);
    }
}
