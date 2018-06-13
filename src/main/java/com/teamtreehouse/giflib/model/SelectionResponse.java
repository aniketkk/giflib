package com.teamtreehouse.giflib.model;

import java.util.List;

public class SelectionResponse {
    int correctBlueAnswers;
    int correctRedAnswers;
    List<List<Card>> selectedCards;

    public int getCorrectBlueAnswers() {
        return correctBlueAnswers;
    }

    public void setCorrectBlueAnswers(int correctBlueAnswers) {
        this.correctBlueAnswers = correctBlueAnswers;
    }

    public int getCorrectRedAnswers() {
        return correctRedAnswers;
    }

    public void setCorrectRedAnswers(int correctRedAnswers) {
        this.correctRedAnswers = correctRedAnswers;
    }

    public List<List<Card>> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(List<List<Card>> selectedCards) {
        this.selectedCards = selectedCards;
    }
}

