package org.sportradar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoard {
    private final ConcurrentHashMap<String, Match> matches;

    public ScoreBoard() {
        matches = new ConcurrentHashMap<>();
    }

    public boolean startMatch(String homeTeam, String awayTeam) {
        var existed = matches.putIfAbsent(MatchUtils.getKey(homeTeam, awayTeam), new Match(homeTeam, awayTeam));
        return existed == null;
    }

    public void stopMatch(String homeTeam, String awayTeam) {
        matches.remove(MatchUtils.getKey(homeTeam, awayTeam));
    }

    public Match updateScores(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        return matches.computeIfPresent(MatchUtils.getKey(homeTeam, awayTeam), (_, match) -> {
            match.updateScores(homeScore, awayScore);
            return match;
        });
    }

    public List<Match> getAll() {
        return new ArrayList<>(matches.values());
    }

    public List<Match> getSummary() {
        return matches.values().stream()
                .sorted(Comparator.comparingInt(Match::getTotal)
                        .thenComparing(Match::getCreatedDate))
                .toList().reversed();
    }
}
