
package com.armoz.mindtrapped.presentation.singlegame.presenter;

import android.os.CountDownTimer;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.mindtrapped.interactor.AnswerQuestionUseCase;
import com.mindtrapped.interactor.DefaultSubscriber;
import com.mindtrapped.interactor.LoadSingleGameUseCase;
import com.mindtrapped.interactor.ResetQuestionStatisticsUseCase;
import com.mindtrapped.interactor.SkipQuestionUseCase;
import com.mindtrapped.model.AnswerEnum;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.QuestionStatistics;
import com.mindtrapped.model.QuestionStatus;
import com.mindtrapped.model.Statistics;

@PerActivity
public class SingleGamePresenter {

    private static final int DEFAULT_TIME = 60000;
    public static final int MISSED_QUESTION_PENALTY_TIME = -2000;
    public static final int CORRECT_QUESTION_REWARD_TIME = 5000;

    private View view;

    private final LoadSingleGameUseCase loadSingleGameUseCase;
    private final AnswerQuestionUseCase answerQuestionUseCase;
    private final SkipQuestionUseCase skipQuestionUseCase;
    private final ResetQuestionStatisticsUseCase resetQuestionStatisticsUseCase;

    private Question question;
    private Statistics statistics;

    private CountDownTimer countDownTimer;
    int timeToAdd = 0;


    public SingleGamePresenter(LoadSingleGameUseCase loadSingleGameUseCase,
                               AnswerQuestionUseCase answerQuestionUseCase,
                               SkipQuestionUseCase skipQuestionUseCase,
                               ResetQuestionStatisticsUseCase resetQuestionStatisticsUseCase) {
        this.loadSingleGameUseCase = loadSingleGameUseCase;
        this.answerQuestionUseCase = answerQuestionUseCase;
        this.skipQuestionUseCase = skipQuestionUseCase;
        this.resetQuestionStatisticsUseCase = resetQuestionStatisticsUseCase;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void loadInitialData() {
        loadSingleGameUseCase.execute(new QuestionStatisticsSubscriber());
    }

    public void checkAnswer(AnswerEnum userAnswer) {
        answerQuestionUseCase.execute(new QuestionStatisticsSubscriber(), question, statistics, userAnswer);
    }

    public void skipQuestion() {
        skipQuestionUseCase.execute(new QuestionStatisticsSubscriber(), question, statistics);
    }

    private void resetQuestionStatistics() {
        view.showReset();
        resetQuestionStatisticsUseCase.execute(new QuestionStatisticsSubscriber(), statistics);
    }

    private void startTimer(int timeInMillis) {
        timeToAdd = 0;

        countDownTimer = new CountDownTimer(timeInMillis,10) {
            @Override
            public void onTick(long millisUntilFinished) {
                view.setProgressBarProgress((int)millisUntilFinished);
                if(timeToAdd != 0){
                    countDownTimer.cancel();
                    startTimer(Math.min((int) millisUntilFinished + timeToAdd, DEFAULT_TIME));
                }
            }

            @Override
            public void onFinish() {
                //showSkip();
            }
        };
        countDownTimer.start();
    }

    @RxLogSubscriber
    private final class QuestionStatisticsSubscriber extends DefaultSubscriber<QuestionStatistics> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onNext(QuestionStatistics questionStatistics) {

            setQuestion(questionStatistics.getQuestion());
            setStatistics(questionStatistics.getStatistics());
            view.onQuestionStatisticsLoaded(question, statistics);

            if (questionStatistics.getQuestionStatus() == QuestionStatus.MISSED){
                view.showMiss();
                timeToAdd = MISSED_QUESTION_PENALTY_TIME;
                return;
            } else if (questionStatistics.getQuestionStatus() == QuestionStatus.OK){
                view.showOk();
                timeToAdd = CORRECT_QUESTION_REWARD_TIME;
                return;
            } else if (questionStatistics.getQuestionStatus() == QuestionStatus.SKIPPED){
                view.showSkip();
                return;
            }

            startTimer(DEFAULT_TIME);
            /*if (questionStatistics.isResetQuestions()) {
                resetQuestionStatistics();
                return;
            }*/
        }
    }

    public interface View {

        void onQuestionStatisticsLoaded(Question question, Statistics statistics);

        void showOk();

        void showMiss();

        void showSkip();

        void showReset();

        void setProgressBarProgress(int progress);
    }
}
