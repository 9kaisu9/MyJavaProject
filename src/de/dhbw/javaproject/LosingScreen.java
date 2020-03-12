package de.dhbw.javaproject;

import javax.swing.*;

public class LosingScreen {
    private JPanel panel1;
    private JButton restartButton;
    private javax.swing.JLabel JLabel;

    /*Endergebnisanzeige*/
    public void setLabelText(int level, int itemsLostScore) {
        JLabel.setText("Level: " + level + " mit " + itemsLostScore + " verlorenen Items");
    }

    /*Wird der Restart-Button geklickt, wird die Funktion callback.startGame() ausgeführt, welche im GamePane
    * implementiert wurde.*/
    public LosingScreen(StartScreenCallback callback) {
        restartButton.addActionListener(e -> callback.startGame());
    }

    public JPanel getPanel1() {
        return panel1;
    }
}