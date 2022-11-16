package GUIs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class mainMenu {
    private final JFrame f;
    private final JButton btnStart;
    private final JButton btnLoad;
    private final JButton btnOptions;
    private final JButton btnExit;

    public static void main(String[] args) {
        new mainMenu();
    }

    public mainMenu() {
        f = new JFrame("IN LE HEAD");
        f.setSize(360, 360);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);
        JPanel bg = new imgPanel();
        f.setContentPane(bg);
        f.setResizable(false);

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
        JLabel title = new JLabel("IN LE HEAD");
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        title.setForeground(new Color(255, 255, 255, 255));
//        panelTitle.setBackground(new Color(0,0,0,0));
        panelTitle.setOpaque(false);
        panelTitle.add(title);
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
//        panelBtn.setBackground(new Color(0,0,0,0));
        panelBtn.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        panelMain.add(panelBtn, gbc);

        btnStart = new JButton("START");
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

        btnOptions = new JButton("OPTIONS");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnOptions.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btnOptions.setForeground(new Color(255, 255, 255));
        btnOptions.setBackground(new Color(82, 79, 78));
        btnOptions.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 55)));
        panelBtn.add(btnOptions, gbc);

        btnExit = new JButton("EXIT");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnExit.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btnExit.setForeground(new Color(255, 255, 255));
        btnExit.setBackground(new Color(82, 79, 78));
        btnExit.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 55)));
        panelBtn.add(btnExit, gbc);

        btnStart.addActionListener(new btnClick());
        btnLoad.addActionListener(new btnClick());
        btnOptions.addActionListener(new btnClick());
        btnExit.addActionListener(new btnClick());

        f.add(panelMain);

        f.setVisible(true);
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


    private class btnClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Timer t = new Timer();
            class hideShowFrame extends TimerTask{
                @Override
                public void run() {
                    f.setVisible(true);
                }
            }
            if (e.getSource() == btnStart) {
                System.out.println("Start");
                f.setVisible(false);
                t.schedule(new hideShowFrame(), 5000);
            } else if (e.getSource() == btnLoad) {
                System.out.println("Load");
                f.setVisible(false);
                t.schedule(new hideShowFrame(), 5000);
            } else if (e.getSource() == btnOptions) {
                System.out.println("Options");
                f.setVisible(false);
                t.schedule(new hideShowFrame(), 5000);
            } else if (e.getSource() == btnExit) {
                if (JOptionPane.showConfirmDialog(btnExit.getParent(), "Giving up?", "Escape", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == 0) {
                    JOptionPane.showMessageDialog(null, "COWARD","COWARD",JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
            }
        }
    }
}
