package com.example.javaspringboot.Model.Additional;

public enum AnimationType {

    UNDEFINED ("Undefined"),
    DEFAULT("Default"),
    STARS("Stars"),
    SMILES("Smiles"),
    FROWNS("Frowns"),
    FIREWORKS("Fireworks"),
    RANDOM("Random");

    private final String name;

    AnimationType(String name) {
        this.name = name;
    }
}