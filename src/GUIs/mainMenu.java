package GUIs;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class mainMenu {
    private final JFrame f;
    private final JPanel panelMain;

//    private final JPanel panelTitle;
    private final JPanel panelBtn;
    private final JButton btnStart;
    private final JButton btnLoad;
    private final JButton btnOptions;
    private final JButton btnExit;

    public static void main(String[] args) {
        new mainMenu();
    }

    public mainMenu() {
        f = new JFrame("In Le Head - Main Menu");
        f.setSize(480, 480);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new GridBagLayout());
        f.setLocationRelativeTo(null);
        f.setBackground(Color.BLUE);

        panelMain = new JPanel();
        panelMain.setLayout(new GridBagLayout());
        panelMain.setOpaque(false);
        JPanel bg = new imgPanel();
        f.setContentPane(bg);

        panelBtn = new JPanel();
        panelBtn.setLayout(new GridBagLayout());
        panelBtn.setOpaque(false);

        GridBagConstraints gbc;
        btnStart = new JButton("START");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnStart.setFont(new Font(Font.MONOSPACED,Font.BOLD,14));
        panelBtn.add(btnStart,gbc);

        btnLoad = new JButton("LOAD");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnLoad.setFont(new Font(Font.MONOSPACED,Font.BOLD,14));
        panelBtn.add(btnLoad,gbc);

        btnOptions = new JButton("OPTIONS");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnOptions.setFont(new Font(Font.MONOSPACED,Font.BOLD,14));
        panelBtn.add(btnOptions,gbc);

        btnExit = new JButton("EXIT");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btnExit.setFont(new Font(Font.MONOSPACED,Font.BOLD,14));
        panelBtn.add(btnExit,gbc);

        panelMain.add(panelBtn);
        f.add(panelMain);

        f.setVisible(true);
    }

    class imgPanel extends JPanel
    {
        Image image;
        public imgPanel()
        {
            try
            {
                image = Toolkit.getDefaultToolkit().createImage("C:/Users/T00229173/IdeaProjects/JavaOOPMiniProject-MTU/src/GUIs/maxresdefault.jpg");
            }
            catch (Exception e) { /*handled in paintComponent()*/ }
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (image != null)
                g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
        }
    }
}
