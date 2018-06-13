package com.teamtreehouse.giflib.model;

public class Card {
    private int id;
    private String value;
    private String color;
    private boolean guessed;

    public Card(int id, String value, String color) {
        this.id = id;
        this.value = value;
        this.color = color;
        this.guessed = false;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }
}
