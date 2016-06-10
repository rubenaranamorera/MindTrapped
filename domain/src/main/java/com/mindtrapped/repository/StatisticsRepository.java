package com.mindtrapped.repository;

import com.mindtrapped.model.Statistics;

import rx.Observable;

/**
 * Interface that represents a Repository for getting statistics related data.
 */
public interface StatisticsRepository {

    Observable<Statistics> getStatistics();

    Observable updateStatistics(Statistics statistics);
}
