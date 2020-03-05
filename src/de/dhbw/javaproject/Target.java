package de.dhbw.javaproject;

import java.awt.*;

public class Target extends GameObject {

    private Movement movement = Movement.DOWN;

    public Target(double x, double y, int width, int height, Ingredient ingredient) {
        super(x, y, width, height, ingredient);
    }

    public void update(long ms, int yBound) {
        this.y += movement.getDy() * ms * 0.20;
    }

    public boolean notCaught(int yBound) {
        return this.y == yBound - this.height;
    }

}
