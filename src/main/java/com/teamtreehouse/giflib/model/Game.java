package com.teamtreehouse.giflib.model;

import java.util.List;

public class Game {
    private List<List<Card>> gameCards;
    private String starter;
    private int blueScore;
    private int redScore;
    private boolean gameOver;


    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<List<Card>> getGameCards() {
        return gameCards;
    }

    public void setGameCards(List<List<Card>> gameCards) {
        this.gameCards = gameCards;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public int getBlueScore() {
        return blueScore;
    }

    public void setBlueScore(int blueScore) {
        this.blueScore = blueScore;
    }

    public int getRedScore() {
        return redScore;
    }

    public void setRedScore(int redScore) {
        this.redScore = redScore;
    }
}