package GUIs;

import GameObjects.Inventory;
import GameObjects.Item;
import GameObjects.Player;
import map.Room;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;

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
    private JTextArea txtGameLog;
    private JButton btnInv1;
    private JButton btnInv2;
    private JButton btnInv3;
    private JButton btnInv4;
    private JPanel pnlConsumables;
    private JPanel pnlHpBar;
    private JLabel lblHpValue;
    private JLabel lblHpIcon;
    private JButton btnLocation;
    private JButton btnMob1;
    private JButton btnMob2;
    private JButton btnMob3;
    private JPanel pnlGame;
    private JLabel lblMob2Hp;
    private JLabel lblMob1Hp;
    private JLabel lblMob3Hp;
    private JPanel pnlLoots;
    private MainMenu parent;
    private Room[] map;
    private int currLoc = 1;
    private Room room;
    private Player player;

    public GameWindow(String title, MainMenu parent, Room[] map, Player player) {
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

        setPlayer(player);
        setMap(map);
        setRoom(map[0]);


        txtGameLog.setEditable(false);
        txtGameLog.setText("You woke up in the forest...");
        txtGameLog.setSize(100, 400);
        txtGameLog.setLineWrap(true);

        btnLocation.addActionListener(e -> nextLocation(currLoc));
    }

    private void setLoots() {
        ArrayList<Item> loots = room.getLoots();
        pnlLoots.removeAll();
        for (Item loot : loots) {
            JButton btnLoot = new JButton();
            btnLoot.setText(loot.getName());
            btnLoot.addActionListener(this::btnLootClicked);
            pnlLoots.add(btnLoot);
        }
    }

    private void btnLootClicked(ActionEvent e) {
        JButton btnLoot = (JButton) e.getSource();
        room.newTurn(room.getPlayer(), "pickup", btnLoot.getText());
        btnLoot.setVisible(false);
        pnlLoots.remove(btnLoot);
        System.out.println(room.getPlayer());
        getTxtGameLog().setText(room.getGameLog());
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
        this.room = room;
        room.addPlayer(player);
        JButton[] btnMobs = {btnMob1, btnMob2, btnMob3};
        JLabel[] lblMobsHp = {lblMob1Hp, lblMob2Hp, lblMob3Hp};
        for (int i = 0; i < btnMobs.length; i++) {
            try {
                if (room.getMobs().get(i).getSrc() != null) {
                    btnMobs[i].setIcon(new ImageIcon(room.getMobs().get(i).getSrc()));
                    btnMobs[i].setSize(120, 120);
                    btnMobs[i].setOpaque(false);
                    btnMobs[i].setContentAreaFilled(false);
                    btnMobs[i].setBorderPainted(false);
                    lblMobsHp[i].setText(room.getMobs().get(i).getName() + " - HP: " + room.getMobs().get(i).getHp());
                    btnMobs[i].setText("");
                    btnMobs[i].setVisible(true);
                    lblMobsHp[i].setVisible(true);
                }
            } catch (Exception e) {
                btnMobs[i].setVisible(false);
                lblMobsHp[i].setVisible(false);
            }
        }

        btnLocation.setText(room.getName());
        setLoots();
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

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

    public JTextArea getTxtGameLog() {
        return txtGameLog;
    }

    public void setTxtGameLog(JTextArea txtGameLog) {
        this.txtGameLog = txtGameLog;
    }

    public JButton getBtnConsum1() {
        return btnInv1;
    }

    public void setBtnConsum1(JButton btnConsum1) {
        this.btnInv1 = btnConsum1;
    }

    public JButton getBtnConsum2() {
        return btnInv2;
    }

    public void setBtnConsum2(JButton btnConsum2) {
        this.btnInv2 = btnConsum2;
    }

    public JButton getBtnConsum3() {
        return btnInv3;
    }

    public void setBtnConsum3(JButton btnConsum3) {
        this.btnInv3 = btnConsum3;
    }

    public JButton getBtnConsum4() {
        return btnInv4;
    }

    public void setBtnConsum4(JButton btnConsum4) {
        this.btnInv4 = btnConsum4;
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
        btnLocation.setHorizontalAlignment(4);
        btnLocation.setText("(location)");
        pnlLocation.add(btnLocation, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlGameLog = new JPanel();
        pnlGameLog.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(pnlGameLog, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 400), null, 0, false));
        txtGameLog = new JTextArea();
        txtGameLog.setEditable(false);
        txtGameLog.setFocusable(false);
        Font txtGameLogFont = this.$$$getFont$$$("JetBrains Mono", Font.BOLD, 14, txtGameLog.getFont());
        if (txtGameLogFont != null) txtGameLog.setFont(txtGameLogFont);
        pnlGameLog.add(txtGameLog, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        pnlMobs = new JPanel();
        pnlMobs.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(pnlMobs, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pnlMobs.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        btnMob1 = new JButton();
        btnMob1.setBorderPainted(false);
        btnMob1.setText("(empty)");
        pnlMobs.add(btnMob1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMob2 = new JButton();
        btnMob2.setBorderPainted(false);
        btnMob2.setText("(empty)");
        pnlMobs.add(btnMob2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMob3 = new JButton();
        btnMob3.setBorderPainted(false);
        btnMob3.setText("(empty)");
        pnlMobs.add(btnMob3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMob2Hp = new JLabel();
        lblMob2Hp.setText("(0/0)");
        pnlMobs.add(lblMob2Hp, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMob1Hp = new JLabel();
        lblMob1Hp.setText("(0/0)");
        pnlMobs.add(lblMob1Hp, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMob3Hp = new JLabel();
        lblMob3Hp.setText("(0/0)");
        pnlMobs.add(lblMob3Hp, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlGame = new JPanel();
        pnlGame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(pnlGame, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, true));
        pnlHpBar = new JPanel();
        pnlHpBar.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnlGame.add(pnlHpBar, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 40), null, 0, false));
        lblHpValue = new JLabel();
        lblHpValue.setText("(100/100)");
        pnlHpBar.add(lblHpValue, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlConsumables = new JPanel();
        pnlConsumables.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        pnlGame.add(pnlConsumables, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(420, 200), new Dimension(420, 200), null, 0, false));
        pnlConsumables.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        btnInv1 = new JButton();
        btnInv1.setText("(empty)");
        pnlConsumables.add(btnInv1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnInv2 = new JButton();
        btnInv2.setText("(empty)");
        pnlConsumables.add(btnInv2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnInv3 = new JButton();
        btnInv3.setText("(empty)");
        pnlConsumables.add(btnInv3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnInv4 = new JButton();
        btnInv4.setText("(empty)");
        pnlConsumables.add(btnInv4, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        pnlLoots = new JPanel();
        pnlLoots.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainPanel.add(pnlLoots, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
}
