package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenu extends JFrame{

    private GameWindow gameWindow;

    public MainMenu(String title) {
        this.setTitle(title);
        this.setSize(360, 360);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        JPanel bg = new imgPanel();
        this.setContentPane(bg);
        this.setResizable(false);

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

        JButton btnLoad = new JButton("LOAD");
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
    }

    Timer t = new Timer();
    class hideShowFrame extends TimerTask{
        @Override
        public void run() {
            MainMenu.this.setVisible(true);
        }
    }

    public void setGameWindow(GameWindow mainGame) {
        this.gameWindow = mainGame;
    }

    public GameWindow getGameWindow() {
        try {
            return gameWindow;
        } catch (Exception e) {
            System.out.println("mainGame is null.");
            return null;
        }
    }

    private void btnStartClicked() {
        try {
            gameWindow.setVisible(true);
            MainMenu.this.setVisible(false);
        } catch (Exception e) {
            System.out.println("mainGame is null.");
            System.exit(0);
        }
    }
    private void btnLoadClicked() {
        System.out.println("Load");

        MainMenu.this.setVisible(false);
        t.schedule(new hideShowFrame(), 3000);
    }
    private void btnOptionsClicked() {
        System.out.println("Options");

        MainMenu.this.setVisible(false);
        t.schedule(new hideShowFrame(), 3000);
    }
    private void btnExitClicked() {
        int exit = JOptionPane.showConfirmDialog(null, "Giving up?", "Escape", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (exit == 0) {
            JOptionPane.showMessageDialog(null, "COWARD","COWARD",JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }


    // https://coderanch.com/wiki/660351/Background-Image-JPanel
    private static class imgPanel extends JPanel {
        Image image;

        public imgPanel() {
            try {
                image = Toolkit.getDefaultToolkit().createImage("src/GUIs/mainMenuBG.jpg");
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
