package com.quiz.model;

import java.io.Serializable;

/**
 * Represents a quiz player with their name and score.
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String netName;
    private int score;

    /**
     * Constructor for Player.
     *
     * @param name The player's name
     * @param netName The player's network name
     */
    public Player(String name, String netName) {
        this.name = name;
        this.netName = netName;
        this.score = 3; // Start with 3 lives ("Anzahl Leben")
    }

    /**
     * Default constructor.
     */
    public Player() {
        this.score = 3;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Decrease score by 1 (lose a life).
     */
    public void decreaseScore() {
        if (this.score > 0) {
            this.score--;
        }
    }

    /**
     * Increase score by 1 (gain a life).
     */
    public void increaseScore() {
        this.score++;
    }

    /**
     * Check if the player has lost all lives.
     *
     * @return true if score is 0, false otherwise
     */
    public boolean isEliminated() {
        return score <= 0;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", netName='" + netName + '\'' +
                ", score=" + score +
                '}';
    }
}
