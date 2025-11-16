package com.quiz.view;

import com.quiz.model.Question;
import com.quiz.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Main view for the Quiz application.
 * Implements the GUI using Swing with BorderLayout.
 */
public class QuizView extends JFrame {
    private static final long serialVersionUID = 1L;

    // Menu components
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, viewMenu, helpMenu;
    private JMenuItem newGameItem, saveItem, loadItem, exitItem;
    private JMenuItem editQuestionsItem, settingsItem;
    private JMenuItem aboutItem, helpItem;

    // Main panel with BorderLayout
    private JPanel mainPanel;

    // North panel - Menu area (already handled by JMenuBar)
    // Center panel - Question display and tabs
    private JTabbedPane categoryTabs;
    private JPanel questionPanel;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton submitButton;
    private JButton nextButton;

    // South panel - Score display
    private JPanel scorePanel;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel currentPlayerLabel;

    // Question editor
    private QuestionEditorDialog editorDialog;

    /**
     * Constructor for QuizView.
     */
    public QuizView() {
        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initializeComponents();
        setupLayout();
    }

    /**
     * Initialize all GUI components.
     */
    private void initializeComponents() {
        // Initialize menu bar
        menuBar = new JMenuBar();

        // File Menu
        fileMenu = new JMenu("File");
        newGameItem = new JMenuItem("New Game");
        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");
        exitItem = new JMenuItem("Exit");
        fileMenu.add(newGameItem);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Edit Menu
        editMenu = new JMenu("Edit");
        editQuestionsItem = new JMenuItem("Edit Questions");
        settingsItem = new JMenuItem("Settings");
        editMenu.add(editQuestionsItem);
        editMenu.addSeparator();
        editMenu.add(settingsItem);

        // View Menu
        viewMenu = new JMenu("View");

        // Help Menu
        helpMenu = new JMenu("Help");
        helpItem = new JMenuItem("Help");
        aboutItem = new JMenuItem("About");
        helpMenu.add(helpItem);
        helpMenu.addSeparator();
        helpMenu.add(aboutItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // Initialize category tabs
        categoryTabs = new JTabbedPane();
        String[] categories = {"BWL", "Digitaltechnik", "Elektrotechnik",
                               "Netzwerktechnik", "Datenbanken", "Programmierung"};
        for (String category : categories) {
            JPanel categoryPanel = new JPanel();
            categoryPanel.setLayout(new FlowLayout());
            JLabel label = new JLabel(category);
            categoryPanel.add(label);
            categoryTabs.addTab(category, categoryPanel);
        }

        // Initialize question panel
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        questionLabel = new JLabel("Select a category to start");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        questionPanel.add(questionLabel);
        questionPanel.add(Box.createVerticalStrut(10));

        // Initialize option buttons (4 options for multiple choice)
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton("Option " + (i + 1));
            optionGroup.add(optionButtons[i]);
            questionPanel.add(optionButtons[i]);
        }

        questionPanel.add(Box.createVerticalStrut(20));

        // Initialize buttons
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit Answer");
        nextButton = new JButton("Next Question");
        buttonPanel.add(submitButton);
        buttonPanel.add(nextButton);
        questionPanel.add(buttonPanel);

        // Initialize score panel
        scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout());
        scorePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        player1Label = new JLabel("Player 1: 0");
        player2Label = new JLabel("Player 2: 0");
        currentPlayerLabel = new JLabel("Current Player: None");

        scorePanel.add(player1Label);
        scorePanel.add(Box.createHorizontalStrut(20));
        scorePanel.add(player2Label);
        scorePanel.add(Box.createHorizontalStrut(20));
        scorePanel.add(currentPlayerLabel);
    }

    /**
     * Setup the layout using BorderLayout.
     */
    private void setupLayout() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Center: question display and category tabs
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(categoryTabs, BorderLayout.NORTH);
        centerPanel.add(questionPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(scorePanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    // Getter methods for controllers
    public JMenuItem getNewGameItem() {
        return newGameItem;
    }

    public JMenuItem getSaveItem() {
        return saveItem;
    }

    public JMenuItem getLoadItem() {
        return loadItem;
    }

    public JMenuItem getExitItem() {
        return exitItem;
    }

    public JMenuItem getEditQuestionsItem() {
        return editQuestionsItem;
    }

    public JMenuItem getSettingsItem() {
        return settingsItem;
    }

    public JMenuItem getHelpItem() {
        return helpItem;
    }

    public JMenuItem getAboutItem() {
        return aboutItem;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JTabbedPane getCategoryTabs() {
        return categoryTabs;
    }

    /**
     * Display a question and its options.
     *
     * @param question The question to display
     */
    public void displayQuestion(Question question) {
        if (question == null) {
            questionLabel.setText("No questions available");
            for (JRadioButton button : optionButtons) {
                button.setText("");
                button.setEnabled(false);
            }
            return;
        }

        questionLabel.setText(question.getText());
        List<String> options = question.getOptions();

        for (int i = 0; i < optionButtons.length; i++) {
            if (i < options.size()) {
                optionButtons[i].setText(options.get(i));
                optionButtons[i].setEnabled(true);
                optionButtons[i].setSelected(false);
            } else {
                optionButtons[i].setText("");
                optionButtons[i].setEnabled(false);
            }
        }

        optionGroup.clearSelection();
    }

    /**
     * Get the index of the selected option.
     *
     * @return The index of the selected option, or -1 if none selected
     */
    public int getSelectedOptionIndex() {
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Clear the selection of options.
     */
    public void clearSelection() {
        optionGroup.clearSelection();
    }

    /**
     * Update the score display for both players.
     *
     * @param player1 First player
     * @param player2 Second player
     * @param currentPlayer Current player
     */
    public void updateScores(Player player1, Player player2, Player currentPlayer) {
        if (player1 != null) {
            player1Label.setText(player1.getName() + ": " + player1.getScore());
        }
        if (player2 != null) {
            player2Label.setText(player2.getName() + ": " + player2.getScore());
        }
        if (currentPlayer != null) {
            currentPlayerLabel.setText("Current Player: " + currentPlayer.getName());
        }
    }

    /**
     * Show an information message dialog.
     *
     * @param title The dialog title
     * @param message The message to display
     */
    public void showInfoDialog(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show an error message dialog.
     *
     * @param title The dialog title
     * @param message The message to display
     */
    public void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show a confirmation dialog.
     *
     * @param title The dialog title
     * @param message The message to display
     * @return true if user clicks "Yes", false otherwise
     */
    public boolean showConfirmDialog(String title, String message) {
        int result = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Show a new game setup dialog.
     *
     * @return An array of [player1Name, player1NetName, player2Name, player2NetName], or null if cancelled
     */
    public String[] showNewGameDialog() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField player1NameField = new JTextField();
        JTextField player1NetField = new JTextField();
        JTextField player2NameField = new JTextField();
        JTextField player2NetField = new JTextField();

        panel.add(new JLabel("Player 1 Name:"));
        panel.add(player1NameField);
        panel.add(new JLabel("Player 1 Net Name:"));
        panel.add(player1NetField);
        panel.add(new JLabel("Player 2 Name:"));
        panel.add(player2NameField);
        panel.add(new JLabel("Player 2 Net Name:"));
        panel.add(player2NetField);

        int result = JOptionPane.showConfirmDialog(this, panel, "New Game",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            return new String[]{
                player1NameField.getText(),
                player1NetField.getText(),
                player2NameField.getText(),
                player2NetField.getText()
            };
        }
        return null;
    }

    /**
     * Show the question editor dialog.
     *
     * @param categories Available question categories
     */
    public void showQuestionEditor(String[] categories) {
        if (editorDialog == null) {
            editorDialog = new QuestionEditorDialog(this, categories);
        }
        editorDialog.setVisible(true);
    }

    /**
     * Add action listener to menu items.
     *
     * @param listener The action listener
     */
    public void addMenuListener(ActionListener listener) {
        newGameItem.addActionListener(listener);
        saveItem.addActionListener(listener);
        loadItem.addActionListener(listener);
        exitItem.addActionListener(listener);
        editQuestionsItem.addActionListener(listener);
        settingsItem.addActionListener(listener);
        helpItem.addActionListener(listener);
        aboutItem.addActionListener(listener);
    }

    /**
     * Add action listener to buttons.
     *
     * @param listener The action listener
     */
    public void addButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
        nextButton.addActionListener(listener);
    }
}
