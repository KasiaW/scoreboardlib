package org.sportradar.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sportradar.exaception.ScoreBoardException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {
    private ScoreBoard scoreBoard;

    @BeforeEach
    public void setup() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void returnsEmptyListWhenNoExistingMatches() {
        var result = scoreBoard.getAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void getsAll() {
        scoreBoard.startMatch("Brazil", "Poland");
        scoreBoard.startMatch("Germany", "Russia");

        var result = scoreBoard.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void startsMatch() {
        var result = scoreBoard.startMatch("Brazil", "Poland");

        assertTrue(result);
        assertEquals(1, scoreBoard.getAll().size());
    }

    @Test
    void startMatchReturnsFalseWhenMatchAlreadyExist() {
        scoreBoard.startMatch("Brazil", "Poland");

        var result = scoreBoard.startMatch("Brazil", "Poland");

        assertFalse(result);
        assertEquals(1, scoreBoard.getAll().size());
    }

    @Test
    void startMatchThrowsExceptionWhenTeamNameIsEmpty() {
        assertThrows(ScoreBoardException.class, () -> scoreBoard.startMatch("Brazil", ""));
    }

    @Test
    void stopsMatch() {
        scoreBoard.startMatch("Brazil", "Poland");

        var result = scoreBoard.stopMatch("Brazil", "Poland");

        assertTrue(result);
        assertTrue(scoreBoard.getAll().isEmpty());
    }

    @Test
    void stopMatchReturnsFalseWhenMatchDoesntExist() {
        assertFalse(scoreBoard.stopMatch("Brazil", "Poland"));
    }

    @Test
    void updatesScores() {
        scoreBoard.startMatch("Brazil", "Poland");
        scoreBoard.startMatch("Germany", "Russia");

        var result = scoreBoard.updateScores("Brazil", "Poland", 3, 2);
        assertEquals(3, result.getHomeScore());
        assertEquals(2, result.getAwayScore());
    }

    @Test
    void updatesScoreThrowsExceptionWhenMatchDoesntExist() {
        assertThrows(ScoreBoardException.class, () -> scoreBoard.updateScores("Brazil", "Poland", 3, 2));
    }

    @Test
    void updatesScoreThrowsExceptionWhenScoreIsInvalid() {
        scoreBoard.startMatch("Brazil", "Poland");
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.updateScores("Brazil", "Poland", -3, 2));
    }

    @Test
    void getsSummary() {
        scoreBoard.startMatch("Mexico", "Canada");
        scoreBoard.startMatch("Spain", "Brazil");
        scoreBoard.startMatch("Germany", "France");
        scoreBoard.startMatch("Uruguay", "Italy");
        scoreBoard.startMatch("Argentina", "Australia");

        var match1 = scoreBoard.updateScores("Mexico", "Canada", 0, 5);
        var match2 = scoreBoard.updateScores("Spain", "Brazil", 10, 2);
        var match3 = scoreBoard.updateScores("Germany", "France", 2, 2);
        var match4 = scoreBoard.updateScores("Uruguay", "Italy", 6, 6);
        var match5 = scoreBoard.updateScores("Argentina", "Australia", 3, 1);

        assertEquals(List.of(match4, match2, match1, match5, match3), scoreBoard.getSummary());
    }

    @Test
    void getSummaryReturnsEmptyListWhenNoMatches() {
        assertTrue(scoreBoard.getSummary().isEmpty());
    }
}
