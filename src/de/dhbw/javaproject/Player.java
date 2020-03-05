package de.dhbw.javaproject;

public class Player extends GameObject {


    //COLORLIST???? damit mehrere Zutaten gepainted werden können und nicht nur ein Rechteck (dann mit for-Schleife)
    private int level;
    private int score;
    private Movement movement = Movement.STOP;

    public Player(double x, double y, int width, int height, Ingredient ingredient) {
        super(x, y, width, height, ingredient);
    }

    public int getScore() {
        return score;
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

}
