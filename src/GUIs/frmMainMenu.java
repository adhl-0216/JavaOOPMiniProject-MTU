package GUIs;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;

public class frmMainMenu extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel btmPanel;
    private JButton btnOptions;
    private JButton btnLoad;
    private JButton btnStart;
    private JButton btnExit;

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
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBackground(new Color(-6466770));
        topPanel.setForeground(new Color(-6466770));
        mainPanel.add(topPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        topPanel.add(spacer1, gbc);
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-1));
        label1.setFocusable(false);
        Font label1Font = this.$$$getFont$$$("Algerian", Font.BOLD, 24, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-11072761));
        label1.setText("In Le Head");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(label1, gbc);
        btmPanel = new JPanel();
        btmPanel.setLayout(new GridBagLayout());
        btmPanel.setBackground(new Color(-6466770));
        btmPanel.setEnabled(false);
        btmPanel.setForeground(new Color(-6466770));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        topPanel.add(btmPanel, gbc);
        btnOptions = new JButton();
        Font btnOptionsFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 14, btnOptions.getFont());
        if (btnOptionsFont != null) btnOptions.setFont(btnOptionsFont);
        btnOptions.setText("OPTIONS");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btmPanel.add(btnOptions, gbc);
        btnExit = new JButton();
        Font btnExitFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 14, btnExit.getFont());
        if (btnExitFont != null) btnExit.setFont(btnExitFont);
        btnExit.setText("EXIT");
        btnExit.setToolTipText("Close the application");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btmPanel.add(btnExit, gbc);
        btnLoad = new JButton();
        Font btnLoadFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 14, btnLoad.getFont());
        if (btnLoadFont != null) btnLoad.setFont(btnLoadFont);
        btnLoad.setText("LOAD");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btmPanel.add(btnLoad, gbc);
        btnStart = new JButton();
        Font btnStartFont = this.$$$getFont$$$("Comic Sans MS", Font.BOLD, 14, btnStart.getFont());
        if (btnStartFont != null) btnStart.setFont(btnStartFont);
        btnStart.setText("START");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        btmPanel.add(btnStart, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        btmPanel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        btmPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        btmPanel.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 10;
        btmPanel.add(spacer5, gbc);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(480, 360);
        frame.setLocationRelativeTo(null);

        frmMainMenu gui = new frmMainMenu();
        gui.getMainPanel().setBackground(Color.DARK_GRAY);

        frame.add(gui.getMainPanel());

        Image backgroundImage = Toolkit.getDefaultToolkit().getImage("mong.jpg");

        frame.setVisible(true);
    }

    Image backgroundImage = Toolkit.getDefaultToolkit().getImage("mong.jpg");

    public frmMainMenu() {
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });

        pack();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
