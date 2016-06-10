package com.armoz.mindtrapped.presentation.singlegame.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.armoz.mindtrapped.R;
import com.armoz.mindtrapped.presentation.base.activity.BaseActivity;
import com.armoz.mindtrapped.presentation.singlegame.component.DaggerSingleGameComponent;
import com.armoz.mindtrapped.presentation.singlegame.module.SingleGameModule;
import com.armoz.mindtrapped.presentation.singlegame.presenter.SingleGamePresenter;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.Statistics;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleGameActivity extends BaseActivity {

    @BindView(R.id.single_game_question)
    TextView questionView;

    @BindView(R.id.single_game_response)
    TextView responseView;

    @BindView(R.id.single_game_answer_button)
    Button answerButton;

    @BindView(R.id.single_game_layout)
    View view;

    @Inject
    SingleGamePresenter presenter;

    private Question question;
    private Statistics statistics;


    public static Intent buildIntent(Context context) {
        return new Intent(context, SingleGameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);
        ButterKnife.bind(this);
        initializeInjector();

        presenter.setActivity(this);
        presenter.loadStatistics();
    }

    public void onStatisticsLoaded(Statistics statistics) {
        this.statistics = statistics;
        presenter.loadUnseenQuestion(statistics.getQuestionSeenList());

        //TODO:  show good questions / total questions seen

        //TODO: show questions in a row

        //TODO: show total lives
    }

    public void onQuestionLoaded(Question question) {
        this.question = question;
        questionView.setText(question.getQuestion());
        updateStatisticsWithSeenQuestion();
        answerButton.setEnabled(true);
    }

    private void updateStatisticsWithSeenQuestion() {
        List<Question> questionSeenList =  statistics.getQuestionSeenList();
        questionSeenList.add(question);
        statistics.setQuestionSeenList(questionSeenList);
        presenter.updateStatistics(statistics);
    }

    @OnClick(R.id.single_game_answer_button)
    public void checkAnswer() {
        String answer = responseView.getText().toString();
        if (isCorrectAnswer(answer)){
            updateStatisticsWithCorrectQuestion();
            question = null;
            presenter.loadUnseenQuestion(statistics.getQuestionSeenList());
            answerButton.setEnabled(false);
            responseView.setText("");
            showOkSnackBar();
        } else {
            showErrorSnackBar();
        }
    }

    private void showErrorSnackBar() {
        Snackbar snackbar = Snackbar
                .make(view, getString(R.string.single_game_wrong_answer), Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.show();
    }

    private void showOkSnackBar() {
        Snackbar snackbar = Snackbar
                .make(view, getString(R.string.single_game_right_answer), Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.GREEN);
        snackbar.show();
    }

    private void updateStatisticsWithCorrectQuestion() {
        int questionsInARow = statistics.getCorrectQuestionsInARow();
        statistics.setCorrectQuestionsInARow(questionsInARow++);
        List<Question> correctQuestionsList =  statistics.getCorrectQuestionList();
        correctQuestionsList.add(question);
        statistics.setCorrectQuestionList(correctQuestionsList);
        presenter.updateStatistics(statistics);
    }

    private boolean isCorrectAnswer(String answer) {
        return presenter.checkAnswer(answer, question.getAnswer());
    }

    private void initializeInjector() {
        DaggerSingleGameComponent.builder()
                .applicationComponent(getApplicationComponent())
                .singleGameModule(new SingleGameModule())
                .build()
                .inject(this);
    }
}
