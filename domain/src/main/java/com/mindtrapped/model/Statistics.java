package com.mindtrapped.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Statistics {

    private String id;

    private Set<Question> seenQuestionSet = new HashSet<>();

    private int correctQuestions = 0;

    private int correctQuestionsInARow = 0;

    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
