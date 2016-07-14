package com.mindtrapped.repository;

import com.mindtrapped.model.Statistics;

import java.util.List;

/**
 * Interface that represents a Repository for getting statistics related data.
 */
public interface StatisticsRepository {

    void saveStatistics(Statistics statistics);

    List<Statistics> getTopTotalCorrectAnswers(int limit);

    List<Statistics> getTopConsecutiveCorrectAnswers(int limit);
}
