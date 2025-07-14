package org.sportradar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchTest {

    @Test
    void prints() {
        var match = new Match("Mexico", "Canada");
        match.updateScores(0, 5);

        assertEquals("Mexico 0 - Canada 5", match.toString());
    }

    @Test
    void getsTotal() {
        var match = new Match("Mexico", "Canada");
        match.updateScores(3, 5);

        assertEquals(8, match.getTotal());
    }
}
