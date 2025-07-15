package org.sportradar.core;

import org.sportradar.exaception.ScoreBoardException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoard {
    private final ConcurrentHashMap<String, Match> matches;

    public ScoreBoard() {
        matches = new ConcurrentHashMap<>();
    }

    /**
     * Adds a new match with score 0–0.
     *
     * @param homeTeam name of home team, case-insensitive
     * @param awayTeam name of away team, case-insensitive
     * @return true if match was added to score board successfully, false otherwise
     */
    public boolean startMatch(String homeTeam, String awayTeam) {
        validateTeamNames(homeTeam, awayTeam);
        var existed = matches.putIfAbsent(MatchUtils.getKey(homeTeam, awayTeam), new Match(homeTeam, awayTeam));
        return existed == null;
    }

    private static void validateTeamNames(String homeTeam, String awayTeam) {
        if (homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new ScoreBoardException("Team name cannot be blank");
        }
    }

    /**
     * Removes match from scoreboard.
     *
     * @param homeTeam name of home team, case-insensitive
     * @param awayTeam name of away team, case-insensitive
     * @return true if match was found and removed from score board, false otherwise
     */
    public boolean stopMatch(String homeTeam, String awayTeam) {
        return matches.remove(MatchUtils.getKey(homeTeam, awayTeam)) != null;
    }

    /**
     * Updates score of a given match.
     *
     * @param homeTeam  name of home team, case-insensitive
     * @param awayTeam  name of away team, case-insensitive
     * @param homeScore score of home team
     * @param awayScore score of away team
     * @throws ScoreBoardException if match for given team pair does not exist
     */
    public Match updateScores(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        validateScores(homeScore, awayScore);
        var updatedMatch = matches.computeIfPresent(MatchUtils.getKey(homeTeam, awayTeam), (_, match) -> {
            match.updateScores(homeScore, awayScore);
            return match;
        });
        if (updatedMatch == null) {
            throw new ScoreBoardException("Match does not exist");
        }
        return updatedMatch;
    }

    private void validateScores(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
    }

    public List<Match> getAll() {
        return new ArrayList<>(matches.values());
    }

    /**
     * Returns list of matches sorted by score and time.
     */
    public List<Match> getSummary() {
        return matches.values().stream()
                .sorted(Comparator.comparingInt(Match::getTotal)
                        .thenComparing(Match::getCreatedDate))
                .toList().reversed();
    }
}
