package com.armoz.data.entities;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class StatisticsEntity extends RealmObject {

    @PrimaryKey
    private String id;

    private int correctQuestions = 0;

    private int correctQuestionsInARow = 0;

    private Date date;

    public StatisticsEntity() {
        //empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Statistics Entity Details *****\n");
        stringBuilder.append("correctQuestionsInARow=" + this.getCorrectQuestionsInARow() + "\n");
        stringBuilder.append("correctQuestions=" + this.getCorrectQuestions() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
