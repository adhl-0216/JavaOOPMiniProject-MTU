package GUIs;

import javax.swing.*;
import java.awt.*;

public class mainMenu {
    private final JFrame f;
    private final JPanel mainPanel;
    private final JButton btnStart;
    private final JButton btnLoad;
    private final JButton btnOptions;
    private final JButton btnExit;
    public mainMenu() {
        f = new JFrame("In Le Head - Main Menu");
        f.setSize(480, 480);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new GridBagLayout());
        f.setLocationRelativeTo(null);

        mainPanel = new JPanel();

        GridBagConstraints gbc;
        btnStart = new JButton("START");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnStart.setFont(Font.getFont("Comic Sans MS"));
        mainPanel.add(btnStart,gbc);

        btnLoad = new JButton("LOAD");
        gbc.gridy = 3;
        btnLoad.setFont(Font.getFont("Comic Sans MS"));
        mainPanel.add(btnLoad);

        btnOptions = new JButton("OPTIONS");
        gbc.gridy = 5;
        btnOptions.setFont(Font.getFont("Comic Sans MS"));
        mainPanel.add(btnOptions,gbc);

        btnExit = new JButton("EXIT");
        gbc.gridy = 7;
        btnExit.setFont(Font.getFont("Comic Sans MS"));
        mainPanel.add(btnExit,gbc);



        mainPanel.setLayout(new GridBagLayout());

        f.add(mainPanel);

        f.setVisible(true);
    }

    public static void main(String[] args) {
        new mainMenu();
    }
}
