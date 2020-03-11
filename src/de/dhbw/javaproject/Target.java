package de.dhbw.javaproject;

import java.awt.*;

public class Target extends GameObject {

    private Ingredient ingredient;
    private Movement movement = Movement.DOWN;

    public Target(double x, double y, int width, int height, Ingredient ingredient) {
        super(x, y, width, height);
        this.ingredient = ingredient;
    }

    public void update(long ms, double fallingSpeed) {
        this.y += movement.getDy() * ms * fallingSpeed;
    }

    public boolean notCaught(int yBound, int xBound) {
        GameObject floor = new GameObject(0, yBound, xBound, 60);
        return this.intersects(floor);
    }

    public void paint(Graphics g) {
        g.setColor(ingredient.getColor());
        g.fillRect((int)x, (int)y, width, height);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
