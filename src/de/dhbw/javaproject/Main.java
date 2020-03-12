package de.dhbw.javaproject;

import javax.swing.*;

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
