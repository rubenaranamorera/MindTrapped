package com.mindtrapped.model;

import java.util.Set;

public class Statistics {

    private long id;

    private Set<Question> seenQuestionSet;

    private Set<Question> correctQuestionSet;

    private int correctQuestionsInARow;


    public Set<Question> getSeenQuestionSet() {
        return seenQuestionSet;
    }

    public void setSeenQuestionSet(Set<Question> seenQuestionSet) {
        this.seenQuestionSet = seenQuestionSet;
    }

    public Set<Question> getCorrectQuestionSet() {
        return correctQuestionSet;
    }

    public void setCorrectQuestionSet(Set<Question> correctQuestionSet) {
        this.correctQuestionSet = correctQuestionSet;
    }

    public int getCorrectQuestionsInARow() {
        return correctQuestionsInARow;
    }

    public void setCorrectQuestionsInARow(int correctQuestionsInARow) {
        this.correctQuestionsInARow = correctQuestionsInARow;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
