package com.quiz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Core model class that manages the quiz game state.
 *
 * This class maintains the quiz questions, players, current game state, and provides
 * methods for game flow control such as progressing through questions and switching
 * between players.
 */
public class QuizModel {
    private List<Question> questions;
    private List<Player> players;
    private int currentQuestionIndex;
    private int currentPlayerIndex;
    private boolean gameActive;

    /**
     * Constructs an empty QuizModel.
     */
    public QuizModel() {
        this.questions = new ArrayList<>();
        this.players = new ArrayList<>();
        this.currentQuestionIndex = 0;
        this.currentPlayerIndex = 0;
        this.gameActive = false;
    }

    /**
     * Constructs a QuizModel with questions and players.
     *
     * @param questions list of quiz questions
     * @param players list of players
     */
    public QuizModel(List<Question> questions, List<Player> players) {
        this.questions = new ArrayList<>(questions);
        this.players = new ArrayList<>(players);
        this.currentQuestionIndex = 0;
        this.currentPlayerIndex = 0;
        this.gameActive = false;
    }

    /**
     * Starts a new game with current questions and players.
     *
     * @return true if game started successfully, false if no questions or players
     */
    public boolean startGame() {
        if (questions.isEmpty() || players.isEmpty()) {
            return false;
        }
        resetGameState();
        gameActive = true;
        return true;
    }

    /**
     * Ends the current game.
     */
    public void endGame() {
        gameActive = false;
    }

    /**
     * Checks if the game is currently active.
     *
     * @return true if game is active, false otherwise
     */
    public boolean isGameActive() {
        return gameActive;
    }

    /**
     * Resets game state for a new game.
     */
    public void resetGameState() {
        currentQuestionIndex = 0;
        currentPlayerIndex = 0;
        players.forEach(Player::resetScore);
    }

    /**
     * Gets the current question.
     *
     * @return the current question, or null if index is invalid
     */
    public Question getCurrentQuestion() {
        if (currentQuestionIndex < 0 || currentQuestionIndex >= questions.size()) {
            return null;
        }
        return questions.get(currentQuestionIndex);
    }

    /**
     * Gets the current player.
     *
     * @return the current player, or null if index is invalid
     */
    public Player getCurrentPlayer() {
        if (currentPlayerIndex < 0 || currentPlayerIndex >= players.size()) {
            return null;
        }
        return players.get(currentPlayerIndex);
    }

    /**
     * Processes a player's answer to the current question.
     *
     * @param answerIndex the index of the selected answer
     * @return true if the answer was correct, false otherwise
     */
    public boolean answerQuestion(int answerIndex) {
        if (!gameActive) {
            return false;
        }

        Question question = getCurrentQuestion();
        Player player = getCurrentPlayer();

        if (question == null || player == null) {
            return false;
        }

        if (!question.isValidAnswerIndex(answerIndex)) {
            return false;
        }

        boolean isCorrect = question.isAnswerCorrect(answerIndex);
        if (isCorrect) {
            player.recordCorrectAnswer();
        } else {
            player.recordIncorrectAnswer();
        }

        return isCorrect;
    }

    /**
     * Moves to the next question.
     *
     * @return true if there are more questions, false if quiz is complete
     */
    public boolean nextQuestion() {
        currentQuestionIndex++;
        return currentQuestionIndex < questions.size();
    }

    /**
     * Checks if there are more questions.
     *
     * @return true if there are more questions after current one
     */
    public boolean hasNextQuestion() {
        return currentQuestionIndex + 1 < questions.size();
    }

    /**
     * Moves to the next player (for multiplayer mode).
     *
     * @return true if there are more players, false if all players completed
     */
    public boolean nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size()) {
            return false;
        }
        return true;
    }

    /**
     * Gets the winner of the game (player with highest score).
     *
     * @return the player with the highest score, or null if no players
     */
    public Player getWinner() {
        if (players.isEmpty()) {
            return null;
        }
        return players.stream()
                .max((p1, p2) -> Integer.compare(p1.getScore(), p2.getScore()))
                .orElse(null);
    }

    /**
     * Gets a sorted list of players by score (highest first).
     *
     * @return list of players sorted by score in descending order
     */
    public List<Player> getPlayersByScore() {
        List<Player> sorted = new ArrayList<>(players);
        sorted.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        return sorted;
    }

    /**
     * Checks if all players are eliminated.
     *
     * @return true if all players have no lives left
     */
    public boolean allPlayersEliminated() {
        return players.stream().allMatch(Player::isEliminated);
    }

    /**
     * Gets the number of remaining active players.
     *
     * @return count of players with lives remaining
     */
    public int getActivePlayerCount() {
        return (int) players.stream()
                .filter(p -> !p.isEliminated())
                .count();
    }

    // Getters and Setters

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
        this.currentQuestionIndex = 0;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public void setPlayers(List<Player> players) {
        this.players = new ArrayList<>(players);
        this.currentPlayerIndex = 0;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int index) {
        if (index >= 0 && index < questions.size()) {
            this.currentQuestionIndex = index;
        }
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int index) {
        if (index >= 0 && index < players.size()) {
            this.currentPlayerIndex = index;
        }
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getTotalPlayers() {
        return players.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizModel quizModel = (QuizModel) o;
        return currentQuestionIndex == quizModel.currentQuestionIndex &&
               currentPlayerIndex == quizModel.currentPlayerIndex &&
               gameActive == quizModel.gameActive &&
               Objects.equals(questions, quizModel.questions) &&
               Objects.equals(players, quizModel.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questions, players, currentQuestionIndex, currentPlayerIndex, gameActive);
    }

    @Override
    public String toString() {
        return "QuizModel{" +
                "questions=" + questions.size() +
                ", players=" + players.size() +
                ", currentQuestionIndex=" + currentQuestionIndex +
                ", currentPlayerIndex=" + currentPlayerIndex +
                ", gameActive=" + gameActive +
                '}';
    }
}
