package com.mindtrapped.repository;

import com.mindtrapped.model.Statistics;

/**
 * Interface that represents a Repository for getting statistics related data.
 */
public interface StatisticsRepository {

    Statistics getStatistics();

    void updateStatistics(Statistics statistics);
}
