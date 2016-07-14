package com.mindtrapped.model;

import java.util.List;

public class RankingBuilder {

    private List<Statistics> topTotalCorrectAnswersStatistics;
    private List<Statistics> topConsecutiveCorrectAnswersStatistics;

    public static RankingBuilder rankingBuilder() {
        return new RankingBuilder();
    }


    public RankingBuilder setTopTotalCorrectAnswersStatistics(List<Statistics> topTotalCorrectAnswersStatistics) {
        this.topTotalCorrectAnswersStatistics = topTotalCorrectAnswersStatistics;
        return this;
    }

    public RankingBuilder setTopConsecutiveCorrectAnswersStatistics(List<Statistics> topConsecutiveCorrectAnswersStatistics) {
        this.topConsecutiveCorrectAnswersStatistics = topConsecutiveCorrectAnswersStatistics;
        return this;
    }

    public Ranking build(){
        return new Ranking(topTotalCorrectAnswersStatistics, topConsecutiveCorrectAnswersStatistics);
    }
}
