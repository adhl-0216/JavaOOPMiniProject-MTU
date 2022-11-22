package GUIs;

import GameObjects.Item;
import GameObjects.Loots.Equipment;
import GameObjects.Loots.Weapon;
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
import java.util.Objects;

public class GameWindow extends JFrame {
    private static final Font customFont = new Font(Font.MONOSPACED, Font.BOLD, 14);
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
    private JPanel pnlInventory;
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
    private JLabel lblDef;
    private JLabel lblAtk;
    private JLabel lblSans;
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
        this.setSize(1080, 960);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        JPanel bg = new MainMenu.imgPanel();
        this.setContentPane(bg);
        this.add(mainPanel);
        this.pack();

        setPlayer(player);
        setMap(map);
        setRoom(map[0]);

        txtGameLog.setEditable(false);
        txtGameLog.setText("You woke up in the forest...");
        txtGameLog.setSize(100, 400);
        txtGameLog.setLineWrap(true);

        btnLocation.addActionListener(e -> nextLocation(currLoc));
        btnInv1.addActionListener(this::equipOrConsume);
        btnInv2.addActionListener(this::equipOrConsume);
        btnInv3.addActionListener(this::equipOrConsume);
        btnInv4.addActionListener(this::equipOrConsume);
    }

    private void equipOrConsume(ActionEvent e) {
        JButton btnInv = (JButton) e.getSource();
        if (btnInv.getName().equalsIgnoreCase("weapon")) {
            room.newTurn(player, "equip", btnInv.getText());
            setEquipments();
        } else if (btnInv.getName().equalsIgnoreCase("equipment")) {
            room.newTurn(player, "equip", btnInv.getText());
            setEquipments();
        } else if (btnInv.getName().equalsIgnoreCase("consumable")) {
            room.newTurn(player, "consume", btnInv.getText());
            lblHpValue.setText(String.format("HP: (%.0f/100)", player.getHp()));
            lblSans.setText(String.format("SANITY: (%.0f/100)", player.getSanity()));
        }
        System.out.println(player);
        btnInv.setText("(empty)");
        txtGameLog.setText(room.getGameLog());
    }

    private void setEquipments() {


        if (player.getMainWeapon() != null) {
            lblAtk.setText("ATK: " + player.getAtk());
            btnMainWeapon.setText(player.getMainWeapon().getName());
        }


        if (player.getHead() != null) {
            lblDef.setText("DEF: " + player.getDef());
            btnHead.setText(player.getHead().getName());
        }

        if (player.getBody() != null) {
            lblDef.setText("DEF: " + player.getDef());
            btnBody.setText(player.getBody().getName());
        }

        if (player.getMisc() != null) {
            lblDef.setText("DEF: " + player.getDef());
            btnMisc.setText(player.getMisc().getName());
        }

    }

    private void setLoots() {
        ArrayList<Item> loots = room.getLoots();
        pnlLoots.removeAll();
        for (Item loot : loots) {
            JButton btnLoot = new JButton();
            btnLoot.setText(loot.getName());
            btnLoot.setFont(customFont);
            btnLoot.setForeground(Color.white);
            btnLoot.setBackground(new Color(82, 79, 78));
            btnLoot.addActionListener(this::btnLootClicked);
            pnlLoots.add(btnLoot);
        }
    }

    private void btnLootClicked(ActionEvent e) {
        String gameLog;
        JButton btnLoot = (JButton) e.getSource();
        gameLog = room.newTurn(room.getPlayer(), "pickup", btnLoot.getText());
        if (!Objects.equals(gameLog, "full")) {
            btnLoot.setVisible(false);
            pnlLoots.remove(btnLoot);
            gameLog = room.getGameLog();
            getTxtGameLog().setText(gameLog);
            try {
                setPnlInventory();
            } catch (Exception ex) {
                System.out.println("???");
            }
        } else {
            JOptionPane.showMessageDialog(null, "There's no more space in your inventory!", "Inventory is Full", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void setPnlInventory() {
        Item[] inv = player.getInventory().getItems();
        JButton[] btnInvs = {btnInv1, btnInv2, btnInv3, btnInv4};
        for (int i = 0; i < inv.length; i++) {
            btnInvs[i].setText(inv[i].getName());
            String type = inv[i].getClass().getSimpleName();
            btnInvs[i].setName(type);
        }
    }

    private void nextLocation(int nextLoc) {
        if (nextLoc == map.length) {
            JOptionPane.showMessageDialog(null, "You have been grated a second chance in life.", "The End", JOptionPane.INFORMATION_MESSAGE);
            super.dispose();
            parent.setVisible(true);
        } else {
            setRoom(map[nextLoc]);
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

    // https://coderanch.com/wiki/660351/Background-Image-JPanel
    private static class imgPanel extends JPanel {
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


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(4, 4, 4, 4), 4, 4));
        mainPanel.setBackground(new Color(-11382962));
        mainPanel.setDoubleBuffered(false);
        Font mainPanelFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, mainPanel.getFont());
        if (mainPanelFont != null) mainPanel.setFont(mainPanelFont);
        mainPanel.setForeground(new Color(-1));
        mainPanel.setMinimumSize(new Dimension(1080, 560));
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new Dimension(1080, 560));
        pnlLocation = new JPanel();
        pnlLocation.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnlLocation.setBackground(new Color(-11382962));
        pnlLocation.setDoubleBuffered(false);
        Font pnlLocationFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlLocation.getFont());
        if (pnlLocationFont != null) pnlLocation.setFont(pnlLocationFont);
        pnlLocation.setForeground(new Color(-1));
        pnlLocation.setOpaque(false);
        mainPanel.add(pnlLocation, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnLocation = new JButton();
        btnLocation.setBackground(new Color(-11382962));
        btnLocation.setBorderPainted(false);
        btnLocation.setDoubleBuffered(false);
        btnLocation.setEnabled(true);
        Font btnLocationFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnLocation.getFont());
        if (btnLocationFont != null) btnLocation.setFont(btnLocationFont);
        btnLocation.setForeground(new Color(-1));
        btnLocation.setHorizontalAlignment(4);
        btnLocation.setOpaque(false);
        btnLocation.setText("(location)");
        pnlLocation.add(btnLocation, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlGameLog = new JPanel();
        pnlGameLog.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnlGameLog.setBackground(new Color(-11382962));
        pnlGameLog.setDoubleBuffered(false);
        Font pnlGameLogFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlGameLog.getFont());
        if (pnlGameLogFont != null) pnlGameLog.setFont(pnlGameLogFont);
        pnlGameLog.setForeground(new Color(-1));
        pnlGameLog.setOpaque(false);
        mainPanel.add(pnlGameLog, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 400), new Dimension(400, 505), 0, false));
        txtGameLog = new JTextArea();
        txtGameLog.setBackground(new Color(-11382962));
        txtGameLog.setDoubleBuffered(false);
        txtGameLog.setEditable(false);
        txtGameLog.setFocusable(false);
        Font txtGameLogFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, txtGameLog.getFont());
        if (txtGameLogFont != null) txtGameLog.setFont(txtGameLogFont);
        txtGameLog.setForeground(new Color(-1));
        txtGameLog.setLineWrap(true);
        txtGameLog.setOpaque(true);
        txtGameLog.setText("");
        txtGameLog.setWrapStyleWord(true);
        pnlGameLog.add(txtGameLog, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(400, 500), new Dimension(400, 500), 0, false));
        pnlMobs = new JPanel();
        pnlMobs.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        pnlMobs.setBackground(new Color(-11382962));
        pnlMobs.setDoubleBuffered(false);
        pnlMobs.setEnabled(false);
        pnlMobs.setFocusable(false);
        Font pnlMobsFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlMobs.getFont());
        if (pnlMobsFont != null) pnlMobs.setFont(pnlMobsFont);
        pnlMobs.setForeground(new Color(-1));
        pnlMobs.setOpaque(false);
        mainPanel.add(pnlMobs, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlMobs.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        btnMob1 = new JButton();
        btnMob1.setBackground(new Color(-11382962));
        btnMob1.setBorderPainted(false);
        btnMob1.setDoubleBuffered(false);
        Font btnMob1Font = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnMob1.getFont());
        if (btnMob1Font != null) btnMob1.setFont(btnMob1Font);
        btnMob1.setForeground(new Color(-1));
        btnMob1.setOpaque(false);
        btnMob1.setText("(empty)");
        pnlMobs.add(btnMob1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMob2 = new JButton();
        btnMob2.setBackground(new Color(-11382962));
        btnMob2.setBorderPainted(false);
        btnMob2.setDoubleBuffered(false);
        Font btnMob2Font = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnMob2.getFont());
        if (btnMob2Font != null) btnMob2.setFont(btnMob2Font);
        btnMob2.setForeground(new Color(-1));
        btnMob2.setOpaque(false);
        btnMob2.setText("(empty)");
        pnlMobs.add(btnMob2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnMob3 = new JButton();
        btnMob3.setBackground(new Color(-11382962));
        btnMob3.setBorderPainted(false);
        btnMob3.setDoubleBuffered(false);
        Font btnMob3Font = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnMob3.getFont());
        if (btnMob3Font != null) btnMob3.setFont(btnMob3Font);
        btnMob3.setForeground(new Color(-1));
        btnMob3.setOpaque(false);
        btnMob3.setText("(empty)");
        pnlMobs.add(btnMob3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMob2Hp = new JLabel();
        lblMob2Hp.setBackground(new Color(-11382962));
        lblMob2Hp.setDoubleBuffered(false);
        Font lblMob2HpFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, lblMob2Hp.getFont());
        if (lblMob2HpFont != null) lblMob2Hp.setFont(lblMob2HpFont);
        lblMob2Hp.setForeground(new Color(-1));
        lblMob2Hp.setOpaque(false);
        lblMob2Hp.setText("(0/0)");
        pnlMobs.add(lblMob2Hp, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMob1Hp = new JLabel();
        lblMob1Hp.setBackground(new Color(-11382962));
        lblMob1Hp.setDoubleBuffered(false);
        Font lblMob1HpFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, lblMob1Hp.getFont());
        if (lblMob1HpFont != null) lblMob1Hp.setFont(lblMob1HpFont);
        lblMob1Hp.setForeground(new Color(-1));
        lblMob1Hp.setOpaque(false);
        lblMob1Hp.setText("(0/0)");
        pnlMobs.add(lblMob1Hp, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblMob3Hp = new JLabel();
        lblMob3Hp.setBackground(new Color(-11382962));
        lblMob3Hp.setDoubleBuffered(false);
        Font lblMob3HpFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, lblMob3Hp.getFont());
        if (lblMob3HpFont != null) lblMob3Hp.setFont(lblMob3HpFont);
        lblMob3Hp.setForeground(new Color(-1));
        lblMob3Hp.setOpaque(false);
        lblMob3Hp.setText("(0/0)");
        pnlMobs.add(lblMob3Hp, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlGame = new JPanel();
        pnlGame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnlGame.setBackground(new Color(-11382962));
        pnlGame.setDoubleBuffered(false);
        pnlGame.setFocusable(false);
        Font pnlGameFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlGame.getFont());
        if (pnlGameFont != null) pnlGame.setFont(pnlGameFont);
        pnlGame.setForeground(new Color(-1));
        pnlGame.setOpaque(false);
        mainPanel.add(pnlGame, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, true));
        pnlHpBar = new JPanel();
        pnlHpBar.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        pnlHpBar.setBackground(new Color(-11382962));
        pnlHpBar.setDoubleBuffered(false);
        Font pnlHpBarFont = this.$$$getFont$$$("Courier New", Font.BOLD, 12, pnlHpBar.getFont());
        if (pnlHpBarFont != null) pnlHpBar.setFont(pnlHpBarFont);
        pnlHpBar.setForeground(new Color(-1));
        pnlHpBar.setOpaque(false);
        pnlGame.add(pnlHpBar, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 26), new Dimension(-1, 26), new Dimension(-1, 26), 0, false));
        lblHpValue = new JLabel();
        lblHpValue.setBackground(new Color(-11382962));
        lblHpValue.setDoubleBuffered(false);
        lblHpValue.setEnabled(true);
        lblHpValue.setFocusable(false);
        Font lblHpValueFont = this.$$$getFont$$$("Courier New", Font.BOLD, 18, lblHpValue.getFont());
        if (lblHpValueFont != null) lblHpValue.setFont(lblHpValueFont);
        lblHpValue.setForeground(new Color(-1));
        lblHpValue.setHorizontalAlignment(0);
        lblHpValue.setHorizontalTextPosition(0);
        lblHpValue.setOpaque(false);
        lblHpValue.setText("HP: (100/100)");
        pnlHpBar.add(lblHpValue, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblDef = new JLabel();
        lblDef.setBackground(new Color(-11382962));
        lblDef.setDoubleBuffered(false);
        lblDef.setEnabled(true);
        lblDef.setFocusable(false);
        Font lblDefFont = this.$$$getFont$$$("Courier New", Font.BOLD, 18, lblDef.getFont());
        if (lblDefFont != null) lblDef.setFont(lblDefFont);
        lblDef.setForeground(new Color(-1));
        lblDef.setHorizontalAlignment(0);
        lblDef.setHorizontalTextPosition(0);
        lblDef.setOpaque(false);
        lblDef.setText("DEF: 5");
        pnlHpBar.add(lblDef, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblAtk = new JLabel();
        lblAtk.setBackground(new Color(-11382962));
        lblAtk.setDoubleBuffered(false);
        lblAtk.setEnabled(true);
        lblAtk.setFocusable(false);
        Font lblAtkFont = this.$$$getFont$$$("Courier New", Font.BOLD, 18, lblAtk.getFont());
        if (lblAtkFont != null) lblAtk.setFont(lblAtkFont);
        lblAtk.setForeground(new Color(-1));
        lblAtk.setHorizontalAlignment(0);
        lblAtk.setHorizontalTextPosition(0);
        lblAtk.setOpaque(false);
        lblAtk.setText("ATK: 5");
        pnlHpBar.add(lblAtk, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblSans = new JLabel();
        lblSans.setBackground(new Color(-11382962));
        lblSans.setDoubleBuffered(false);
        lblSans.setEnabled(true);
        lblSans.setFocusable(false);
        Font lblSansFont = this.$$$getFont$$$("Courier New", Font.BOLD, 18, lblSans.getFont());
        if (lblSansFont != null) lblSans.setFont(lblSansFont);
        lblSans.setForeground(new Color(-1));
        lblSans.setHorizontalAlignment(0);
        lblSans.setHorizontalTextPosition(0);
        lblSans.setOpaque(false);
        lblSans.setText("SANITY: (100/100)");
        pnlHpBar.add(lblSans, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlInventory = new JPanel();
        pnlInventory.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        pnlInventory.setBackground(new Color(-11382962));
        pnlInventory.setDoubleBuffered(false);
        pnlInventory.setEnabled(false);
        pnlInventory.setFocusable(true);
        Font pnlInventoryFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlInventory.getFont());
        if (pnlInventoryFont != null) pnlInventory.setFont(pnlInventoryFont);
        pnlInventory.setForeground(new Color(-1));
        pnlInventory.setOpaque(false);
        pnlGame.add(pnlInventory, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(420, 200), new Dimension(420, 200), null, 0, false));
        pnlInventory.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        btnInv1 = new JButton();
        btnInv1.setBackground(new Color(-11382962));
        btnInv1.setDoubleBuffered(false);
        Font btnInv1Font = this.$$$getFont$$$("Courier New", Font.BOLD, 12, btnInv1.getFont());
        if (btnInv1Font != null) btnInv1.setFont(btnInv1Font);
        btnInv1.setForeground(new Color(-1));
        btnInv1.setOpaque(true);
        btnInv1.setText("(empty)");
        btnInv1.setToolTipText("Inventory slot 1");
        pnlInventory.add(btnInv1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnInv2 = new JButton();
        btnInv2.setBackground(new Color(-11382962));
        btnInv2.setDoubleBuffered(false);
        Font btnInv2Font = this.$$$getFont$$$("Courier New", Font.BOLD, 12, btnInv2.getFont());
        if (btnInv2Font != null) btnInv2.setFont(btnInv2Font);
        btnInv2.setForeground(new Color(-1));
        btnInv2.setOpaque(true);
        btnInv2.setText("(empty)");
        btnInv2.setToolTipText("Inventory slot 2");
        pnlInventory.add(btnInv2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnInv3 = new JButton();
        btnInv3.setBackground(new Color(-11382962));
        btnInv3.setDoubleBuffered(false);
        Font btnInv3Font = this.$$$getFont$$$("Courier New", Font.BOLD, 12, btnInv3.getFont());
        if (btnInv3Font != null) btnInv3.setFont(btnInv3Font);
        btnInv3.setForeground(new Color(-1));
        btnInv3.setOpaque(true);
        btnInv3.setText("(empty)");
        btnInv3.setToolTipText("Inventory slot 3");
        pnlInventory.add(btnInv3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnInv4 = new JButton();
        btnInv4.setBackground(new Color(-11382962));
        btnInv4.setDoubleBuffered(false);
        Font btnInv4Font = this.$$$getFont$$$("Courier New", Font.BOLD, 12, btnInv4.getFont());
        if (btnInv4Font != null) btnInv4.setFont(btnInv4Font);
        btnInv4.setForeground(new Color(-1));
        btnInv4.setOpaque(true);
        btnInv4.setText("(empty)");
        btnInv4.setToolTipText("Inventory slot 4");
        pnlInventory.add(btnInv4, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        pnlEquipments = new JPanel();
        pnlEquipments.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnlEquipments.setBackground(new Color(-11382962));
        pnlEquipments.setDoubleBuffered(false);
        Font pnlEquipmentsFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlEquipments.getFont());
        if (pnlEquipmentsFont != null) pnlEquipments.setFont(pnlEquipmentsFont);
        pnlEquipments.setForeground(new Color(-1));
        pnlEquipments.setOpaque(false);
        pnlEquipments.setPreferredSize(new Dimension(320, 320));
        pnlGame.add(pnlEquipments, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(124, 124), null, 0, false));
        btnMainWeapon = new JButton();
        btnMainWeapon.setBackground(new Color(-11382962));
        btnMainWeapon.setDoubleBuffered(false);
        Font btnMainWeaponFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnMainWeapon.getFont());
        if (btnMainWeaponFont != null) btnMainWeapon.setFont(btnMainWeaponFont);
        btnMainWeapon.setForeground(new Color(-1));
        btnMainWeapon.setOpaque(true);
        btnMainWeapon.setText("(empty)");
        btnMainWeapon.setToolTipText("Main Weapon slot");
        pnlEquipments.add(btnMainWeapon, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnHead = new JButton();
        btnHead.setBackground(new Color(-11382962));
        btnHead.setDoubleBuffered(false);
        Font btnHeadFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnHead.getFont());
        if (btnHeadFont != null) btnHead.setFont(btnHeadFont);
        btnHead.setForeground(new Color(-1));
        btnHead.setOpaque(true);
        btnHead.setText("(empty)");
        btnHead.setToolTipText("Head slot");
        pnlEquipments.add(btnHead, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnBody = new JButton();
        btnBody.setBackground(new Color(-11382962));
        btnBody.setDoubleBuffered(false);
        Font btnBodyFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnBody.getFont());
        if (btnBodyFont != null) btnBody.setFont(btnBodyFont);
        btnBody.setForeground(new Color(-1));
        btnBody.setOpaque(true);
        btnBody.setText("(empty)");
        btnBody.setToolTipText("Body slot");
        pnlEquipments.add(btnBody, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnMisc = new JButton();
        btnMisc.setBackground(new Color(-11382962));
        btnMisc.setDoubleBuffered(false);
        Font btnMiscFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnMisc.getFont());
        if (btnMiscFont != null) btnMisc.setFont(btnMiscFont);
        btnMisc.setForeground(new Color(-1));
        btnMisc.setOpaque(true);
        btnMisc.setText("(empty)");
        btnMisc.setToolTipText("Misc. slot");
        pnlEquipments.add(btnMisc, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        pnlLoots = new JPanel();
        pnlLoots.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pnlLoots.setBackground(new Color(-11382962));
        pnlLoots.setDoubleBuffered(false);
        pnlLoots.setEnabled(false);
        Font pnlLootsFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlLoots.getFont());
        if (pnlLootsFont != null) pnlLoots.setFont(pnlLootsFont);
        pnlLoots.setForeground(new Color(-1));
        pnlLoots.setOpaque(false);
        mainPanel.add(pnlLoots, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
