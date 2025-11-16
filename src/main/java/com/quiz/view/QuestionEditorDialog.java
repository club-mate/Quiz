package com.quiz.view;

import com.quiz.model.Question;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dialog for editing and adding quiz questions.
 */
public class QuestionEditorDialog extends JDialog {
    private static final long serialVersionUID = 1L;

    private JComboBox<String> categoryCombo;
    private JTextArea questionArea;
    private JTextField[] optionFields;
    private JSpinner correctAnswerSpinner;
    private JButton addButton, cancelButton;
    private Question newQuestion;

    /**
     * Constructor for QuestionEditorDialog.
     *
     * @param parent The parent frame
     * @param categories The available categories
     */
    public QuestionEditorDialog(JFrame parent, String[] categories) {
        super(parent, "Edit Questions", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        initializeComponents(categories);
        setupLayout();
    }

    /**
     * Initialize all components.
     *
     * @param categories The available categories
     */
    private void initializeComponents(String[] categories) {
        // Category selection
        categoryCombo = new JComboBox<>(categories);

        // Question text area
        JLabel questionLabel = new JLabel("Question:");
        questionArea = new JTextArea(3, 40);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        JScrollPane questionScroll = new JScrollPane(questionArea);

        // Option fields
        optionFields = new JTextField[4];
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        for (int i = 0; i < 4; i++) {
            optionFields[i] = new JTextField(30);
            JLabel optionLabel = new JLabel("Option " + (i + 1) + ":");
            JPanel optionRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            optionRowPanel.add(optionLabel);
            optionRowPanel.add(optionFields[i]);
            optionsPanel.add(optionRowPanel);
        }

        // Correct answer selection
        correctAnswerSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
        JPanel answerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        answerPanel.add(new JLabel("Correct Answer (0-3):"));
        answerPanel.add(correctAnswerSpinner);

        // Buttons
        addButton = new JButton("Add Question");
        cancelButton = new JButton("Cancel");

        // Main layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("Category:"));
        mainPanel.add(categoryCombo);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(questionLabel);
        mainPanel.add(questionScroll);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JLabel("Options:"));
        mainPanel.add(optionsPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(answerPanel);
        mainPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel);

        setContentPane(mainPanel);

        // Event listeners
        addButton.addActionListener(e -> createQuestion());
        cancelButton.addActionListener(e -> dispose());
    }

    /**
     * Setup the layout.
     */
    private void setupLayout() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * Create a question from the current input.
     */
    private void createQuestion() {
        String category = (String) categoryCombo.getSelectedItem();
        String text = questionArea.getText().trim();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a question", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> options = new ArrayList<>();
        for (JTextField field : optionFields) {
            String option = field.getText().trim();
            if (option.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all options", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            options.add(option);
        }

        int correctIndex = (int) correctAnswerSpinner.getValue();
        newQuestion = new Question(category, text, options, correctIndex);

        JOptionPane.showMessageDialog(this, "Question added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
    }

    /**
     * Clear all input fields.
     */
    private void clearFields() {
        questionArea.setText("");
        for (JTextField field : optionFields) {
            field.setText("");
        }
        correctAnswerSpinner.setValue(0);
    }

    /**
     * Get the newly created question.
     *
     * @return The created question, or null if none created
     */
    public Question getNewQuestion() {
        return newQuestion;
    }

    /**
     * Reset the new question variable.
     */
    public void resetNewQuestion() {
        newQuestion = null;
    }
}
