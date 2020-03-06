package de.dhbw.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {

    private int level;
    private int score;
    private Movement movement = Movement.STOP;
    private List<Ingredient> burgerIngredients = new ArrayList<>();

    public Player(double x, double y, int width, int height, Ingredient ingredient) {
        super(x, y, width, height);
        addIngedrient(ingredient);
    }

    public int getScore() {
        return score;
    }

    public void increaseHeight(int height) {
        this.height += height;
        this.y -= height;
    }

    public void increaseScore() {
        score++;
    }

    public void decreaseScore() {
        score--;
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

}
