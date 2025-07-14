package org.sportradar;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public synchronized void updateScores(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(homeTeam);
        sb.append(" ");
        sb.append(homeScore);
        sb.append(" - ");
        sb.append(awayTeam);
        sb.append(" ");
        sb.append(awayScore);
        return sb.toString();
    }
}
