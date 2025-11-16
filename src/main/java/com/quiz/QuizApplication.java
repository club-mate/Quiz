package com.quiz;

import com.quiz.controller.QuizController;
import com.quiz.model.QuizModel;
import com.quiz.view.QuizView;

import javax.swing.*;

/**
 * Main entry point for the Quiz Application.
 * Initializes the MVC components and starts the application.
 */
public class QuizApplication {

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Use Event Dispatch Thread for thread safety in Swing
        SwingUtilities.invokeLater(() -> {
            // Create model
            QuizModel model = new QuizModel();

            // Create view
            QuizView view = new QuizView();

            // Create controller (binds model and view together)
            new QuizController(model, view);

            // Display the view
            view.setVisible(true);
        });
    }
}
