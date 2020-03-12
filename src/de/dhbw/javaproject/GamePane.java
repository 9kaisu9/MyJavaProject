package de.dhbw.javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class GamePane extends JPanel {

    /*Deklarierung/Initialisierung der Variablen*/
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

    /*Initialisierung des Players in der unteren Mitte und der Liste der herunterfallenden Targets*/
    private Player player = new Player(285, 441, 50, 20);
    private List<Target> targets = new ArrayList<>();

    /*Timer, der jede Millisekunde durchlaufen wird*/
    private Timer timer = new Timer(1, e -> {

        long now = System.currentTimeMillis();
        /*Neues Target fällt herunter, sobald die Zeit timeBetweenTargets seit dem letzten Target abgelaufen ist.*/
        timeBetweenTargetsCounter = now - lastTarget;
        if (timeBetweenTargetsCounter > timeBetweenTargets) {
            createTarget();
        }

        /*Millisekundendifferenz, damit der Player/die Targets sich auf unterschiedlich schnellen Prozessoren
        * gleich schnell bewegen*/
        long diff = now - lastTimer;
        lastTimer = now;

        /*Bewegen des Players/Burgers*/
        player.update(diff, getWidth());

        /*Abwärtsbewegung der Targets
        * Hierbei wird geprüft, ob der Player ein valides Target (nach Burgerrezept) berührt oder nicht
        * und ob das Recipe fertiggestellt wurde */
        for (int i = targets.size() - 1; i >= 0; i--) {
            Target target = targets.get(i);
            target.update(diff, fallingSpeed);

            Ingredient validIngredient = levelRecipe.getIngredients().get(player.burgerSize());

            if (player.intersects(target)) {
                if (validIngredient == target.getIngredient()) {
                    /*Falls valides Target gefangen wurde, wird dem Player eine weitere Ingredient hinzugefügt*/
                    player.addIngedrient(target.getIngredient());
                    player.increaseHeight(TARGET_HEIGHT);
                    targets.remove(i);

                    /*falls Burger fertig*/
                    if (player.getBurgerIngredients().equals(levelRecipe.getIngredients())) {
                        level++;
                        levelRecipe = pickRandomRecipe();
                        fallingSpeed += 0.07;
                        player.burgerDone(TARGET_HEIGHT, 441);
                        timeBetweenTargets -= 100;
                    }
                } else {
                    /*Falls ein falsches Target gefangen wurde, hat der Spieler verloren*/
                    stopTimer();
                    this.setVisible(false);

                    LosingScreen losingScreen = new LosingScreen(() -> {
                        GamePane pane = new GamePane(f);
                        f.setContentPane(pane);
                        f.pack();
                        f.setSize(500, 500);
                        pane.start();
                    });
                    losingScreen.setLabelText(level, itemsLostScore);
                    f.setContentPane((losingScreen.getPanel1()));
                }
            } else if (target.notCaught(getHeight(), getWidth())) {
                /*Falls ein Target auf dem Boden aufkommt*/
                if (target.getIngredient() == validIngredient) {
                    itemsLostScore++;
                }
                targets.remove(i);
            }
        }
        /*Führt paintComponent aus*/
        repaint();
    });

    /*Falls der Spieler verloren hat muss der Timer gestoppt werden. Die Methode timer.stop() kann allerdings nicht
    * innerhalb des Timers ausgeführt werden.*/
    private void stopTimer() {
        timer.stop();
    }

    /*Fügt ein weiteres zufälliges Target der Targetliste hinzu*/
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

    /*Wählt ein zufälliges Recipe für levelRecipe*/
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

    /*Zeichnet die die einzelnen Komponenten*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*Recipe*/
        levelRecipe.paint(g, TARGET_HEIGHT, TARGET_WIDTH);
        g.setColor(new Color(0));
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString(levelRecipe.getName(), 390, 410);

        /*Level und verfehlte valide Targets*/
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Level: " + level, 15, 30);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("items lost: " + itemsLostScore, 15, 50);

        /*Burger*/
        player.paint(g, TARGET_HEIGHT, TARGET_WIDTH, getHeight());

        /*Target*/
        for (Target target : targets) {
            target.paint(g);
        }
    }

    /*Tastatureingabe der Pfeiltasten verändern das Movement des Players*/
    private KeyListener keyListener = new KeyListener() {
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
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyReleased(KeyEvent e) { }
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
