package de.dhbw.javaproject;

/*Auflistung der 3 Bewegungsrichtungen (Movement) des Spielers + 1 Bewegungsrichtung der herunterfallenden Targets*/
public enum Movement {

    STOP(0,0),
    LEFT(-1,0),
    RIGHT(1,0),
    DOWN(0,1);

    private double dx;
    private double dy;

    Movement(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

}
