package com.quiz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single quiz question with options and answer validation.
 *
 * This class encapsulates all information related to a quiz question including
 * the question text, multiple choice options, correct answer index, and metadata
 * like difficulty level and category.
 */
public class Question {
    private int id;
    private String text;
    private List<String> options;
    private int correctAnswerIndex;
    private String difficulty;
    private String category;

    /**
     * Constructs a Question with all fields.
     *
     * @param id the unique identifier for this question
     * @param text the question text
     * @param options list of answer options
     * @param correctAnswerIndex index of the correct answer (0-based)
     * @param difficulty difficulty level (easy, medium, hard)
     * @param category question category (Geography, Math, etc.)
     */
    public Question(int id, String text, List<String> options, int correctAnswerIndex,
                   String difficulty, String category) {
        this.id = id;
        this.text = text;
        this.options = new ArrayList<>(options);
        this.correctAnswerIndex = correctAnswerIndex;
        this.difficulty = difficulty;
        this.category = category;
    }

    /**
     * Constructs a Question with minimal fields.
     *
     * @param id the unique identifier for this question
     * @param text the question text
     * @param options list of answer options
     * @param correctAnswerIndex index of the correct answer
     */
    public Question(int id, String text, List<String> options, int correctAnswerIndex) {
        this(id, text, options, correctAnswerIndex, "medium", "General Knowledge");
    }

    /**
     * Default constructor for deserialization.
     */
    public Question() {
        this.options = new ArrayList<>();
        this.difficulty = "medium";
        this.category = "General Knowledge";
    }

    /**
     * Validates if the given answer index is correct.
     *
     * @param answerIndex the index of the selected answer
     * @return true if the answer is correct, false otherwise
     */
    public boolean isAnswerCorrect(int answerIndex) {
        return answerIndex >= 0 && answerIndex == correctAnswerIndex;
    }

    /**
     * Validates if the given answer index is within valid range.
     *
     * @param answerIndex the index to validate
     * @return true if the index is valid, false otherwise
     */
    public boolean isValidAnswerIndex(int answerIndex) {
        return answerIndex >= 0 && answerIndex < options.size();
    }

    /**
     * Gets the correct answer text.
     *
     * @return the text of the correct answer
     */
    public String getCorrectAnswer() {
        if (correctAnswerIndex < 0 || correctAnswerIndex >= options.size()) {
            return null;
        }
        return options.get(correctAnswerIndex);
    }

    /**
     * Gets the answer text at the given index.
     *
     * @param answerIndex the index of the answer
     * @return the answer text, or null if index is invalid
     */
    public String getAnswerOption(int answerIndex) {
        if (!isValidAnswerIndex(answerIndex)) {
            return null;
        }
        return options.get(answerIndex);
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfOptions() {
        return options.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
               correctAnswerIndex == question.correctAnswerIndex &&
               Objects.equals(text, question.text) &&
               Objects.equals(options, question.options) &&
               Objects.equals(difficulty, question.difficulty) &&
               Objects.equals(category, question.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, options, correctAnswerIndex, difficulty, category);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", correctAnswerIndex=" + correctAnswerIndex +
                ", difficulty='" + difficulty + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
