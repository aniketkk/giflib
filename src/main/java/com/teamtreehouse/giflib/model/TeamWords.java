package com.teamtreehouse.giflib.model;

import java.util.List;

public class TeamWords {
    private String currentTeam;
    private List<String> selectedWords;

    public String getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(String currentTeam) {
        this.currentTeam = currentTeam;
    }

    public List<String> getSelectedWords() {
        return selectedWords;
    }

    public void setSelectedWords(List<String> selectedWords) {
        this.selectedWords = selectedWords;
    }
}
