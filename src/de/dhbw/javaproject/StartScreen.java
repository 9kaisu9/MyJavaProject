package de.dhbw.javaproject;

import javax.swing.*;

public class StartScreen {
    private JButton startButton;
    private JPanel panel1;

    /*Wird der Start-Button geklickt, wird die Funktion callback.startGame() ausgeführt, welche in der Main
    * implementiert wurde.*/
    public StartScreen(StartScreenCallback callback) {
        startButton.addActionListener(e -> callback.startGame());
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void createUIComponents() {

    }
}
