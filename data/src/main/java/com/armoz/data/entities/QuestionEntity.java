package com.armoz.data.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class QuestionEntity extends RealmObject{

    @PrimaryKey
    private int id;

    private String questionType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public QuestionEntity() {
        //empty
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Question Entity Details *****\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("questionType=" + this.getQuestionType() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
