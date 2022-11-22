package GUIs;

import GameObjects.Entity;
import map.Room;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

public class GameWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel pnlEquipments;
    private JPanel pnlLocation;
    private JPanel pnlMobs;
    private JPanel pnlGameLog;
    private JButton btnMisc;
    private JButton btnHead;
    private JButton btnMainWeapon;
    private JButton btnBody;
    private JTextField txtGameLog;
    private JButton btnConsum1;
    private JButton btnConsum2;
    private JButton btnConsum3;
    private JButton btnConsum4;
    private JPanel pnlConsumables;
    private JPanel pnlHpBar;
    private JLabel lblHpValue;
    private JLabel lblHpIcon;
    private JButton btnLocation;
    private JButton btnMob1;
    private JButton btnMob2;
    private JButton btnMob3;
    private JPanel pnlGame;
    private MainMenu parent;
    private Room[] map;
    private int currLoc = 1;
    private Room room;

    public GameWindow(String title, MainMenu parent, Room[] map) {
        super(title);
        this.setParent(parent);
        $$$setupUI$$$();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(exitListener);
        this.setSize(1080, 720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(mainPanel);
        this.pack();

        this.setMap(map);
        this.setRoom(map[0]);

        txtGameLog.setEditable(false);
        txtGameLog.setText("lorem ipsum");

        btnLocation.addActionListener(e -> nextLocation(currLoc));
    }

    private void nextLocation(int currLoc) {
        if (currLoc == map.length) {
            JOptionPane.showMessageDialog(null, "You have been grated a second chance in life.", "The End", JOptionPane.INFORMATION_MESSAGE);
            super.dispose();
            parent.setVisible(true);
        } else {
            setRoom(map[currLoc]);
            this.currLoc++;
        }
    }

    private void setRoom(Room room) {
        JButton[] btnMobs = {btnMob1, btnMob2, btnMob3};
        for (int i = 0; i < btnMobs.length; i++) {
            try {
                if (room.getMobs().get(i).getSrc() != null) {
                    btnMobs[i].setIcon(new ImageIcon(room.getMobs().get(i).getSrc()));
                    btnMobs[i].setText(room.getMobs().get(i).getName());
                    btnMobs[i].setVisible(true);
                    System.out.println("set " + room.getMobs().get(i).getName());
                }
            } catch (Exception e) {
                btnMobs[i].setVisible(false);
            }
        }

        btnLocation.setText(room.getName());
    }

    public void setMap(Room[] map) {
        this.map = map;
//        System.out.println(Arrays.toString(map));
    }

    public Room[] getMap() {
        return map;
    }

    WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showOptionDialog(
                    null, "Return To Main Menu?",
                    "Quit Game", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == 0) {
                getParent().setVisible(true);
                GameWindow.super.dispose();
            }
        }
    };

    @Override
    public MainMenu getParent() {
        return parent;
    }

    public void setParent(MainMenu parent) {
        this.parent = parent;
    }

    public JButton getBtnMisc() {
        return btnMisc;
    }

    public void setBtnMisc(JButton btnMisc) {
        this.btnMisc = btnMisc;
    }

    public JButton getBtnHead() {
        return btnHead;
    }

    public void setBtnHead(JButton btnHead) {
        this.btnHead = btnHead;
    }

    public JButton getBtnMainWeapon() {
        return btnMainWeapon;
    }

    public void setBtnMainWeapon(JButton btnMainWeapon) {
        this.btnMainWeapon = btnMainWeapon;
    }

    public JButton getBtnBody() {
        return btnBody;
    }

    public void setBtnBody(JButton btnBody) {
        this.btnBody = btnBody;
    }

    public JTextField getTxtGameLog() {
        return txtGameLog;
    }

    public void setTxtGameLog(JTextField txtGameLog) {
        this.txtGameLog = txtGameLog;
    }

    public JButton getBtnConsum1() {
        return btnConsum1;
    }

    public void setBtnConsum1(JButton btnConsum1) {
        this.btnConsum1 = btnConsum1;
    }

    public JButton getBtnConsum2() {
        return btnConsum2;
    }

    public void setBtnConsum2(JButton btnConsum2) {
        this.btnConsum2 = btnConsum2;
    }

    public JButton getBtnConsum3() {
        return btnConsum3;
    }

    public void setBtnConsum3(JButton btnConsum3) {
        this.btnConsum3 = btnConsum3;
    }

    public JButton getBtnConsum4() {
        return btnConsum4;
    }

    public void setBtnConsum4(JButton btnConsum4) {
        this.btnConsum4 = btnConsum4;
    }

    public JLabel getLblHpValue() {
        return lblHpValue;
    }

    public void setLblHpValue(JLabel lblHpValue) {
        this.lblHpValue = lblHpValue;
    }

    public JLabel getLblHpIcon() {
        return lblHpIcon;
    }

    public void setLblHpIcon(JLabel lblHpIcon) {
        this.lblHpIcon = lblHpIcon;
    }

    public JButton getBtnLocation() {
        return btnLocation;
    }

    public void setBtnLocation(JButton btnLocation) {
        this.btnLocation = btnLocation;
    }

    public JButton getBtnMob1() {
        return btnMob1;
    }

    public void setBtnMob1(JButton btnMob1) {
        this.btnMob1 = btnMob1;
    }

    public JButton getBtnMob2() {
        return btnMob2;
    }

    public void setBtnMob2(JButton btnMob2) {
        this.btnMob2 = btnMob2;
    }

    public JButton getBtnMob3() {
        return btnMob3;
    }

    public void setBtnMob3(JButton btnMob3) {
        this.btnMob3 = btnMob3;
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
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setMinimumSize(new Dimension(1080, 480));
        mainPanel.setPreferredSize(new Dimension(1080, 480));
        pnlLocation = new JPanel();
        pnlLocation.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(pnlLocation, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnLocation = new JButton();
        btnLocation.setBorderPainted(false);
        btnLocation.setText("(location)");
        pnlLocation.add(btnLocation, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlGameLog = new JPanel();
        pnlGameLog.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(pnlGameLog, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 400), null, 0, false));
        txtGameLog = new JTextField();
        pnlGameLog.add(txtGameLog, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pnlMobs = new JPanel();
        pnlMobs.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(pnlMobs, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pnlMobs.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        btnMob1 = new JButton();
        btnMob1.setBorderPainted(false);
        btnMob1.setText("(empty)");
        pnlMobs.add(btnMob1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMob2 = new JButton();
        btnMob2.setBorderPainted(false);
        btnMob2.setText("(empty)");
        pnlMobs.add(btnMob2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMob3 = new JButton();
        btnMob3.setBorderPainted(false);
        btnMob3.setText("(empty)");
        pnlMobs.add(btnMob3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlGame = new JPanel();
        pnlGame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(pnlGame, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, true));
        pnlHpBar = new JPanel();
        pnlHpBar.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnlGame.add(pnlHpBar, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 40), null, 0, false));
        lblHpIcon = new JLabel();
        lblHpIcon.setText("Label");
        pnlHpBar.add(lblHpIcon, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblHpValue = new JLabel();
        lblHpValue.setText("Label");
        pnlHpBar.add(lblHpValue, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlConsumables = new JPanel();
        pnlConsumables.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        pnlGame.add(pnlConsumables, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(420, 200), new Dimension(420, 200), null, 0, false));
        pnlConsumables.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        btnConsum1 = new JButton();
        btnConsum1.setText("(empty)");
        pnlConsumables.add(btnConsum1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnConsum2 = new JButton();
        btnConsum2.setText("(empty)");
        pnlConsumables.add(btnConsum2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnConsum3 = new JButton();
        btnConsum3.setText("(empty)");
        pnlConsumables.add(btnConsum3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnConsum4 = new JButton();
        btnConsum4.setText("(empty)");
        pnlConsumables.add(btnConsum4, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlEquipments = new JPanel();
        pnlEquipments.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnlEquipments.setOpaque(true);
        pnlEquipments.setPreferredSize(new Dimension(320, 320));
        pnlGame.add(pnlEquipments, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(124, 124), null, 0, false));
        btnMainWeapon = new JButton();
        btnMainWeapon.setText("(empty)");
        pnlEquipments.add(btnMainWeapon, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnHead = new JButton();
        btnHead.setText("(empty)");
        pnlEquipments.add(btnHead, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnBody = new JButton();
        btnBody.setText("(empty)");
        pnlEquipments.add(btnBody, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMisc = new JButton();
        btnMisc.setText("(empty)");
        pnlEquipments.add(btnMisc, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
}
