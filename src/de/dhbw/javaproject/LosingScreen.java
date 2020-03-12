package de.dhbw.javaproject;

import javax.swing.*;

public class LosingScreen {
    private JPanel panel1;
    private JButton restartButton;
    private javax.swing.JLabel JLabel;

    public void setLabelText(int level, int itemsLostScore) {
        JLabel.setText("Level: " + level + " mit " + itemsLostScore + " verlorenen Items");
    }

    public LosingScreen(StartScreenCallback callback) {
        restartButton.addActionListener(e -> callback.startGame());
    }

    public JPanel getPanel1() {
        return panel1;
    }


}