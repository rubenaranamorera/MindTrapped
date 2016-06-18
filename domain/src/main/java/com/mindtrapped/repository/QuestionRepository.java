package com.mindtrapped.repository;

import com.mindtrapped.model.Question;

import java.util.Set;

import rx.Observable;

/**
 * Interface that represents a Repository for getting question related data.
 */
public interface QuestionRepository {

    Observable<Question> getUnseenQuestion(Set<Question> questionSeenList);
}
