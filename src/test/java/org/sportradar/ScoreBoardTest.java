package org.sportradar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {
    private ScoreBoard scoreBoard;

    @BeforeEach
    public void setup() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void getAllReturnsEmptyList() {
        var result = scoreBoard.getAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void returnsEmptyListWhenNoExistingMatches() {
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

}
