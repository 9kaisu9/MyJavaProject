package de.dhbw.javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class GamePane extends JPanel {

    private int targetNumber = 1;
    private static final int TARGET_WIDTH = 50;
    private static final int TARGET_HEIGHT = 20;

    private long lastTimer = System.currentTimeMillis();
    private Player player = new Player(0, 0, 50, 20, Ingredient.BUN);
    private List<Target> targets = new ArrayList<>();

    private Timer timer = new Timer(1, e -> {
        long now = System.currentTimeMillis();
        long diff = now - lastTimer;
        lastTimer = now;

        player.update(diff, getWidth());

        for (int i = targets.size() - 1; i >= 0; i--) {
            Target target = targets.get(i);
            target.update(diff, getHeight());

            System.out.println(getHeight());

            if (player.intersects(target)) {
                System.out.println(target.y);
                player.addIngedrient(target.getIngredient());
                player.increaseHeight(TARGET_HEIGHT);
                player.increaseScore();
                targets.remove(i);
                System.out.println("gefangen");
            }
            else if(target.notCaught(getHeight(), getWidth())) {
                player.decreaseScore();
                targets.remove(i);
                System.out.println("Boden");
            }
        }

        createMissingTargets();

        repaint();

    });

    private void createMissingTargets() {
        for (int i = 0; i < (targetNumber - targets.size()); i++) {
            targets.add(createRandomTarget());
        }
    }

    private Target createRandomTarget() {
        Ingredient randomIngredient;
        int newTargetX = (int) (Math.random() * (getWidth() - TARGET_WIDTH));
        int i = (int) (Math.random() * 5);
        switch(i) {
            case 0: randomIngredient = Ingredient.BUN; break;
            case 1: randomIngredient = Ingredient.CHEESE; break;
            case 2: randomIngredient = Ingredient.LETTUCE; break;
            case 3: randomIngredient = Ingredient.PATTY; break;
            default: randomIngredient = Ingredient.TOMATO; break;
        }
        return new Target(newTargetX, 0, TARGET_WIDTH, TARGET_HEIGHT, randomIngredient);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.drawString("" + player.getScore(), getWidth()/2, getHeight() / 2);

        player.paint(g, TARGET_HEIGHT, TARGET_WIDTH, getHeight());

        for (Target target : targets) {
            target.paint(g);
        }
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

    public GamePane() {
        setFocusable(true);
        addKeyListener(keyListener);
    }

    public void start() {
        timer.start();
        requestFocus();
        createMissingTargets();
    }

}
