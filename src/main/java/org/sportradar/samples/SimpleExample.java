package org.sportradar.samples;

import org.sportradar.core.Match;
import org.sportradar.core.ScoreBoard;

import java.util.List;

public class SimpleExample {
    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startMatch("Mexico", "Canada");
        scoreBoard.startMatch("Spain", "Brazil");
        scoreBoard.startMatch("Germany", "France");
        scoreBoard.startMatch("Uruguay", "Italy");
        scoreBoard.startMatch("Argentina", "Australia");

        scoreBoard.updateScores("Mexico", "Canada", 0, 5);
        scoreBoard.updateScores("Spain", "Brazil", 10, 2);
        scoreBoard.updateScores("Germany", "France", 2, 2);
        scoreBoard.updateScores("Uruguay", "Italy", 6, 6);
        scoreBoard.updateScores("Argentina", "Australia", 3, 1);

        printSummary(scoreBoard);
    }

    private static void printSummary(ScoreBoard scoreBoard) {
        System.out.println("=== Match Summary ===");
        for (var match : scoreBoard.getSummary()) {
            System.out.println(match);
        }
    }
}
