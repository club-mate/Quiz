package com.quiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a quiz question with multiple choice options.
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private String category;
    private String text;
    private List<String> options;
    private int correctOptionIndex;

    /**
     * Constructor for Question.
     *
     * @param category The category of the question
     * @param text The question text
     * @param options List of answer options
     * @param correctOptionIndex Index of the correct option (0-based)
     */
    public Question(String category, String text, List<String> options, int correctOptionIndex) {
        this.category = category;
        this.text = text;
        this.options = new ArrayList<>(options);
        this.correctOptionIndex = correctOptionIndex;
    }

    /**
     * Default constructor for deserialization.
     */
    public Question() {
        this.options = new ArrayList<>();
    }

    // Getters and setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return new ArrayList<>(options);
    }

    public void setOptions(List<String> options) {
        this.options = new ArrayList<>(options);
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

    /**
     * Check if the given answer is correct.
     *
     * @param answerIndex The index of the selected answer
     * @return true if the answer is correct, false otherwise
     */
    public boolean isCorrectAnswer(int answerIndex) {
        return answerIndex == correctOptionIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "category='" + category + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", correctOptionIndex=" + correctOptionIndex +
                '}';
    }
}
