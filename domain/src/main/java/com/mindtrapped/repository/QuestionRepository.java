package com.mindtrapped.repository;

import com.mindtrapped.exception.NoMoreQuestionsFoundException;
import com.mindtrapped.model.Question;

import java.util.Set;

/**
 * Interface that represents a Repository for getting question related data.
 */
public interface QuestionRepository {

    Question getUnseenQuestion(Set<Question> questionSeenList) throws NoMoreQuestionsFoundException;
}
