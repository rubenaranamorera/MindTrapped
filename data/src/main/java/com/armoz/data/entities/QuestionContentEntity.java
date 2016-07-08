package com.armoz.data.entities;

import com.mindtrapped.model.AnswerEnum;

public class QuestionContentEntity {

    private int id;

    private String question;

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    private AnswerEnum correctAnswer;


    public QuestionContentEntity() {
        //empty
    }

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

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public AnswerEnum getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(AnswerEnum correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
