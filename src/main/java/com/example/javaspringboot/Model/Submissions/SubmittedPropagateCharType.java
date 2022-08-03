package com.example.javaspringboot.Model.Submissions;

public enum SubmittedPropagateCharType {
    INCORRECT ("Staff"),
    ALMOST("Student"),
    CORRECT ("Undefined");

    private final String name;

    SubmittedPropagateCharType(String name) {
        this.name = name;
    }
}