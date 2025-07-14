package org.sportradar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertTrue(scoreBoard.startMatch("Brazil", "Poland"));
        assertEquals(1, scoreBoard.getAll().size());
    }

    @Test
    void returnFalseWhenStartsTheSameMatch() {
        scoreBoard.startMatch("Brazil", "Poland");

        assertFalse(scoreBoard.startMatch("Brazil", "Poland"));
        assertEquals(1, scoreBoard.getAll().size());
    }

    @Test
    void removesMatchAfterStopIt() {
        scoreBoard.startMatch("Brazil", "Poland");

        scoreBoard.stopMatch("Brazil", "Poland");

        var result = scoreBoard.getAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void doesNothingWhenRemoveNonExistingMatch() {
        assertDoesNotThrow(() -> scoreBoard.stopMatch("Brazil", "Poland"));
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
