package com.quiz.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Manages the overall quiz game state including questions and player management.
 */
public class QuizGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int currentQuestionIndex;
    private Map<String, List<Question>> questionsByCategory;
    private String[] categories = {
        "BWL",
        "Digitaltechnik",
        "Elektrotechnik",
        "Netzwerktechnik",
        "Datenbanken",
        "Programmierung"
    };

    /**
     * Constructor for QuizGame.
     *
     * @param player1 First player
     * @param player2 Second player
     */
    public QuizGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.currentQuestionIndex = 0;
        this.questionsByCategory = new HashMap<>();

        // Initialize empty question lists for each category
        for (String category : categories) {
            questionsByCategory.put(category, new ArrayList<>());
        }
    }

    /**
     * Default constructor.
     */
    public QuizGame() {
        this.questionsByCategory = new HashMap<>();
        for (String category : categories) {
            questionsByCategory.put(category, new ArrayList<>());
        }
    }

    // Getters and setters
    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int index) {
        this.currentQuestionIndex = index;
    }

    public Map<String, List<Question>> getQuestionsByCategory() {
        return questionsByCategory;
    }

    public String[] getCategories() {
        return categories;
    }

    /**
     * Get questions for a specific category.
     *
     * @param category The category name
     * @return List of questions in the category
     */
    public List<Question> getQuestionsForCategory(String category) {
        return questionsByCategory.getOrDefault(category, new ArrayList<>());
    }

    /**
     * Add a question to a specific category.
     *
     * @param category The category name
     * @param question The question to add
     */
    public void addQuestion(String category, Question question) {
        if (questionsByCategory.containsKey(category)) {
            questionsByCategory.get(category).add(question);
        }
    }

    /**
     * Switch to the other player's turn.
     */
    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    /**
     * Check if the game is over (one player has been eliminated).
     *
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver() {
        return player1.isEliminated() || player2.isEliminated();
    }

    /**
     * Get the winner of the game.
     *
     * @return The player with remaining lives, or null if game is not over
     */
    public Player getWinner() {
        if (!isGameOver()) {
            return null;
        }
        return player1.isEliminated() ? player2 : player1;
    }

    /**
     * Reset the game for a new round.
     */
    public void reset() {
        player1.setScore(3);
        player2.setScore(3);
        currentPlayer = player1;
        currentQuestionIndex = 0;
    }

    @Override
    public String toString() {
        return "QuizGame{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", currentPlayer=" + currentPlayer +
                ", categories=" + categories.length +
                '}';
    }
}
