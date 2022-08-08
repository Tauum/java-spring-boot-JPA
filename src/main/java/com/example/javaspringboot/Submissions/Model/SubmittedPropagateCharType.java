package com.example.javaspringboot.Submissions.Model;

public enum SubmittedPropagateCharType {
    INCORRECT ("Staff"),
    ALMOST("Student"),
    CORRECT ("Undefined");

    private final String name;

    SubmittedPropagateCharType(String name) {
        this.name = name;
    }
}