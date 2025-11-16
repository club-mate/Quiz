package com.quiz.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Question class.
 */
class QuestionTest {
    private Question question;
    private List<String> options;

    @BeforeEach
    void setUp() {
        options = Arrays.asList("Option A", "Option B", "Option C", "Option D");
        question = new Question(1, "What is 2 + 2?", options, 2, "easy", "Math");
    }

    @Test
    void testQuestionCreation() {
        assertEquals(1, question.getId());
        assertEquals("What is 2 + 2?", question.getText());
        assertEquals(4, question.getNumberOfOptions());
        assertEquals(2, question.getCorrectAnswerIndex());
        assertEquals("easy", question.getDifficulty());
        assertEquals("Math", question.getCategory());
    }

    @Test
    void testMinimalQuestionCreation() {
        Question minimalQuestion = new Question(2, "Test question", options, 0);
        assertEquals(2, minimalQuestion.getId());
        assertEquals("Test question", minimalQuestion.getText());
        assertEquals("medium", minimalQuestion.getDifficulty());
        assertEquals("General Knowledge", minimalQuestion.getCategory());
    }

    @Test
    void testDefaultQuestionCreation() {
        Question defaultQuestion = new Question();
        assertEquals(0, defaultQuestion.getNumberOfOptions());
        assertEquals("medium", defaultQuestion.getDifficulty());
        assertEquals("General Knowledge", defaultQuestion.getCategory());
    }

    @Test
    void testIsAnswerCorrect() {
        assertTrue(question.isAnswerCorrect(2));
        assertFalse(question.isAnswerCorrect(0));
        assertFalse(question.isAnswerCorrect(1));
        assertFalse(question.isAnswerCorrect(3));
    }

    @Test
    void testIsAnswerCorrectWithNegativeIndex() {
        assertFalse(question.isAnswerCorrect(-1));
    }

    @Test
    void testIsAnswerCorrectWithOutOfBoundsIndex() {
        assertFalse(question.isAnswerCorrect(4));
    }

    @Test
    void testIsValidAnswerIndex() {
        assertTrue(question.isValidAnswerIndex(0));
        assertTrue(question.isValidAnswerIndex(1));
        assertTrue(question.isValidAnswerIndex(2));
        assertTrue(question.isValidAnswerIndex(3));
        assertFalse(question.isValidAnswerIndex(4));
        assertFalse(question.isValidAnswerIndex(-1));
    }

    @Test
    void testGetCorrectAnswer() {
        assertEquals("Option C", question.getCorrectAnswer());
    }

    @Test
    void testGetAnswerOption() {
        assertEquals("Option A", question.getAnswerOption(0));
        assertEquals("Option B", question.getAnswerOption(1));
        assertEquals("Option C", question.getAnswerOption(2));
        assertEquals("Option D", question.getAnswerOption(3));
    }

    @Test
    void testGetAnswerOptionInvalidIndex() {
        assertNull(question.getAnswerOption(4));
        assertNull(question.getAnswerOption(-1));
    }

    @Test
    void testSetText() {
        question.setText("New question text");
        assertEquals("New question text", question.getText());
    }

    @Test
    void testSetOptions() {
        List<String> newOptions = Arrays.asList("New A", "New B", "New C");
        question.setOptions(newOptions);
        assertEquals(3, question.getNumberOfOptions());
        assertEquals("New A", question.getAnswerOption(0));
    }

    @Test
    void testSetCorrectAnswerIndex() {
        question.setCorrectAnswerIndex(1);
        assertEquals(1, question.getCorrectAnswerIndex());
        assertTrue(question.isAnswerCorrect(1));
        assertFalse(question.isAnswerCorrect(2));
    }

    @Test
    void testSetDifficulty() {
        question.setDifficulty("hard");
        assertEquals("hard", question.getDifficulty());
    }

    @Test
    void testSetCategory() {
        question.setCategory("History");
        assertEquals("History", question.getCategory());
    }

    @Test
    void testEquality() {
        Question question2 = new Question(1, "What is 2 + 2?", options, 2, "easy", "Math");
        assertEquals(question, question2);
    }

    @Test
    void testInequalityDifferentId() {
        Question question2 = new Question(2, "What is 2 + 2?", options, 2, "easy", "Math");
        assertNotEquals(question, question2);
    }

    @Test
    void testInequalityDifferentText() {
        Question question2 = new Question(1, "What is 3 + 3?", options, 2, "easy", "Math");
        assertNotEquals(question, question2);
    }

    @Test
    void testHashCode() {
        Question question2 = new Question(1, "What is 2 + 2?", options, 2, "easy", "Math");
        assertEquals(question.hashCode(), question2.hashCode());
    }

    @Test
    void testToString() {
        String str = question.toString();
        assertTrue(str.contains("1"));
        assertTrue(str.contains("What is 2 + 2?"));
        assertTrue(str.contains("Math"));
    }

    @Test
    void testGetOptionsReturnsNewList() {
        List<String> retrievedOptions = question.getOptions();
        retrievedOptions.clear();
        assertEquals(4, question.getNumberOfOptions());
    }

    @Test
    void testGetCorrectAnswerWithInvalidIndex() {
        Question invalidQuestion = new Question(1, "Test", options, 10, "easy", "Test");
        assertNull(invalidQuestion.getCorrectAnswer());
    }
}
