package de.dhbw.javaproject;

import javax.swing.*;

/*Das Spiel wurde durch das Spiel Skyburger inspiriert. Dabei geht es darum einen Burger (Player) nach einem Rezept (Recipe)
* nachzubauen. Der Spieler steuert dabei das bewegliche Brot und fängt dabei die Zutaten (Target), die vom Himmel fallen.
* Wenn das Rezept nachgebaut wurde, wird das Level schwerer, indem die Zutaten schneller herunterfallen. Wird eine falsche
* Zutat gefangen, hat der Spieler verloren
*
* Hier wird das Programm gestartet und der StartScreen im JFrame aufgerufen. Bei Klick auf den Start-Knopf wird das GamePane
* initialisiert und das eigentliche Spiel beginnt.*/
public class Main {

    public static void main(String[] args) {

        JFrame f = new JFrame();

        f.setSize(500, 500);
        StartScreen startScreen = new StartScreen(() -> {
            GamePane pane = new GamePane(f);
            f.setContentPane(pane);
            f.pack();
            f.setSize(500, 500);
            pane.start();
        });
        f.setContentPane((startScreen.getPanel1()));
        f.setVisible(true);
    }
}
