package de.dhbw.javaproject;

import java.awt.*;

public class GameObject {

    protected double x;
    protected double y;
    protected int width;
    protected int height;

    public GameObject(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(GameObject that) {
        Rectangle thisBounds = new Rectangle((int)this.x, (int)this.y, this.width, this.height);
        Rectangle thatBounds = new Rectangle((int)that.x, (int)that.y, that.width, that.height);

        return thisBounds.intersects(thatBounds);
    }

}
