package de.dhbw.javaproject;

import java.awt.*;

public class Target extends GameObject {

    private Ingredient ingredient;
    private Movement movement = Movement.DOWN;

    public Target(double x, double y, int width, int height, Ingredient ingredient) {
        super(x, y, width, height);
        this.ingredient = ingredient;
    }

    /*Bewegung des Targets in Abhängigkeit vom fallingSpeed*/
    public void update(long ms, double fallingSpeed) {
        this.y += movement.getDy() * ms * fallingSpeed;
    }

    /*Fragt ab, ob das Target den Boden (floor) berührt*/
    public boolean notCaught(int yBound, int xBound) {
        GameObject floor = new GameObject(0, yBound, xBound, 60);
        return this.intersects(floor);
    }

    /*Zeichnet das Target in der Farbe der zugehörigen Ingredient*/
    public void paint(Graphics g) {
        g.setColor(ingredient.getColor());
        g.fillRect((int)x, (int)y, width, height);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
