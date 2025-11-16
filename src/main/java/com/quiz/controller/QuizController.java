package com.quiz.controller;

import com.quiz.model.*;
import com.quiz.view.QuizView;
import com.quiz.view.QuestionEditorDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Main controller for the Quiz application.
 * Mediates between Model and View, handling all user interactions.
 */
public class QuizController implements ActionListener, QuizModel.ModelObserver {
    private QuizModel model;
    private QuizView view;
    private Question currentQuestion;

    /**
     * Constructor for QuizController.
     *
     * @param model The quiz model
     * @param view The quiz view
     */
    public QuizController(QuizModel model, QuizView view) {
        this.model = model;
        this.view = view;

        // Register as observer to model changes
        model.addObserver(this);

        // Add action listeners to view components
        view.addMenuListener(this);
        view.addButtonListener(this);

        // Initial setup
        updateViewFromModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // File Menu
        if (source == view.getNewGameItem()) {
            handleNewGame();
        } else if (source == view.getSaveItem()) {
            handleSave();
        } else if (source == view.getLoadItem()) {
            handleLoad();
        } else if (source == view.getExitItem()) {
            handleExit();
        }
        // Edit Menu
        else if (source == view.getEditQuestionsItem()) {
            handleEditQuestions();
        } else if (source == view.getSettingsItem()) {
            handleSettings();
        }
        // Help Menu
        else if (source == view.getHelpItem()) {
            handleHelp();
        } else if (source == view.getAboutItem()) {
            handleAbout();
        }
        // Buttons
        else if (source == view.getSubmitButton()) {
            handleSubmitAnswer();
        } else if (source == view.getNextButton()) {
            handleNextQuestion();
        }
    }

    /**
     * Handle new game initialization.
     */
    private void handleNewGame() {
        String[] playerInfo = view.showNewGameDialog();

        if (playerInfo != null && playerInfo.length == 4) {
            String player1Name = playerInfo[0].trim();
            String player1NetName = playerInfo[1].trim();
            String player2Name = playerInfo[2].trim();
            String player2NetName = playerInfo[3].trim();

            if (player1Name.isEmpty() || player2Name.isEmpty()) {
                view.showErrorDialog("Error", "Player names cannot be empty");
                return;
            }

            // Initialize the game
            model.initializeGame(player1Name, player1NetName, player2Name, player2NetName);
            view.showInfoDialog("Success", "Game initialized. Player 1 starts!");

            // Load a question from the first category
            loadRandomQuestion();
        }
    }

    /**
     * Load a random question from a category.
     */
    private void loadRandomQuestion() {
        if (model.getGame() == null) {
            view.showErrorDialog("Error", "Game not initialized");
            return;
        }

        // Try to get a question from the current category (first tab)
        String[] categories = model.getCategories();
        if (categories.length == 0) {
            view.showErrorDialog("Error", "No categories available");
            return;
        }

        int selectedTab = view.getCategoryTabs().getSelectedIndex();
        String selectedCategory = categories[Math.max(0, selectedTab)];

        currentQuestion = model.getRandomQuestion(selectedCategory);

        if (currentQuestion == null) {
            view.showErrorDialog("No Questions", "No questions available in category: " + selectedCategory);
            return;
        }

        view.displayQuestion(currentQuestion);
        view.clearSelection();
    }

    /**
     * Handle answer submission.
     */
    private void handleSubmitAnswer() {
        if (currentQuestion == null) {
            view.showErrorDialog("Error", "No question loaded");
            return;
        }

        int selectedIndex = view.getSelectedOptionIndex();
        if (selectedIndex == -1) {
            view.showErrorDialog("Error", "Please select an answer");
            return;
        }

        boolean isCorrect = model.submitAnswer(currentQuestion, selectedIndex);

        if (isCorrect) {
            view.showInfoDialog("Correct!", "Your answer is correct!");
        } else {
            Player currentPlayer = model.getCurrentPlayer();
            int remainingLives = currentPlayer.getScore();
            view.showInfoDialog("Incorrect", "Wrong answer! Lives remaining: " + remainingLives);

            // Check if game is over
            if (model.isGameOver()) {
                Player winner = model.getWinner();
                view.showInfoDialog("Game Over!", "Game Over! Winner: " + winner.getName());
                return;
            }
        }

        // Load next question
        loadRandomQuestion();
        view.clearSelection();
    }

    /**
     * Handle next question button.
     */
    private void handleNextQuestion() {
        if (model.getGame() == null) {
            view.showErrorDialog("Error", "Game not initialized");
            return;
        }

        // Switch to next player
        model.nextTurn();
        loadRandomQuestion();
        updateViewFromModel();
    }

    /**
     * Handle save game.
     */
    private void handleSave() {
        if (model.getGame() == null) {
            view.showErrorDialog("Error", "No game to save");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(view);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(model.getGame());
                view.showInfoDialog("Success", "Game saved successfully!");
            } catch (IOException e) {
                view.showErrorDialog("Error", "Failed to save game: " + e.getMessage());
            }
        }
    }

    /**
     * Handle load game.
     */
    private void handleLoad() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(view);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                QuizGame loadedGame = (QuizGame) ois.readObject();
                // Create a new model with the loaded game
                model.getGame().setPlayer1(loadedGame.getPlayer1());
                model.getGame().setPlayer2(loadedGame.getPlayer2());
                model.getGame().setCurrentPlayer(loadedGame.getCurrentPlayer());
                model.getGame().setCurrentQuestionIndex(loadedGame.getCurrentQuestionIndex());

                // Update view with loaded game
                updateViewFromModel();
                view.showInfoDialog("Success", "Game loaded successfully!");
                loadRandomQuestion();
            } catch (IOException | ClassNotFoundException e) {
                view.showErrorDialog("Error", "Failed to load game: " + e.getMessage());
            }
        }
    }

    /**
     * Handle exit.
     */
    private void handleExit() {
        if (view.showConfirmDialog("Exit", "Are you sure you want to exit?")) {
            System.exit(0);
        }
    }

    /**
     * Handle edit questions.
     */
    private void handleEditQuestions() {
        if (model.getGame() == null) {
            view.showErrorDialog("Error", "Please start a new game first");
            return;
        }

        String[] categories = model.getCategories();
        view.showQuestionEditor(categories);

        // Note: In a real application, you would get the question from the editor
        // and add it to the model. For now, this is a placeholder.
    }

    /**
     * Handle settings.
     */
    private void handleSettings() {
        view.showInfoDialog("Settings", "Settings dialog not yet implemented");
    }

    /**
     * Handle help.
     */
    private void handleHelp() {
        String helpText = "How to play:\n" +
                "1. Click 'New Game' to start\n" +
                "2. Enter player names\n" +
                "3. Select a category and answer questions\n" +
                "4. Each wrong answer costs a life\n" +
                "5. Player with remaining lives wins!";
        view.showInfoDialog("Help", helpText);
    }

    /**
     * Handle about.
     */
    private void handleAbout() {
        String aboutText = "Quiz Application v1.0\n" +
                "A two-player educational quiz game\n" +
                "Built with Java Swing";
        view.showInfoDialog("About", aboutText);
    }

    /**
     * Update the view to reflect current model state.
     */
    @Override
    public void modelChanged() {
        updateViewFromModel();
    }

    /**
     * Synchronize view with current model state.
     */
    private void updateViewFromModel() {
        if (model.getGame() != null) {
            Player player1 = model.getGame().getPlayer1();
            Player player2 = model.getGame().getPlayer2();
            Player currentPlayer = model.getGame().getCurrentPlayer();

            view.updateScores(player1, player2, currentPlayer);
        }
    }

    /**
     * Add a question to the model.
     *
     * @param question The question to add
     */
    public void addQuestion(Question question) {
        if (question != null) {
            model.addQuestion(question.getCategory(), question);
        }
    }
}
