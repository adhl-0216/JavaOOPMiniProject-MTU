package GUIs;

import GameObjects.Player;
import map.Map;
import map.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class MainMenu extends JFrame{

    private GameWindow gameWindow;
    private final OptionsMenu optionsMenu = new OptionsMenu(this);
    private final JButton btnLoad;

    public MainMenu(String title) {
        this.setTitle(title);
        this.setSize(360, 360);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        JPanel bg = new imgPanel();
        this.setContentPane(bg);
        this.setResizable(false);

        this.setIconImage(new ImageIcon("resources/icon.png").getImage());

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridBagLayout());
        panelMain.setOpaque(false);

        GridBagConstraints gbc;
        final JPanel vSpacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 45;
        vSpacer1.setOpaque(false);
        panelMain.add(vSpacer1, gbc);

        JPanel panelTitle = new JPanel();
        JLabel lblTitle = new JLabel("A SECOND CHANCE");
        lblTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        lblTitle.setForeground(new Color(255, 255, 255, 255));
        panelTitle.setOpaque(false);
        panelTitle.add(lblTitle);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panelMain.add(panelTitle, gbc);

        final JPanel vSpacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 15;
        vSpacer2.setOpaque(false);
        panelMain.add(vSpacer2, gbc);

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new GridBagLayout());
        panelBtn.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        panelMain.add(panelBtn, gbc);

        JButton btnStart = new JButton("START");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnStart.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btnStart.setForeground(new Color(255, 255, 255));
        btnStart.setBackground(new Color(82, 79, 78));
        btnStart.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 55)));
        panelBtn.add(btnStart, gbc);

        btnLoad = new JButton("LOAD");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnLoad.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btnLoad.setForeground(new Color(255, 255, 255));
        btnLoad.setBackground(new Color(82, 79, 78));
        btnLoad.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 55)));
        panelBtn.add(btnLoad, gbc);

        JButton btnOptions = new JButton("OPTIONS");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnOptions.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btnOptions.setForeground(new Color(255, 255, 255));
        btnOptions.setBackground(new Color(82, 79, 78));
        btnOptions.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 55)));
        panelBtn.add(btnOptions, gbc);

        JButton btnExit = new JButton("EXIT");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnExit.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btnExit.setForeground(new Color(255, 255, 255));
        btnExit.setBackground(new Color(82, 79, 78));
        btnExit.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 55)));
        panelBtn.add(btnExit, gbc);

        btnStart.addActionListener(e -> btnStartClicked());
        btnLoad.addActionListener(e -> btnLoadClicked());
        btnOptions.addActionListener(e -> btnOptionsClicked());
        btnExit.addActionListener(e -> btnExitClicked());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                btnExitClicked();
            }
        });

        this.add(panelMain);
        this.setVisible(true);
    }
    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }
    private void btnStartClicked() {
        int newGame = JOptionPane.showConfirmDialog(null, "Start new game?", "Start Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(newGame == 0) {
            try {
                Player player = new Player(100, 15, 15,3);
                String difficulty = optionsMenu.getDifficulty();
                if (difficulty != null) {
                    switch (difficulty) {
                        case "Easy" -> player = new Player(200, 20, 20,3);
                        case "Normal" -> player = new Player(100, 15, 15,3);
                        case "Hard" -> player = new Player(100, 5, 5,3);
                    }
                }
                setGameWindow(new GameWindow(this.getTitle(), this, player, Map.getMap(),0));
                this.setVisible(false);
                btnLoad.setEnabled(true);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private void btnLoadClicked() {
        if (gameWindow == null) {
            try {
                File inFile = new File("src/SavedGame/saved_game.data");
                FileInputStream inStream = new FileInputStream(inFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
                Room[] loadedMap = (Room[]) objectInputStream.readObject();
                int prevLoc = (int) objectInputStream.readObject();
                setGameWindow(new GameWindow(this.getTitle(), this, loadedMap[prevLoc].getPlayer(), loadedMap, prevLoc));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No Saved Games.", "No Saved Games", JOptionPane.INFORMATION_MESSAGE);
                throw new RuntimeException(e);
            }
        }
        else {
            gameWindow.setVisible(true);
            this.setVisible(false);
        }

    }
    private void btnOptionsClicked() {
        optionsMenu.setVisible(true);
        MainMenu.this.setVisible(false);
    }
    private void btnExitClicked() {
        int exit = JOptionPane.showConfirmDialog(null, "Giving up?", "Escape", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (exit == 0) {
            if (this.gameWindow != null) {
                try {
                    File outFile = new File("src/SavedGame/saved_game.data");
                    FileOutputStream outStream = new FileOutputStream(outFile);
                    ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
                    objectOutStream.writeObject(gameWindow.getMap());
                    objectOutStream.writeObject(gameWindow.getCurrLoc());
                    JOptionPane.showMessageDialog(null, "There is no escape...", "Game Saved", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            JOptionPane.showMessageDialog(null, "COWARD","COWARD",JOptionPane.WARNING_MESSAGE);
            MainMenu.this.dispose();
            System.exit(0);
        }
    }

    // https://coderanch.com/wiki/660351/Background-Image-JPanel
    static class imgPanel extends JPanel {
        Image image;

        public imgPanel() {
            try {
                image = Toolkit.getDefaultToolkit().createImage("resources/mainMenuBG.jpg");
            } catch (Exception e) { /*handled in paintComponent()*/ }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null)
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}