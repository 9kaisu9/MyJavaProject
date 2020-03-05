package de.dhbw.javaproject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class GamePane extends JPanel {

    private int targetNumber = 1;
    private static final int TARGET_SIZE = 50;

    private long lastTimer = System.currentTimeMillis();
    private Player player = new Player(285, 600, 50, 20, Ingredient.BUN);
    private List<Target> targets = new ArrayList<>();

    private Timer timer = new Timer(1, e -> {
        long now = System.currentTimeMillis();
        long diff = now - lastTimer;
        lastTimer = now;

        player.update(diff, getWidth());

        for (int i = targets.size() - 1; i >= 0; i--) {
            Target target = targets.get(i);
            target.update(diff, getHeight());
            if (player.intersects(target)) {
                targets.remove(i);
                player.increaseScore();
            }
            else if(target.notCaught(getHeight())) {
                targets.remove(i);
                player.decreaseScore();
            }
        }

        if (!(targets.size() == targetNumber)) {
            targets.add(createRandomTarget());
        }

    });

    private Target createRandomTarget() {
        Ingredient randomIngredient;
        int newTargetX = (int) (Math.random() * (getWidth() - TARGET_SIZE));
        int i = (int) Math.random() * 5;
        switch(i) {
            case 0: randomIngredient = Ingredient.BUN; break;
            case 1: randomIngredient = Ingredient.CHEESE; break;
            case 2: randomIngredient = Ingredient.LETTUCE; break;
            case 3: randomIngredient = Ingredient.PATTY; break;
            default: randomIngredient = Ingredient.TOMATO; break;
        }
        return new Target(newTargetX, 0, TARGET_SIZE, 20, randomIngredient);
    }

}
