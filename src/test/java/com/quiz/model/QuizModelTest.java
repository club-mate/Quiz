package com.quiz.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the QuizModel class.
 */
class QuizModelTest {
    private QuizModel model;
    private List<Question> questions;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        // Create test questions
        questions = new ArrayList<>();
        questions.add(new Question(1, "What is 2 + 2?",
                Arrays.asList("1", "2", "4", "5"), 2, "easy", "Math"));
        questions.add(new Question(2, "What is the capital of France?",
                Arrays.asList("London", "Paris", "Berlin", "Madrid"), 1, "easy", "Geography"));
        questions.add(new Question(3, "What is 5 * 3?",
                Arrays.asList("10", "15", "20", "25"), 1, "medium", "Math"));

        // Create test players
        players = new ArrayList<>();
        players.add(new Player("Alice"));
        players.add(new Player("Bob"));

        model = new QuizModel(questions, players);
    }

    @Test
    void testQuizModelCreation() {
        assertEquals(3, model.getTotalQuestions());
        assertEquals(2, model.getTotalPlayers());
        assertFalse(model.isGameActive());
    }

    @Test
    void testEmptyQuizModel() {
        QuizModel emptyModel = new QuizModel();
        assertEquals(0, emptyModel.getTotalQuestions());
        assertEquals(0, emptyModel.getTotalPlayers());
    }

    @Test
    void testStartGame() {
        assertTrue(model.startGame());
        assertTrue(model.isGameActive());
        assertEquals(0, model.getCurrentQuestionIndex());
        assertEquals(0, model.getCurrentPlayerIndex());
    }

    @Test
    void testStartGameWithNoQuestions() {
        QuizModel emptyModel = new QuizModel();
        emptyModel.addPlayer(new Player("Alice"));
        assertFalse(emptyModel.startGame());
    }

    @Test
    void testStartGameWithNoPlayers() {
        QuizModel emptyModel = new QuizModel();
        emptyModel.addQuestion(new Question());
        assertFalse(emptyModel.startGame());
    }

    @Test
    void testEndGame() {
        model.startGame();
        model.endGame();
        assertFalse(model.isGameActive());
    }

    @Test
    void testGetCurrentQuestion() {
        model.startGame();
        Question current = model.getCurrentQuestion();
        assertNotNull(current);
        assertEquals(1, current.getId());
    }

    @Test
    void testGetCurrentPlayer() {
        model.startGame();
        Player current = model.getCurrentPlayer();
        assertNotNull(current);
        assertEquals("Alice", current.getName());
    }

    @Test
    void testAnswerQuestionCorrect() {
        model.startGame();
        boolean result = model.answerQuestion(2);
        assertTrue(result);
        assertEquals(10, model.getCurrentPlayer().getScore());
    }

    @Test
    void testAnswerQuestionIncorrect() {
        model.startGame();
        boolean result = model.answerQuestion(0);
        assertFalse(result);
        assertEquals(0, model.getCurrentPlayer().getScore());
        assertEquals(0, model.getCurrentPlayer().getCorrectAnswers());
    }

    @Test
    void testAnswerQuestionInvalidIndex() {
        model.startGame();
        boolean result = model.answerQuestion(10);
        assertFalse(result);
    }

    @Test
    void testAnswerQuestionWhenGameNotActive() {
        boolean result = model.answerQuestion(2);
        assertFalse(result);
    }

    @Test
    void testNextQuestion() {
        model.startGame();
        assertTrue(model.nextQuestion());
        assertEquals(1, model.getCurrentQuestionIndex());
        assertEquals(2, model.getCurrentQuestion().getId());
    }

    @Test
    void testNextQuestionAtEnd() {
        model.startGame();
        model.setCurrentQuestionIndex(2);
        assertFalse(model.nextQuestion());
    }

    @Test
    void testHasNextQuestion() {
        model.startGame();
        assertTrue(model.hasNextQuestion());
        model.nextQuestion();
        assertTrue(model.hasNextQuestion());
        model.nextQuestion();
        assertFalse(model.hasNextQuestion());
    }

    @Test
    void testNextPlayer() {
        model.startGame();
        assertTrue(model.nextPlayer());
        assertEquals(1, model.getCurrentPlayerIndex());
        assertEquals("Bob", model.getCurrentPlayer().getName());
    }

    @Test
    void testNextPlayerAtEnd() {
        model.startGame();
        model.setCurrentPlayerIndex(1);
        assertFalse(model.nextPlayer());
    }

    @Test
    void testAddQuestion() {
        QuizModel newModel = new QuizModel();
        newModel.addQuestion(new Question(1, "Test", Arrays.asList("A", "B"), 0));
        assertEquals(1, newModel.getTotalQuestions());
    }

    @Test
    void testAddPlayer() {
        QuizModel newModel = new QuizModel();
        newModel.addPlayer(new Player("Test"));
        assertEquals(1, newModel.getTotalPlayers());
    }

    @Test
    void testGetWinner() {
        model.startGame();
        model.getCurrentPlayer().addScore(50);
        model.nextPlayer();
        model.getCurrentPlayer().addScore(30);

        Player winner = model.getWinner();
        assertNotNull(winner);
        assertEquals("Alice", winner.getName());
        assertEquals(50, winner.getScore());
    }

    @Test
    void testGetWinnerWithNoPlayers() {
        QuizModel emptyModel = new QuizModel();
        assertNull(emptyModel.getWinner());
    }

    @Test
    void testGetWinnerWithTiedScores() {
        model.startGame();
        model.getCurrentPlayer().addScore(50);
        model.nextPlayer();
        model.getCurrentPlayer().addScore(50);

        Player winner = model.getWinner();
        assertNotNull(winner);
        assertEquals(50, winner.getScore());
    }

    @Test
    void testGetPlayersByScore() {
        model.startGame();
        model.getCurrentPlayer().addScore(30);
        model.nextPlayer();
        model.getCurrentPlayer().addScore(50);

        List<Player> sorted = model.getPlayersByScore();
        assertEquals(2, sorted.size());
        assertEquals("Bob", sorted.get(0).getName());
        assertEquals("Alice", sorted.get(1).getName());
    }

    @Test
    void testResetGameState() {
        model.startGame();
        model.answerQuestion(2);
        model.nextQuestion();
        model.nextPlayer();

        model.resetGameState();

        assertEquals(0, model.getCurrentQuestionIndex());
        assertEquals(0, model.getCurrentPlayerIndex());
        assertEquals(0, players.get(0).getScore());
        assertEquals(0, players.get(1).getScore());
    }

    @Test
    void testSetCurrentQuestionIndex() {
        model.startGame();
        model.setCurrentQuestionIndex(1);
        assertEquals(1, model.getCurrentQuestionIndex());
        assertEquals(2, model.getCurrentQuestion().getId());
    }

    @Test
    void testSetCurrentQuestionIndexInvalid() {
        model.startGame();
        model.setCurrentQuestionIndex(10);
        assertEquals(0, model.getCurrentQuestionIndex());
    }

    @Test
    void testSetCurrentPlayerIndex() {
        model.startGame();
        model.setCurrentPlayerIndex(1);
        assertEquals(1, model.getCurrentPlayerIndex());
        assertEquals("Bob", model.getCurrentPlayer().getName());
    }

    @Test
    void testSetCurrentPlayerIndexInvalid() {
        model.startGame();
        model.setCurrentPlayerIndex(10);
        assertEquals(0, model.getCurrentPlayerIndex());
    }

    @Test
    void testAllPlayersEliminated() {
        model.startGame();
        assertFalse(model.allPlayersEliminated());

        players.get(0).setRemainingLives(0);
        players.get(1).setRemainingLives(0);
        assertTrue(model.allPlayersEliminated());
    }

    @Test
    void testGetActivePlayerCount() {
        model.startGame();
        assertEquals(2, model.getActivePlayerCount());

        players.get(0).setRemainingLives(0);
        assertEquals(1, model.getActivePlayerCount());

        players.get(1).setRemainingLives(0);
        assertEquals(0, model.getActivePlayerCount());
    }

    @Test
    void testEquality() {
        QuizModel model2 = new QuizModel(questions, players);
        assertEquals(model, model2);
    }

    @Test
    void testHashCode() {
        QuizModel model2 = new QuizModel(questions, players);
        assertEquals(model.hashCode(), model2.hashCode());
    }

    @Test
    void testToString() {
        String str = model.toString();
        assertTrue(str.contains("3"));
        assertTrue(str.contains("2"));
        assertTrue(str.contains("QuizModel"));
    }

    @Test
    void testGameFlow() {
        // Simulate a complete game flow
        assertTrue(model.startGame());

        // Alice answers first question
        assertTrue(model.answerQuestion(2));
        assertTrue(model.nextQuestion());

        // Alice answers second question
        assertFalse(model.answerQuestion(0));
        assertTrue(model.nextQuestion());

        // Alice answers third question
        assertTrue(model.answerQuestion(1));

        // Move to Bob
        assertTrue(model.nextPlayer());
        model.resetGameState();
        model.setCurrentPlayerIndex(1);

        // Bob answers questions
        assertFalse(model.answerQuestion(0));
        assertTrue(model.nextQuestion());

        // Verify results
        List<Player> sortedByScore = model.getPlayersByScore();
        assertEquals("Alice", sortedByScore.get(0).getName());
    }
}
