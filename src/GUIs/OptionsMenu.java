package GUIs;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;

public class OptionsMenu extends JFrame {
    private JPanel mainPanel;
    private JRadioButton radEasy;
    private JRadioButton radNormal;
    private JRadioButton radHard;
    private JLabel lblDifficulty;
    private JPanel pnlDiff;
    private MainMenu parent;
    private String difficulty;

    public OptionsMenu(MainMenu parent) {
        super("Options");
        setParent(parent);
        this.setSize(240, 180);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel bg = new MainMenu.imgPanel();
        this.setContentPane(bg);
        this.add(mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(exitListener);

        radEasy.addActionListener(this::selectDifficulty);
        radNormal.addActionListener(this::selectDifficulty);
        radHard.addActionListener(this::selectDifficulty);
    }

    private void selectDifficulty(ActionEvent e) {
        this.difficulty = ((JRadioButton) e.getSource()).getText();
        System.out.println(this.difficulty);
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public MainMenu getParent() {
        return parent;
    }

    public void setParent(MainMenu parent) {
        this.parent = parent;
    }

    WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(
                    null, "Return To Main Menu?",
                    "Quit Options", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
                getParent().setVisible(true);
                OptionsMenu.super.dispose();
            }
        }
    };

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);
        pnlDiff = new JPanel();
        pnlDiff.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnlDiff.setOpaque(false);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(pnlDiff, gbc);
        lblDifficulty = new JLabel();
        Font lblDifficultyFont = this.$$$getFont$$$("Monospaced", Font.BOLD, 16, lblDifficulty.getFont());
        if (lblDifficultyFont != null) lblDifficulty.setFont(lblDifficultyFont);
        lblDifficulty.setForeground(new Color(-1));
        lblDifficulty.setText("Difficulty");
        pnlDiff.add(lblDifficulty, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        radEasy = new JRadioButton();
        Font radEasyFont = this.$$$getFont$$$("Monospaced", Font.BOLD, 12, radEasy.getFont());
        if (radEasyFont != null) radEasy.setFont(radEasyFont);
        radEasy.setForeground(new Color(-1));
        radEasy.setName("");
        radEasy.setOpaque(false);
        radEasy.setText("Easy");
        pnlDiff.add(radEasy, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radNormal = new JRadioButton();
        Font radNormalFont = this.$$$getFont$$$("Monospaced", Font.BOLD, 12, radNormal.getFont());
        if (radNormalFont != null) radNormal.setFont(radNormalFont);
        radNormal.setForeground(new Color(-1));
        radNormal.setOpaque(false);
        radNormal.setText("Normal");
        pnlDiff.add(radNormal, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radHard = new JRadioButton();
        Font radHardFont = this.$$$getFont$$$("Monospaced", Font.BOLD, 12, radHard.getFont());
        if (radHardFont != null) radHard.setFont(radHardFont);
        radHard.setForeground(new Color(-1));
        radHard.setOpaque(false);
        radHard.setText("Hard");
        pnlDiff.add(radHard, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radHard);
        buttonGroup.add(radNormal);
        buttonGroup.add(radEasy);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    // https://coderanch.com/wiki/660351/Background-Image-JPanel
    static class imgPanel extends JPanel {
        Image image;

        public imgPanel() {
            try {
                image = Toolkit.getDefaultToolkit().createImage("assets/mainMenuBG.jpg");
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
