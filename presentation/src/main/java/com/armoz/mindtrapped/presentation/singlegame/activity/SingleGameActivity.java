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

    @BindView(R.id.single_game_skip_button)
    Button skipButton;

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
        presenter.loadInitialData();
    }

    @Override
    public void onQuestionStatisticsLoaded(Question question, Statistics statistics) {
        correctQuestionsView.setText(String.valueOf(statistics.getCorrectQuestionSet().size()));
        seenQuestionsView.setText(String.valueOf(statistics.getSeenQuestionSet().size()));
        correctQuestionsInARowView.setText(String.valueOf(statistics.getCorrectQuestionsInARow()));

        questionView.setText(question.getQuestion());

        enableButtons();
    }

    @Override
    public void showOk() {
        showConfirmation(getString(R.string.single_game_right_answer));
    }

    @Override
    public void showMiss() {
        showError(getString(R.string.single_game_wrong_answer));
    }

    @Override
    public void showSkip() {
        showAlert(getString(R.string.single_game_skip_question));
    }

    @Override
    public void showReset() {
        showAlert(getString(R.string.single_game_question_statistics_reset));
    }

    private void enableButtons() {
        answerButton.setEnabled(true);
        skipButton.setEnabled(true);
    }

    private void disableButtons() {
        answerButton.setEnabled(false);
        skipButton.setEnabled(false);
    }

    @OnClick(R.id.single_game_answer_button)
    public void checkAnswer() {
        String answer = responseView.getText().toString();
        disableButtons();
        presenter.checkAnswer(answer);
    }

    @OnClick(R.id.single_game_skip_button)
    public void skipQuestion(){
        responseView.setText("");
        disableButtons();
        presenter.skipQuestion();
    }

    private void initializeInjector() {
        DaggerSingleGameComponent.builder()
                .applicationComponent(getApplicationComponent())
                .singleGameModule(new SingleGameModule())
                .build()
                .inject(this);
    }
}
