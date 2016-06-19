package com.mindtrapped.model;

public class QuestionStatistics {

    private Question question;
    private Statistics statistics;

    private QuestionStatus questionStatus;

    private boolean resetQuestions = false;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public QuestionStatus getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(QuestionStatus questionStatus) {
        this.questionStatus = questionStatus;
    }

    public boolean isResetQuestions() {
        return resetQuestions;
    }

    public void setResetQuestions(boolean resetQuestions) {
        this.resetQuestions = resetQuestions;
    }
}
