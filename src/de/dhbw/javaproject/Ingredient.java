package de.dhbw.javaproject;

import java.awt.*;

/*Auflistung der 6 verschiedenen Zutaten (Ingredients), die eine zugehörige RGB-Farbe besitzen*/
public enum Ingredient {
    TOMATO(new Color (238, 0, 0)),
    PATTY(new Color(139, 69, 19)),
    LETTUCE(new Color(0, 205, 0)),
    CHEESE(new Color(255, 215, 0)),
    BUN(new Color(255, 140, 0)),
    ONION(new Color(192, 192, 192));

    private Color color;

    Ingredient(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
