package de.dhbw.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Recipe {
    HAMBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.PATTY, Ingredient.ONION, Ingredient.BUN))),
    CHEESEBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.PATTY, Ingredient.CHEESE, Ingredient.ONION, Ingredient.BUN))),
    ROYALEBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.PATTY, Ingredient.CHEESE, Ingredient.LETTUCE, Ingredient.TOMATO, Ingredient.BUN))),
    TOWERBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.PATTY, Ingredient.ONION, Ingredient.BUN, Ingredient.PATTY, Ingredient.ONION, Ingredient.BUN, Ingredient.PATTY, Ingredient.ONION, Ingredient.BUN))),
    VEGGIEBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.CHEESE, Ingredient.CHEESE, Ingredient.LETTUCE, Ingredient.TOMATO, Ingredient.ONION, Ingredient.BUN))),
    VEGANBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.LETTUCE, Ingredient.LETTUCE, Ingredient.LETTUCE, Ingredient.TOMATO, Ingredient.ONION, Ingredient.BUN)));


    private List<Ingredient> ingredients;

    Recipe(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void paint(Graphics g, int targetHeight, int targetWidth) {

        for (int i = ingredients.size() - 1; i >= 0; i--) {
            g.setColor(ingredients.get(i).getColor());
            g.fillRect(400, 400 - targetHeight * (i + 1), targetWidth, targetHeight);
        }
    }
}
