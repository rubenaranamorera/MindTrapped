package com.mindtrapped.repository;

import com.mindtrapped.model.Question;

import rx.Observable;

/**
 * Interface that represents a Repository for getting question related data.
 */
public interface QuestionRepository {

    Observable<Question> question();
}
