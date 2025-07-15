package org.sportradar.core;

import java.time.ZonedDateTime;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final ZonedDateTime createdDate;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.createdDate = ZonedDateTime.now();
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public synchronized void updateScores(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public synchronized int getTotal() {
        return homeScore + awayScore;
    }

    @Override
    public synchronized String toString() {
        String sb = homeTeam +
                " " +
                homeScore +
                " - " +
                awayTeam +
                " " +
                awayScore;
        return sb;
    }
}
