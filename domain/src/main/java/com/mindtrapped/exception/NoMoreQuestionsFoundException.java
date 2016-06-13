package com.mindtrapped.exception;

/**
 * Exception throw by the application when there is no more questions
 */
public class NoMoreQuestionsFoundException extends Exception {

    public static final String MESSAGE = "There are not any unseen questions";

    public NoMoreQuestionsFoundException() {
        super(MESSAGE);
    }
}
