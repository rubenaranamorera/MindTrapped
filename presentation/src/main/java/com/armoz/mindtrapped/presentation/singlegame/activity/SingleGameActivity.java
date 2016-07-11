package com.armoz.mindtrapped.presentation.singlegame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.armoz.mindtrapped.R;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
import com.armoz.mindtrapped.presentation.singlegame.component.DaggerSingleGameComponent;
import com.armoz.mindtrapped.presentation.singlegame.module.SingleGameModule;
import com.armoz.mindtrapped.presentation.singlegame.presenter.SingleGamePresenter;
import com.mindtrapped.model.AnswerEnum;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.Statistics;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleGameActivity extends BaseActivity implements SingleGamePresenter.View{

    @BindView(R.id.single_game_question)
    TextView questionView;

    @BindView(R.id.single_game_answerA_button)
    Button answerAButton;

    @BindView(R.id.single_game_answerB_button)
    Button answerBButton;

    @BindView(R.id.single_game_answerC_button)
    Button answerCButton;

    @BindView(R.id.single_game_answerD_button)
    Button answerDButton;

    @BindView(R.id.single_game_skip_button)
    Button skipButton;

    @BindView(R.id.single_game_correct_questions)
    TextView correctQuestionsView;

    @BindView(R.id.single_game_seen_questions)
    TextView seenQuestionsView;

    @BindView(R.id.single_game_questions_in_a_row)
    TextView correctQuestionsInARowView;

    @BindView(R.id.single_game_progress_bar)
    ProgressBar progressBar;

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
        progressBar.setProgress(60000);
        presenter.loadInitialData();
    }

    @Override
    public void onQuestionStatisticsLoaded(Question question, Statistics statistics) {
        correctQuestionsView.setText(String.valueOf(statistics.getCorrectQuestionSet().size()));
        seenQuestionsView.setText(String.valueOf(statistics.getSeenQuestionSet().size()));
        correctQuestionsInARowView.setText(String.valueOf(statistics.getCorrectQuestionsInARow()));

        questionView.setText(question.getQuestion());
        answerAButton.setText(question.getAnswerA());
        answerBButton.setText(question.getAnswerB());
        answerCButton.setText(question.getAnswerC());
        answerDButton.setText(question.getAnswerD());

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

    @Override
    public void setProgressBarProgress(int progress) {
        progressBar.setProgress(progress);
    }

    private void enableButtons() {
        answerAButton.setEnabled(true);
        answerBButton.setEnabled(true);
        answerCButton.setEnabled(true);
        answerDButton.setEnabled(true);
        skipButton.setEnabled(true);
    }

    private void disableButtons() {
        answerAButton.setEnabled(false);
        answerBButton.setEnabled(false);
        answerCButton.setEnabled(false);
        answerDButton.setEnabled(false);
        skipButton.setEnabled(false);
    }

    @OnClick(R.id.single_game_answerA_button)
    public void checkAnswerA() {
        disableButtons();
        presenter.checkAnswer(AnswerEnum.A);
    }

    @OnClick(R.id.single_game_answerB_button)
    public void checkAnswerB() {
        disableButtons();
        presenter.checkAnswer(AnswerEnum.B);
    }

    @OnClick(R.id.single_game_answerC_button)
    public void checkAnswerC() {
        disableButtons();
        presenter.checkAnswer(AnswerEnum.C);
    }

    @OnClick(R.id.single_game_answerD_button)
    public void checkAnswerD() {
        disableButtons();
        presenter.checkAnswer(AnswerEnum.D);
    }
    @OnClick(R.id.single_game_skip_button)
    public void skipQuestion(){
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
