package com.quiz.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Player class.
 */
class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Alice");
    }

    @Test
    void testPlayerCreation() {
        assertEquals("Alice", player.getName());
        assertEquals(0, player.getScore());
        assertEquals(0, player.getCorrectAnswers());
        assertEquals(0, player.getTotalQuestions());
        assertEquals(Player.getInitialLives(), player.getRemainingLives());
    }

    @Test
    void testPlayerCreationWithCustomLives() {
        Player customPlayer = new Player("Bob", 5);
        assertEquals("Bob", customPlayer.getName());
        assertEquals(5, customPlayer.getRemainingLives());
    }

    @Test
    void testRecordCorrectAnswerDefaultPoints() {
        player.recordCorrectAnswer();
        assertEquals(10, player.getScore());
        assertEquals(1, player.getCorrectAnswers());
        assertEquals(1, player.getTotalQuestions());
    }

    @Test
    void testRecordCorrectAnswerCustomPoints() {
        player.recordCorrectAnswer(25);
        assertEquals(25, player.getScore());
        assertEquals(1, player.getCorrectAnswers());
        assertEquals(1, player.getTotalQuestions());
    }

    @Test
    void testRecordIncorrectAnswer() {
        int initialLives = player.getRemainingLives();
        player.recordIncorrectAnswer();
        assertEquals(initialLives - 1, player.getRemainingLives());
        assertEquals(0, player.getCorrectAnswers());
        assertEquals(1, player.getTotalQuestions());
    }

    @Test
    void testMultipleAnswers() {
        player.recordCorrectAnswer(15);
        player.recordIncorrectAnswer();
        player.recordCorrectAnswer(20);

        assertEquals(35, player.getScore());
        assertEquals(2, player.getCorrectAnswers());
        assertEquals(3, player.getTotalQuestions());
    }

    @Test
    void testAddScore() {
        player.addScore(50);
        assertEquals(50, player.getScore());
        player.addScore(30);
        assertEquals(80, player.getScore());
    }

    @Test
    void testAddScoreNegativePoints() {
        player.addScore(50);
        player.addScore(-10);
        assertEquals(50, player.getScore());
    }

    @Test
    void testLoseLife() {
        int initialLives = player.getRemainingLives();
        assertTrue(player.loseLife());
        assertEquals(initialLives - 1, player.getRemainingLives());
    }

    @Test
    void testLoseAllLives() {
        for (int i = 0; i < Player.getInitialLives(); i++) {
            player.loseLife();
        }
        assertEquals(0, player.getRemainingLives());
        assertTrue(player.isEliminated());
    }

    @Test
    void testLoseLifeWhenNoLivesRemaining() {
        for (int i = 0; i < Player.getInitialLives() + 1; i++) {
            player.loseLife();
        }
        assertEquals(0, player.getRemainingLives());
    }

    @Test
    void testIsEliminated() {
        assertFalse(player.isEliminated());
        for (int i = 0; i < Player.getInitialLives(); i++) {
            player.loseLife();
        }
        assertTrue(player.isEliminated());
    }

    @Test
    void testGetAccuracy() {
        assertEquals(0, player.getAccuracy());

        player.recordCorrectAnswer();
        player.recordCorrectAnswer();
        player.recordIncorrectAnswer();

        double accuracy = player.getAccuracy();
        assertEquals(66.66666666666666, accuracy, 0.001);
    }

    @Test
    void testGetAccuracyPerfect() {
        player.recordCorrectAnswer();
        player.recordCorrectAnswer();
        player.recordCorrectAnswer();
        assertEquals(100, player.getAccuracy());
    }

    @Test
    void testGetAccuracyZero() {
        player.recordIncorrectAnswer();
        player.recordIncorrectAnswer();
        assertEquals(0, player.getAccuracy());
    }

    @Test
    void testResetScore() {
        player.recordCorrectAnswer(50);
        player.recordIncorrectAnswer();
        player.recordIncorrectAnswer();

        assertEquals(50, player.getScore());
        assertNotEquals(0, player.getTotalQuestions());

        player.resetScore();

        assertEquals(0, player.getScore());
        assertEquals(0, player.getCorrectAnswers());
        assertEquals(0, player.getTotalQuestions());
        assertEquals(Player.getInitialLives(), player.getRemainingLives());
    }

    @Test
    void testSetName() {
        player.setName("Charlie");
        assertEquals("Charlie", player.getName());
    }

    @Test
    void testSetScore() {
        player.setScore(100);
        assertEquals(100, player.getScore());
    }

    @Test
    void testSetScoreNegative() {
        player.setScore(-50);
        assertEquals(0, player.getScore());
    }

    @Test
    void testSetRemainingLives() {
        player.setRemainingLives(10);
        assertEquals(10, player.getRemainingLives());
    }

    @Test
    void testSetRemainingLivesNegative() {
        player.setRemainingLives(-5);
        assertEquals(0, player.getRemainingLives());
    }

    @Test
    void testGetStatsSummary() {
        player.recordCorrectAnswer(15);
        player.recordIncorrectAnswer();
        String summary = player.getStatsSummary();
        assertTrue(summary.contains("15"));
        assertTrue(summary.contains("1/2"));
    }

    @Test
    void testEquality() {
        Player player2 = new Player("Alice");
        assertEquals(player, player2);
    }

    @Test
    void testInequalityDifferentName() {
        Player player2 = new Player("Bob");
        assertNotEquals(player, player2);
    }

    @Test
    void testHashCode() {
        Player player2 = new Player("Alice");
        assertEquals(player.hashCode(), player2.hashCode());
    }

    @Test
    void testToString() {
        String str = player.toString();
        assertTrue(str.contains("Alice"));
        assertTrue(str.contains("Player"));
    }
}
