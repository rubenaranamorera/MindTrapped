package com.mindtrapped.model;

import java.util.Date;
import java.util.Set;

public class Statistics {

    private long id;

    private Set<Question> seenQuestionSet;

    private int correctQuestions;

    private int correctQuestionsInARow;

    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Question> getSeenQuestionSet() {
        return seenQuestionSet;
    }

    public void setSeenQuestionSet(Set<Question> seenQuestionSet) {
        this.seenQuestionSet = seenQuestionSet;
    }

    public int getCorrectQuestions() {
        return correctQuestions;
    }

    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    public int getCorrectQuestionsInARow() {
        return correctQuestionsInARow;
    }

    public void setCorrectQuestionsInARow(int correctQuestionsInARow) {
        this.correctQuestionsInARow = correctQuestionsInARow;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
