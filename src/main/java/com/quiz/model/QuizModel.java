package com.quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Central model for the Quiz application.
 * Manages game state and provides interface for the controller.
 */
public class QuizModel {
    private QuizGame game;
    private List<ModelObserver> observers;

    /**
     * Interface for observers to listen to model changes.
     */
    public interface ModelObserver {
        void modelChanged();
    }

    /**
     * Constructor for QuizModel.
     */
    public QuizModel() {
        this.observers = new ArrayList<>();
    }

    /**
     * Initialize a new game with two players.
     *
     * @param player1Name Name of player 1
     * @param player1NetName Network name of player 1
     * @param player2Name Name of player 2
     * @param player2NetName Network name of player 2
     */
    public void initializeGame(String player1Name, String player1NetName,
                                String player2Name, String player2NetName) {
        Player p1 = new Player(player1Name, player1NetName);
        Player p2 = new Player(player2Name, player2NetName);
        this.game = new QuizGame(p1, p2);
        notifyObservers();
    }

    /**
     * Get the current game state.
     *
     * @return The QuizGame instance
     */
    public QuizGame getGame() {
        return game;
    }

    /**
     * Get the current player.
     *
     * @return The current player
     */
    public Player getCurrentPlayer() {
        if (game == null) return null;
        return game.getCurrentPlayer();
    }

    /**
     * Get a random question from a specific category.
     *
     * @param category The category name
     * @return A random question from the category, or null if no questions exist
     */
    public Question getRandomQuestion(String category) {
        List<Question> questions = game.getQuestionsForCategory(category);
        if (questions.isEmpty()) {
            return null;
        }
        int randomIndex = (int) (Math.random() * questions.size());
        return questions.get(randomIndex);
    }

    /**
     * Process an answer submitted by the current player.
     *
     * @param question The question being answered
     * @param answerIndex The index of the selected answer
     * @return true if the answer is correct, false otherwise
     */
    public boolean submitAnswer(Question question, int answerIndex) {
        boolean isCorrect = question.isCorrectAnswer(answerIndex);

        if (!isCorrect) {
            game.getCurrentPlayer().decreaseScore();
        }

        notifyObservers();
        return isCorrect;
    }

    /**
     * Switch to the next player's turn.
     */
    public void nextTurn() {
        game.switchPlayer();
        notifyObservers();
    }

    /**
     * Check if the game is over.
     *
     * @return true if one player has been eliminated
     */
    public boolean isGameOver() {
        return game != null && game.isGameOver();
    }

    /**
     * Get the winner of the game.
     *
     * @return The winning player, or null if game is not over
     */
    public Player getWinner() {
        if (game == null) return null;
        return game.getWinner();
    }

    /**
     * Add a question to the game.
     *
     * @param category The category of the question
     * @param question The question to add
     */
    public void addQuestion(String category, Question question) {
        if (game != null) {
            game.addQuestion(category, question);
            notifyObservers();
        }
    }

    /**
     * Get all questions for a category.
     *
     * @param category The category name
     * @return List of questions in the category
     */
    public List<Question> getQuestionsForCategory(String category) {
        if (game == null) return new ArrayList<>();
        return game.getQuestionsForCategory(category);
    }

    /**
     * Get all question categories.
     *
     * @return Array of category names
     */
    public String[] getCategories() {
        if (game == null) return new String[0];
        return game.getCategories();
    }

    /**
     * Register an observer to listen to model changes.
     *
     * @param observer The observer to register
     */
    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    /**
     * Unregister an observer.
     *
     * @param observer The observer to unregister
     */
    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify all observers of model changes.
     */
    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.modelChanged();
        }
    }

    /**
     * Reset the game.
     */
    public void resetGame() {
        if (game != null) {
            game.reset();
            notifyObservers();
        }
    }
}
