
package com.armoz.mindtrapped.presentation.singlegame.presenter;

import com.armoz.mindtrapped.presentation.base.PerActivity;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.mindtrapped.interactor.AnswerQuestionUseCase;
import com.mindtrapped.interactor.DefaultSubscriber;
import com.mindtrapped.interactor.LoadSingleGameUseCase;
import com.mindtrapped.interactor.ResetQuestionStatisticsUseCase;
import com.mindtrapped.interactor.SkipQuestionUseCase;
import com.mindtrapped.model.Question;
import com.mindtrapped.model.QuestionStatistics;
import com.mindtrapped.model.QuestionStatus;
import com.mindtrapped.model.Statistics;

@PerActivity
public class SingleGamePresenter {

    private View view;

    private final LoadSingleGameUseCase loadSingleGameUseCase;
    private final AnswerQuestionUseCase answerQuestionUseCase;
    private final SkipQuestionUseCase skipQuestionUseCase;
    private final ResetQuestionStatisticsUseCase resetQuestionStatisticsUseCase;

    private Question question;
    private Statistics statistics;

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

    public void checkAnswer(String userAnswer) {
        answerQuestionUseCase.execute(new QuestionStatisticsSubscriber(), question, statistics, userAnswer);
    }

    public void skipQuestion() {
        skipQuestionUseCase.execute(new QuestionStatisticsSubscriber(), question, statistics);
    }

    private void resetQuestionStatistics() {
        view.showReset();
        resetQuestionStatisticsUseCase.execute(new QuestionStatisticsSubscriber(), statistics);
    }

    @RxLogSubscriber
    private final class QuestionStatisticsSubscriber extends DefaultSubscriber<QuestionStatistics> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onNext(QuestionStatistics questionStatistics) {

            if (questionStatistics.getQuestionStatus() == QuestionStatus.MISSED){
                view.showMiss();
            } else if (questionStatistics.getQuestionStatus() == QuestionStatus.OK){
                view.showOk();
            } else if (questionStatistics.getQuestionStatus() == QuestionStatus.SKIPPED){
                view.showSkip();
            }
            if (questionStatistics.isResetQuestions()) {
                resetQuestionStatistics();
                return;
            }

            setQuestion(questionStatistics.getQuestion());
            setStatistics(questionStatistics.getStatistics());
            view.onQuestionStatisticsLoaded(question, statistics);
        }

    }

    public interface View {

        void onQuestionStatisticsLoaded(Question question, Statistics statistics);

        void showOk();

        void showMiss();

        void showSkip();

        void showReset();
    }
}
