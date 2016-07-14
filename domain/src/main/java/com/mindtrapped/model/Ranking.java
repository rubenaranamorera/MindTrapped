package com.mindtrapped.model;

import java.util.List;

public class Ranking {

    private static List<Statistics> topTotalCorrectAnswersStatistics;
    private static List<Statistics> topConsecutiveCorrectAnswersStatistics;

    public Ranking(List<Statistics> topTotalCorrectAnswersStatistics, List<Statistics> topConsecutiveCorrectAnswersStatistics) {
        this.topTotalCorrectAnswersStatistics = topTotalCorrectAnswersStatistics;
        this.topConsecutiveCorrectAnswersStatistics = topConsecutiveCorrectAnswersStatistics;
    }

    public List<Statistics> getTopTotalCorrectAnswersStatistics() {
        return topTotalCorrectAnswersStatistics;
    }

    public List<Statistics> getTopConsecutiveCorrectAnswersStatistics() {
        return topConsecutiveCorrectAnswersStatistics;
    }
}
