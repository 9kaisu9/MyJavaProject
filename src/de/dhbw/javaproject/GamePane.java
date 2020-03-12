package de.dhbw.javaproject;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GamePane extends JPanel {

    private JFrame f;
    private static final int TARGET_WIDTH = 50;
    private static final int TARGET_HEIGHT = 20;
    private int itemsLostScore = 0;
    private int level = 1;
    private Recipe levelRecipe = pickRandomRecipe();
    private long timeBetweenTargets = 1000;
    private long timeBetweenTargetsCounter;
    private long lastTarget;
    private double fallingSpeed = 0.20;

    private long lastTimer = System.currentTimeMillis();
    private Player player = new Player(285, 441, 50, 20);
    private List<Target> targets = new ArrayList<>();

    private Timer timer = new Timer(1, e -> {

        long now = System.currentTimeMillis();

        timeBetweenTargetsCounter = now - lastTarget;
        if (timeBetweenTargetsCounter > timeBetweenTargets) {
            createTarget();
        }

        long diff = now - lastTimer;
        lastTimer = now;

        player.update(diff, getWidth());

        for (int i = targets.size() - 1; i >= 0; i--) {
            Target target = targets.get(i);
            target.update(diff, fallingSpeed);

            Ingredient validIngredient = levelRecipe.getIngredients().get(player.burgerSize());

            if (player.intersects(target)) {
                 if (validIngredient == target.getIngredient()) {
                    player.addIngedrient(target.getIngredient());
                    player.increaseHeight(TARGET_HEIGHT);

                    targets.remove(i);
                    if (player.getBurgerIngredients().equals(levelRecipe.getIngredients())) {
                        level++;
                        levelRecipe = pickRandomRecipe();
                        fallingSpeed += 0.07;
                        player.burgerDone(TARGET_HEIGHT, 441);
                        timeBetweenTargets -= 100;
                    }
                } else {
                    resetGame();
                    this.setVisible(false);

                    StartScreen startScreen = new StartScreen(() -> {
                        GamePane pane = new GamePane(f);
                        f.setContentPane(pane);
                        f.pack();
                        f.setSize(500, 500);
                        pane.start();
                    });
                    f.setContentPane((startScreen.getPanel1()));
                }
                System.out.println("gefangen");
            } else if (target.notCaught(getHeight(), getWidth())) {
                if (target.getIngredient() == validIngredient) {
                    itemsLostScore++;
                }
                targets.remove(i);
                System.out.println("Boden");
            }
        }

        repaint();

    });

    private void resetGame() {
        timer.stop();

/*      System.out.println("blyat");
        targets.clear();
        levelRecipe = pickRandomRecipe();
        level = 1;
        itemsLostScore = 0;*/
    }

    private void createTarget() {
        targets.add(createRandomTarget());
        lastTarget = System.currentTimeMillis();
    }

    private Target createRandomTarget() {
        Ingredient randomIngredient;
        int newTargetX = (int) (Math.random() * (getWidth() - TARGET_WIDTH));
        int i = (int) (Math.random() * 6);
        switch (i) {
            case 0:
                randomIngredient = Ingredient.BUN;
                break;
            case 1:
                randomIngredient = Ingredient.CHEESE;
                break;
            case 2:
                randomIngredient = Ingredient.LETTUCE;
                break;
            case 3:
                randomIngredient = Ingredient.PATTY;
                break;
            case 4:
                randomIngredient = Ingredient.ONION;
                break;
            default:
                randomIngredient = Ingredient.TOMATO;
                break;
        }
        return new Target(newTargetX, 0, TARGET_WIDTH, TARGET_HEIGHT, randomIngredient);
    }

    private Recipe pickRandomRecipe() {
        Recipe randomRecipe;
        int i = (int) (Math.random() * 6);
        switch (i) {
            case 0:
                randomRecipe = Recipe.ROYALEBURGER;
                break;
            case 1:
                randomRecipe = Recipe.CHEESEBURGER;
                break;
            case 2:
                randomRecipe = Recipe.HAMBURGER;
                break;
            case 3:
                randomRecipe = Recipe.TOWERBURGER;
                break;
            case 4:
                randomRecipe = Recipe.VEGANBURGER;
                break;
            default:
                randomRecipe = Recipe.VEGGIEBURGER;
                break;
        }
        return randomRecipe;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("" + level, getWidth() / 2, getHeight() / 2);

        //braucht Überarbeitung
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("items lost: " + itemsLostScore, getWidth() / 3, getHeight() / 2);


        player.paint(g, TARGET_HEIGHT, TARGET_WIDTH, getHeight());

        for (Target target : targets) {
            target.paint(g);
        }

        levelRecipe.paint(g, TARGET_HEIGHT, TARGET_WIDTH);
    }

    private KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    player.move(Movement.RIGHT);
                    break;
                case KeyEvent.VK_LEFT:
                    player.move(Movement.LEFT);
                    break;
                case KeyEvent.VK_DOWN:
                    player.move(Movement.STOP);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };

    public GamePane(JFrame f) {
        this.f = f;
        setFocusable(true);
        addKeyListener(keyListener);
    }

    public void start() {
        timer.start();
        requestFocus();
        createTarget();
    }
}
