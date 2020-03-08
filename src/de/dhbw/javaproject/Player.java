package de.dhbw.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {

    private int level;
    //private int score;
    private Movement movement = Movement.STOP;
    private List<Ingredient> burgerIngredients = new ArrayList<>();

    public Player(double x, double y, int width, int height) {
        super(x, y, width, height);
        addIngedrient(Ingredient.BUN);
    }
/*
    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public void decreaseScore() {
        score--;
    }

 */

    public void increaseHeight(int height) {
        this.height += height;
        this.y -= height;
    }

    public void update(long ms, int xBound) {
        this.x += movement.getDx() * ms * 0.25;

        if(x < 0) {
            x = xBound - width;
        }
        if(x + width > xBound) {
            x = 0;
        }

    }

    public void addIngedrient(Ingredient ingredient) {
        burgerIngredients.add(ingredient);
    }

    public void paint(Graphics g, int targetHeight, int targetWidth, int yBound) {

        for (int i = burgerIngredients.size() - 1; i >= 0; i--) {
            g.setColor(burgerIngredients.get(i).getColor());
            g.fillRect((int)x, yBound - targetHeight * (i + 1), targetWidth, targetHeight);
        }
    }

    public void move(Movement movement) {
        this.movement = movement;
    }

    public void clearIngredients() {
        burgerIngredients.clear();
        burgerIngredients.add(Ingredient.BUN);
    }

    public int burgerSize() {
        return burgerIngredients.size();
    }

    public List<Ingredient> getBurgerIngredients() {
        return burgerIngredients;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void burgerDone(int targetHeight, double y) {
        clearIngredients();
        setHeight(targetHeight);
        setY(y);
    }
}
