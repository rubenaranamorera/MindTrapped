package com.mindtrapped.model;

import java.util.List;

public class Statistics {

    private List<Question> questionSeenList;

    private List<Question> correctQuestionList;

    private int correctQuestionsInARow;

    public List<Question> getQuestionSeenList() {
        return questionSeenList;
    }

    public void setQuestionSeenList(List<Question> questionSeenList) {
        this.questionSeenList = questionSeenList;
    }

    public List<Question> getCorrectQuestionList() {
        return correctQuestionList;
    }

    public void setCorrectQuestionList(List<Question> correctQuestionList) {
        this.correctQuestionList = correctQuestionList;
    }

    public int getCorrectQuestionsInARow() {
        return correctQuestionsInARow;
    }

    public void setCorrectQuestionsInARow(int correctQuestionsInARow) {
        this.correctQuestionsInARow = correctQuestionsInARow;
    }
}
