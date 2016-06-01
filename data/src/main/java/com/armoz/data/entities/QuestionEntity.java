package com.armoz.data.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class QuestionEntity extends RealmObject{

    @PrimaryKey
    private int id;

    private String question;

    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionEntity() {
        //empty
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Question Entity Details *****\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("question=" + this.getQuestion() + "\n");
        stringBuilder.append("fullname=" + this.getAnswer() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
