package com.quiz.model;

import java.util.Objects;

/**
 * Represents a quiz player with scoring and progress tracking.
 *
 * This class tracks a player's name, score, correct answers, and other game statistics.
 * It provides methods for updating the player's score based on their performance.
 */
public class Player {
    private String name;
    private int score;
    private int correctAnswers;
    private int totalQuestions;
    private int remainingLives;
    private static final int INITIAL_LIVES = 3;

    /**
     * Constructs a Player with a name.
     *
     * @param name the player's name
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.correctAnswers = 0;
        this.totalQuestions = 0;
        this.remainingLives = INITIAL_LIVES;
    }

    /**
     * Constructs a Player with name and initial lives.
     *
     * @param name the player's name
     * @param initialLives the initial number of lives
     */
    public Player(String name, int initialLives) {
        this.name = name;
        this.score = 0;
        this.correctAnswers = 0;
        this.totalQuestions = 0;
        this.remainingLives = initialLives;
    }

    /**
     * Records a correct answer and updates score.
     *
     * @param points points to add for correct answer
     */
    public void recordCorrectAnswer(int points) {
        correctAnswers++;
        totalQuestions++;
        addScore(points);
    }

    /**
     * Records an incorrect answer and deducts life.
     */
    public void recordIncorrectAnswer() {
        totalQuestions++;
        loseLife();
    }

    /**
     * Records a correct answer with default points (10).
     */
    public void recordCorrectAnswer() {
        recordCorrectAnswer(10);
    }

    /**
     * Adds points to the player's score.
     *
     * @param points the points to add
     */
    public void addScore(int points) {
        if (points > 0) {
            score += points;
        }
    }

    /**
     * Reduces remaining lives by 1.
     *
     * @return true if the player still has lives, false if lives are exhausted
     */
    public boolean loseLife() {
        if (remainingLives > 0) {
            remainingLives--;
        }
        return remainingLives > 0;
    }

    /**
     * Checks if the player has lost (no lives remaining).
     *
     * @return true if player has no lives left, false otherwise
     */
    public boolean isEliminated() {
        return remainingLives <= 0;
    }

    /**
     * Gets the player's accuracy percentage.
     *
     * @return accuracy as a percentage (0-100), or 0 if no questions answered
     */
    public double getAccuracy() {
        if (totalQuestions == 0) {
            return 0;
        }
        return (double) correctAnswers / totalQuestions * 100;
    }

    /**
     * Resets the player's statistics for a new game.
     */
    public void resetScore() {
        score = 0;
        correctAnswers = 0;
        totalQuestions = 0;
        remainingLives = INITIAL_LIVES;
    }

    /**
     * Gets a summary of the player's performance.
     *
     * @return a string with score, correct answers, and accuracy
     */
    public String getStatsSummary() {
        return String.format("Score: %d | Correct: %d/%d | Accuracy: %.1f%%",
                score, correctAnswers, totalQuestions, getAccuracy());
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = Math.max(0, score);
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = Math.max(0, remainingLives);
    }

    public static int getInitialLives() {
        return INITIAL_LIVES;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", correctAnswers=" + correctAnswers +
                ", totalQuestions=" + totalQuestions +
                ", remainingLives=" + remainingLives +
                '}';
    }
}
