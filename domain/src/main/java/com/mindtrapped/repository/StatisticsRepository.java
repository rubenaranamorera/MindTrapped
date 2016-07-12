package com.mindtrapped.repository;

import com.mindtrapped.model.Statistics;

/**
 * Interface that represents a Repository for getting statistics related data.
 */
public interface StatisticsRepository {

    void saveStatistics(Statistics statistics);
}
