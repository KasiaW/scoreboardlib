package org.sportradar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MatchUtilsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "brazil, poland, BRAZIL-POLAND",
            "BRAZIL, POLAND, BRAZIL-POLAND",
            "bRAZil, poland, BRAZIL-POLAND",
            "bRAZil, '', BRAZIL-",
            "poland, brazil, POLAND-BRAZIL",
    })
    void getsKey(String homeTeam, String awayTeam, String expectedKey) {
        assertEquals(expectedKey, MatchUtils.getKey(homeTeam, awayTeam));
    }

}