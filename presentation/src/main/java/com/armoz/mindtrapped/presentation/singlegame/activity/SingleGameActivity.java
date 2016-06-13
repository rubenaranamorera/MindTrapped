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

import java.util.ArrayList;
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

    @BindView(R.id.single_game_correct_questions)
    TextView correctQuestionsView;

    @BindView(R.id.single_game_seen_questions)
    TextView seenQuestionsView;

    @BindView(R.id.single_game_questions_in_a_row)
    TextView correctQuestionsInARowView;

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
        setView(findViewById(R.id.single_game_layout));
        ButterKnife.bind(this);
        initializeInjector();

        presenter.setActivity(this);
        presenter.loadStatistics();
    }

    public void onStatisticsLoaded(Statistics statistics) {
        this.statistics = statistics;
        presenter.loadUnseenQuestion(statistics.getQuestionSeenList());

        correctQuestionsView.setText(String.valueOf(statistics.getCorrectQuestionList().size()));
        seenQuestionsView.setText(String.valueOf(statistics.getQuestionSeenList().size() - 1));
        correctQuestionsInARowView.setText(String.valueOf(statistics.getCorrectQuestionsInARow()));

        //TODO: show total lives
    }

    public void onQuestionLoaded(Question question) {
        this.question = question;
        questionView.setText(question.getQuestion());
        updateStatisticsWithSeenQuestion();
        answerButton.setEnabled(true);
    }

    private void updateStatisticsWithSeenQuestion() {
        List<Question> questionSeenList = statistics.getQuestionSeenList();
        questionSeenList.add(question);
        statistics.setQuestionSeenList(questionSeenList);
        presenter.updateStatistics(statistics);
        seenQuestionsView.setText(String.valueOf(statistics.getQuestionSeenList().size() - 1));
        correctQuestionsInARowView.setText(String.valueOf(statistics.getCorrectQuestionsInARow()));
    }

    @OnClick(R.id.single_game_answer_button)
    public void checkAnswer() {
        String answer = responseView.getText().toString();
        if (isCorrectAnswer(answer)) {
            updateStatisticsWithCorrectQuestion();
            question = null;
            presenter.loadUnseenQuestion(statistics.getQuestionSeenList());
            answerButton.setEnabled(false);
            responseView.setText("");
            showConfirmation(getString(R.string.single_game_right_answer));
        } else {
            updateStatisticsWithMissedQuestion();
            showError(getString(R.string.single_game_wrong_answer));
        }
    }

    private void updateStatisticsWithMissedQuestion() {
        statistics.setCorrectQuestionsInARow(0);
        presenter.updateStatistics(statistics);
        correctQuestionsInARowView.setText(String.valueOf(statistics.getCorrectQuestionsInARow()));
    }

    private void updateStatisticsWithCorrectQuestion() {
        int questionsInARow = statistics.getCorrectQuestionsInARow() + 1;
        statistics.setCorrectQuestionsInARow(questionsInARow);
        List<Question> correctQuestionsList = statistics.getCorrectQuestionList();
        correctQuestionsList.add(question);
        statistics.setCorrectQuestionList(correctQuestionsList);
        presenter.updateStatistics(statistics);
        correctQuestionsView.setText(String.valueOf(statistics.getCorrectQuestionList().size()));
        correctQuestionsInARowView.setText(String.valueOf(statistics.getCorrectQuestionsInARow()));
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

    public void restartQuestionStatistics() {
        statistics.setCorrectQuestionList(new ArrayList());
        statistics.setQuestionSeenList(new ArrayList());
        presenter.updateStatistics(statistics);
        onStatisticsLoaded(statistics);
    }
}
