package com.example.jasonhu.triviaapp;

import java.util.ArrayList;
import java.util.List;

public class QGroup {

    private String correctOption;
    private List<String> incorrectOptions;

    public QGroup(String correct) {
        correctOption = correct;
        incorrectOptions = new ArrayList<>();
    }

    public QGroup(String correct, List<String> incorrect) {
        correctOption = correct;
        incorrectOptions = incorrect;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public List<String> getIncorrectOptions() {
        return incorrectOptions;
    }

    public void add(String inCorrectOption) {
        incorrectOptions.add(inCorrectOption);
    }
}