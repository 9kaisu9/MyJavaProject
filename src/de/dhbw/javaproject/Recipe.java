package de.dhbw.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Auflistung der verschiedenen Rezepte (Recipe). Diese besitzen jeweils eine Ingredientliste + Namen*/
public enum Recipe {
    HAMBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.PATTY, Ingredient.ONION, Ingredient.BUN)), "Hamburger"),
    CHEESEBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.PATTY, Ingredient.CHEESE, Ingredient.ONION, Ingredient.BUN)), "Cheeseburger"),
    ROYALEBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.PATTY, Ingredient.CHEESE, Ingredient.LETTUCE, Ingredient.TOMATO, Ingredient.BUN)), "Royaleburger"),
    TOWERBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.PATTY, Ingredient.ONION, Ingredient.BUN, Ingredient.PATTY, Ingredient.ONION, Ingredient.BUN, Ingredient.PATTY, Ingredient.ONION, Ingredient.BUN)), "Towerburger"),
    VEGGIEBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.CHEESE, Ingredient.CHEESE, Ingredient.LETTUCE, Ingredient.TOMATO, Ingredient.ONION, Ingredient.BUN)), "Veggieburger"),
    VEGANBURGER(new ArrayList<>(Arrays.asList(Ingredient.BUN, Ingredient.LETTUCE, Ingredient.LETTUCE, Ingredient.LETTUCE, Ingredient.TOMATO, Ingredient.ONION, Ingredient.BUN)), "Veganburger");

    private String name;
    private List<Ingredient> ingredients;

    /*Iterativ werden die einzelnen Ingredients des Burgers von oben nach unten aufgemalt*/
    public void paint(Graphics g, int targetHeight, int targetWidth) {
        for (int i = ingredients.size() - 1; i >= 0; i--) {
            g.setColor(ingredients.get(i).getColor());
            g.fillRect(400, 400 - targetHeight * (i + 1), targetWidth, targetHeight);
        }
    }

    Recipe(List<Ingredient> ingredients, String name) {
        this.ingredients = ingredients;
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }
}
