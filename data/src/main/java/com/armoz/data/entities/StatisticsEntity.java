package com.armoz.data.entities;

import io.realm.RealmList;
import io.realm.RealmObject;

public class StatisticsEntity extends RealmObject {

    private RealmList<QuestionEntity> questionSeenList;

    private RealmList<QuestionEntity> correctQuestionList;

    private int correctQuestionsInARow;

    public StatisticsEntity() {
        //empty
    }


    public RealmList<QuestionEntity> getQuestionSeenList() {
        return questionSeenList;
    }

    public void setQuestionSeenList(RealmList<QuestionEntity> questionSeenList) {
        this.questionSeenList = questionSeenList;
    }

    public RealmList<QuestionEntity> getCorrectQuestionList() {
        return correctQuestionList;
    }

    public void setCorrectQuestionList(RealmList<QuestionEntity> correctQuestionList) {
        this.correctQuestionList = correctQuestionList;
    }

    public int getCorrectQuestionsInARow() {
        return correctQuestionsInARow;
    }

    public void setCorrectQuestionsInARow(int correctQuestionsInARow) {
        this.correctQuestionsInARow = correctQuestionsInARow;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Statistics Entity Details *****\n");
        stringBuilder.append("correctQuestionsInARow=" + this.getCorrectQuestionsInARow() + "\n");
        stringBuilder.append("questionSeenListSize=" + this.getQuestionSeenList().size() + "\n");
        stringBuilder.append("correctQuestionList=" + this.getCorrectQuestionList().size() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }
}
