package GUIs;

import GameObjects.Entity;
import GameObjects.Item;
import GameObjects.Loots.Consumable;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class GameWindow extends JFrame implements Serializable {
    private static final Font customFont = new Font(Font.MONOSPACED, Font.BOLD, 14);
    private JPanel mainPanel;
    private JPanel pnlLocation;
    private JButton btnLocation;

    private JPanel pnlGameLog;
    private JTextArea txtGameLog;

    private JPanel pnlMobs;
    private JPanel pnlLoots;

    private JPanel pnlGame;
    private JPanel pnlInventory;
    private JButton btnInv1;
    private JButton btnInv2;
    private JButton btnInv3;
    private JButton btnInv4;
    private JPanel pnlHpBar;
    private JLabel lblHp;
    private JLabel lblDef;
    private JLabel lblAtk;
    private JLabel lblSans;
    private JPanel pnlEquipments;
    private JButton btnHead;
    private JButton btnBody;
    private JButton btnMainWeapon;
    private JButton btnMisc;
    private JLabel lblTurn;

    private MainMenu parent;
    private Player player;
    private Room[] map;
    private int currLoc = 0;
    private Room room;

    public GameWindow(String title, MainMenu parent, Player player, Room[] map, int loadedLoc) {
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
        this.setIconImage(new ImageIcon("resources/icon.png").getImage());
        this.add(mainPanel);
        this.pack();

        setPlayer(player);
        setMap(map);
        setCurrLoc(loadedLoc);
        setRoom(this.map[currLoc]);

        txtGameLog.setText("You woke up in the forest...\n");

        btnLocation.addActionListener(e -> nextLocation(currLoc));
        btnInv1.addActionListener(this::equipOrConsume);
        btnInv2.addActionListener(this::equipOrConsume);
        btnInv3.addActionListener(this::equipOrConsume);
        btnInv4.addActionListener(this::equipOrConsume);

        this.setVisible(true);
    }
    private void setRoom(Room room) {
        this.room = room;
        room.addPlayer(player);
        btnLocation.setText(room.getName().toUpperCase());
        btnLocation.setToolTipText("Current location: " + room.getName().toUpperCase());
        if (!room.getName().equalsIgnoreCase("cabin")) {
            btnLocation.setEnabled(false);
        }
        lblTurn.setText("Turn " + room.getTurnCount());
        lblAtk.setText("ATK: " + player.getAtk());
        lblDef.setText("DEF: " + player.getDef());
        lblHp.setText(String.format("HP: (%.2f/100.00)", player.getHp()));
        lblSans.setText(String.format("SANITY: (%.2f/100.00)", +player.getSanity()));
        setMobs();
        setLoots();
        try {
            setPnlInventory();
        } catch (Exception e) {
            System.out.print("");
        }
        setEquipments();
        txtGameLog.setText(room.getGameLog());
    }
    private void nextLocation(int currLoc) {
        boolean allCleared = true;
        ArrayList<Entity> mobs = room.getMobs();
        if (mobs != null) {
            allCleared = false;
            for (Entity mob : mobs) {
                if (mob.getHp() != 0) break;
                allCleared = true;
            }
        }

        if (allCleared || room.getName().equalsIgnoreCase("cabin")) {
            btnLocation.setText("Proceed to the next location>>");
            if (currLoc == getMap().length) {
                JOptionPane.showMessageDialog(null, "You have been grated a second chance in life.", "The End", JOptionPane.INFORMATION_MESSAGE);
                super.dispose();
                parent.setVisible(true);
            } else {
                room.removePlayer();
                txtGameLog.setText(room.getGameLog());
                setRoom(getMap()[++this.currLoc]);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Something is blocking your way...", "Can't Proceed", JOptionPane.WARNING_MESSAGE);
        }
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
            lblHp.setText(String.format("HP: (%.2f/100.00)", player.getHp()));
            lblSans.setText(String.format("SANITY: (%.2f/100.00)", player.getSanity()));
        }
        btnInv.setText("(empty)");
        txtGameLog.setText(room.getGameLog());
    }
    private void setEquipments() {
        if (player.getMainWeapon() != null) {
            lblAtk.setText("ATK: " + player.getAtk());
            btnMainWeapon.setText(player.getMainWeapon().getName());
            btnMainWeapon.setToolTipText(String.format("[ATK: %d, DUR: (%.0f/%d)]    %s", player.getMainWeapon().getAtk(), player.getMainWeapon().getDurability(), player.getMainWeapon().getMaxDurability(), player.getMainWeapon().getDesc()));
        } else {
            lblAtk.setText("ATK: " + player.getAtk());
            btnMainWeapon.setText("(empty)");
            btnMainWeapon.setToolTipText("Main Weapon slot");
        }

        if (player.getHead() != null) {
            lblDef.setText("DEF: " + player.getDef());
            btnHead.setText(player.getHead().getName());
            btnHead.setToolTipText(String.format("[DEF: %d, DUR: (%.0f/%d)]    %s", player.getHead().getDef(), player.getHead().getDurability(), player.getHead().getMaxDurability(), player.getHead().getDesc()));

        }

        if (player.getBody() != null) {
            lblDef.setText("DEF: " + player.getDef());
            btnBody.setText(player.getBody().getName());
            btnBody.setToolTipText(String.format("[DEF: %d, DUR: (%.0f/%d)]    %s", player.getBody().getDef(), player.getBody().getDurability(), player.getBody().getMaxDurability(), player.getBody().getDesc()));
        }

        if (player.getMisc() != null) {
            lblDef.setText("DEF: " + player.getDef());
            btnMisc.setText(player.getMisc().getName());
            btnMisc.setToolTipText(String.format("[DEF: %d, DUR: (%.0f/%d)]    %s", player.getMisc().getDef(), player.getMisc().getDurability(), player.getMisc().getMaxDurability(), player.getMisc().getDesc()));
        }
    }
    private void playerAttack(JButton btn, JLabel lbl) {
        for (Entity mob : room.getMobs()) {
            if (mob.getId() == Integer.parseInt(btn.getName())) {
                room.newTurn(player, "attack", String.valueOf(mob.getId()));
                txtGameLog.setText(room.getGameLog());
                lblTurn.setText("Turn " + room.getTurnCount());
                lbl.setText(String.format("%s - HP: %.2f", mob.getName(), mob.getHp()));
                lblHp.setText(String.format("HP: (%.2f/100.00)", player.getHp()));
                lblSans.setText(String.format("SANITY: (%.2f/100.00)", player.getSanity()));
                if (mob.getHp() == 0) {
                    btn.setEnabled(false);
                    txtGameLog.append("Nothing is blocking your way now...");
                    btnLocation.setText("Proceed>>");
                    btnLocation.setToolTipText("Click to proceed to the next location.");
                    btnLocation.setEnabled(true);
                }
                break;
            }
        }

        if (player.getHp() <= 0 || player.getSanity() <= 0) {
            JOptionPane.showMessageDialog(null, "All is lost...", "GAME OVER", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            this.parent.setVisible(true);
        }
        if (player.getMainWeapon() == null) setEquipments();
    }
    private void setMobs() {
        ArrayList<Entity> mobs = room.getMobs();
        pnlMobs.removeAll();
        for (Entity mob : mobs) {
            JPanel pnlMob = new JPanel();
            GridBagConstraints gbc;
            pnlMob.setLayout(new GridBagLayout());
            pnlMob.setOpaque(false);

            //mob status label
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.BOTH;
            JLabel lblMob = new JLabel(String.format("%s - HP: %.2f", mob.getName(), +mob.getHp()));
            lblMob.setFont(customFont);
            lblMob.setForeground(Color.white);
            lblMob.setBackground(new Color(82, 79, 78));
            lblMob.setVisible(true);

            //set image, addEventListener etc.
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            JButton btnMob = new JButton();
            btnMob.setName(String.valueOf(mob.getId()));
            btnMob.setIcon(new ImageIcon(mob.getSrc()));
            btnMob.setSize(120, 120);
            btnMob.setOpaque(false);
            btnMob.setContentAreaFilled(false);
            btnMob.setBorderPainted(false);
            btnMob.addActionListener(e -> playerAttack(btnMob, lblMob));
            btnMob.setToolTipText(String.format("%s (ATK: %.2f, DEF: %.2f)    [Click To Attack]", mob.getName(), mob.getAtk(), mob.getDef()));
            btnMob.setVisible(true);

            pnlMob.add(lblMob, gbc);
            pnlMob.add(btnMob, gbc);
            pnlMobs.add(pnlMob);
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
            btnLoot.setToolTipText("Click to pick up loot");
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
                System.out.print("");
            }
            lblTurn.setText("Turn " + room.getTurnCount());
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
            if (type.equalsIgnoreCase("consumable")) {
                Consumable con = (Consumable) inv[i];
                btnInvs[i].setToolTipText(inv[i].getDesc() + String.format(" (+ %.2f%s)", con.getValue(), con.getType()));
            } else if (type.equalsIgnoreCase("equipment")) {
                Equipment eq = (Equipment) inv[i];
                btnInvs[i].setToolTipText(inv[i].getDesc() + String.format(" (+ %d.00DEF)", eq.getDef()));
            } else if (type.equalsIgnoreCase("weapon")) {
                Weapon wp = (Weapon) inv[i];
                btnInvs[i].setToolTipText(inv[i].getDesc() + String.format(" (+ %d.00ATK)", wp.getAtk()));
            }
            btnInvs[i].setName(type);
        }
    }
    public void setMap(Room[] map) {
        this.map = Arrays.copyOf(map, map.length);
    }
    public Room[] getMap() {
        return Arrays.copyOf(map, map.length);
    }

    WindowListener exitListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int confirm = JOptionPane.showConfirmDialog(null, "Return To Main Menu?", "Quit Game", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == 0) {
                getParent().setVisible(true);
                GameWindow.super.dispose();
            }
        }
    };
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
    public JTextArea getTxtGameLog() {
        return txtGameLog;
    }
    public int getCurrLoc() {
        return currLoc;
    }
    public void setCurrLoc(int currLoc) {
        this.currLoc = currLoc;
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
        mainPanel.setBackground(new Color(-11382962));
        mainPanel.setDoubleBuffered(false);
        Font mainPanelFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, mainPanel.getFont());
        if (mainPanelFont != null) mainPanel.setFont(mainPanelFont);
        mainPanel.setForeground(new Color(-1));
        mainPanel.setMaximumSize(new Dimension(1080, 720));
        mainPanel.setMinimumSize(new Dimension(1080, 720));
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new Dimension(1080, 720));
        mainPanel.setRequestFocusEnabled(false);
        pnlLocation = new JPanel();
        pnlLocation.setLayout(new GridBagLayout());
        pnlLocation.setBackground(new Color(-11382962));
        pnlLocation.setDoubleBuffered(false);
        pnlLocation.setEnabled(true);
        Font pnlLocationFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlLocation.getFont());
        if (pnlLocationFont != null) pnlLocation.setFont(pnlLocationFont);
        pnlLocation.setForeground(new Color(-1));
        pnlLocation.setOpaque(false);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(pnlLocation, gbc);
        btnLocation = new JButton();
        btnLocation.setBackground(new Color(-11382962));
        btnLocation.setBorderPainted(false);
        btnLocation.setDoubleBuffered(false);
        btnLocation.setEnabled(true);
        Font btnLocationFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnLocation.getFont());
        if (btnLocationFont != null) btnLocation.setFont(btnLocationFont);
        btnLocation.setForeground(new Color(-1));
        btnLocation.setHorizontalAlignment(4);
        btnLocation.setHorizontalTextPosition(0);
        btnLocation.setOpaque(false);
        btnLocation.setText("(location)");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.VERTICAL;
        pnlLocation.add(btnLocation, gbc);
        lblTurn = new JLabel();
        lblTurn.setEnabled(true);
        Font lblTurnFont = this.$$$getFont$$$("DialogInput", Font.BOLD, 20, lblTurn.getFont());
        if (lblTurnFont != null) lblTurn.setFont(lblTurnFont);
        lblTurn.setForeground(new Color(-1));
        lblTurn.setText("Turn 1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 690, 0, 0);
        pnlLocation.add(lblTurn, gbc);
        pnlGameLog = new JPanel();
        pnlGameLog.setLayout(new GridBagLayout());
        pnlGameLog.setBackground(new Color(-11382962));
        pnlGameLog.setDoubleBuffered(false);
        Font pnlGameLogFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlGameLog.getFont());
        if (pnlGameLogFont != null) pnlGameLog.setFont(pnlGameLogFont);
        pnlGameLog.setForeground(new Color(-1));
        pnlGameLog.setMaximumSize(new Dimension(364, 804));
        pnlGameLog.setMinimumSize(new Dimension(364, 804));
        pnlGameLog.setOpaque(false);
        pnlGameLog.setPreferredSize(new Dimension(364, 804));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(pnlGameLog, gbc);
        txtGameLog = new JTextArea();
        txtGameLog.setAutoscrolls(true);
        txtGameLog.setBackground(new Color(-11382962));
        txtGameLog.setDoubleBuffered(false);
        txtGameLog.setEditable(false);
        txtGameLog.setFocusable(false);
        Font txtGameLogFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, txtGameLog.getFont());
        if (txtGameLogFont != null) txtGameLog.setFont(txtGameLogFont);
        txtGameLog.setForeground(new Color(-1));
        txtGameLog.setLineWrap(true);
        txtGameLog.setMaximumSize(new Dimension(364, 804));
        txtGameLog.setMinimumSize(new Dimension(360, 17));
        txtGameLog.setOpaque(true);
        txtGameLog.setPreferredSize(new Dimension(364, 804));
        txtGameLog.setText("");
        txtGameLog.setWrapStyleWord(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnlGameLog.add(txtGameLog, gbc);
        pnlGame = new JPanel();
        pnlGame.setLayout(new GridBagLayout());
        pnlGame.setBackground(new Color(-11382962));
        pnlGame.setDoubleBuffered(false);
        pnlGame.setFocusable(false);
        Font pnlGameFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlGame.getFont());
        if (pnlGameFont != null) pnlGame.setFont(pnlGameFont);
        pnlGame.setForeground(new Color(-1));
        pnlGame.setMaximumSize(new Dimension(680, 600));
        pnlGame.setMinimumSize(new Dimension(680, 600));
        pnlGame.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(pnlGame, gbc);
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
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        pnlGame.add(pnlInventory, gbc);
        pnlInventory.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        btnInv1 = new JButton();
        btnInv1.setBackground(new Color(-11382962));
        btnInv1.setDoubleBuffered(false);
        Font btnInv1Font = this.$$$getFont$$$("Courier New", Font.BOLD, 12, btnInv1.getFont());
        if (btnInv1Font != null) btnInv1.setFont(btnInv1Font);
        btnInv1.setForeground(new Color(-1));
        btnInv1.setOpaque(true);
        btnInv1.setText("(empty)");
        btnInv1.setToolTipText("Inventory slot 1");
        pnlInventory.add(btnInv1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnInv2 = new JButton();
        btnInv2.setBackground(new Color(-11382962));
        btnInv2.setDoubleBuffered(false);
        Font btnInv2Font = this.$$$getFont$$$("Courier New", Font.BOLD, 12, btnInv2.getFont());
        if (btnInv2Font != null) btnInv2.setFont(btnInv2Font);
        btnInv2.setForeground(new Color(-1));
        btnInv2.setOpaque(true);
        btnInv2.setText("(empty)");
        btnInv2.setToolTipText("Inventory slot 2");
        pnlInventory.add(btnInv2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnInv3 = new JButton();
        btnInv3.setBackground(new Color(-11382962));
        btnInv3.setDoubleBuffered(false);
        Font btnInv3Font = this.$$$getFont$$$("Courier New", Font.BOLD, 12, btnInv3.getFont());
        if (btnInv3Font != null) btnInv3.setFont(btnInv3Font);
        btnInv3.setForeground(new Color(-1));
        btnInv3.setOpaque(true);
        btnInv3.setText("(empty)");
        btnInv3.setToolTipText("Inventory slot 3");
        pnlInventory.add(btnInv3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnInv4 = new JButton();
        btnInv4.setBackground(new Color(-11382962));
        btnInv4.setDoubleBuffered(false);
        Font btnInv4Font = this.$$$getFont$$$("Courier New", Font.BOLD, 12, btnInv4.getFont());
        if (btnInv4Font != null) btnInv4.setFont(btnInv4Font);
        btnInv4.setForeground(new Color(-1));
        btnInv4.setOpaque(true);
        btnInv4.setText("(empty)");
        btnInv4.setToolTipText("Inventory slot 4");
        pnlInventory.add(btnInv4, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        pnlHpBar = new JPanel();
        pnlHpBar.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 4, 0, 4), 1, 1));
        pnlHpBar.setBackground(new Color(-11382962));
        pnlHpBar.setDoubleBuffered(false);
        pnlHpBar.setEnabled(true);
        Font pnlHpBarFont = this.$$$getFont$$$("Courier New", Font.BOLD, 12, pnlHpBar.getFont());
        if (pnlHpBarFont != null) pnlHpBar.setFont(pnlHpBarFont);
        pnlHpBar.setForeground(new Color(-1));
        pnlHpBar.setOpaque(false);
        pnlHpBar.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnlGame.add(pnlHpBar, gbc);
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
        pnlHpBar.add(lblAtk, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        pnlHpBar.add(lblDef, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        pnlHpBar.add(lblSans, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblHp = new JLabel();
        lblHp.setBackground(new Color(-11382962));
        lblHp.setDoubleBuffered(false);
        lblHp.setEnabled(true);
        lblHp.setFocusable(false);
        Font lblHpFont = this.$$$getFont$$$("Courier New", Font.BOLD, 18, lblHp.getFont());
        if (lblHpFont != null) lblHp.setFont(lblHpFont);
        lblHp.setForeground(new Color(-1));
        lblHp.setHorizontalAlignment(0);
        lblHp.setHorizontalTextPosition(0);
        lblHp.setOpaque(false);
        lblHp.setText("HP: (100/100)");
        pnlHpBar.add(lblHp, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlEquipments = new JPanel();
        pnlEquipments.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1, true, true));
        pnlEquipments.setBackground(new Color(-11382962));
        pnlEquipments.setDoubleBuffered(false);
        Font pnlEquipmentsFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlEquipments.getFont());
        if (pnlEquipmentsFont != null) pnlEquipments.setFont(pnlEquipmentsFont);
        pnlEquipments.setForeground(new Color(-1));
        pnlEquipments.setMaximumSize(new Dimension(250, 250));
        pnlEquipments.setMinimumSize(new Dimension(250, 250));
        pnlEquipments.setOpaque(false);
        pnlEquipments.setPreferredSize(new Dimension(320, 320));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnlGame.add(pnlEquipments, gbc);
        btnMainWeapon = new JButton();
        btnMainWeapon.setBackground(new Color(-11382962));
        btnMainWeapon.setDoubleBuffered(false);
        Font btnMainWeaponFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnMainWeapon.getFont());
        if (btnMainWeaponFont != null) btnMainWeapon.setFont(btnMainWeaponFont);
        btnMainWeapon.setForeground(new Color(-1));
        btnMainWeapon.setOpaque(true);
        btnMainWeapon.setText("(empty)");
        btnMainWeapon.setToolTipText("Main Weapon slot");
        pnlEquipments.add(btnMainWeapon, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnHead = new JButton();
        btnHead.setBackground(new Color(-11382962));
        btnHead.setDoubleBuffered(false);
        Font btnHeadFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnHead.getFont());
        if (btnHeadFont != null) btnHead.setFont(btnHeadFont);
        btnHead.setForeground(new Color(-1));
        btnHead.setOpaque(true);
        btnHead.setText("(empty)");
        btnHead.setToolTipText("Head slot");
        pnlEquipments.add(btnHead, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnBody = new JButton();
        btnBody.setBackground(new Color(-11382962));
        btnBody.setDoubleBuffered(false);
        Font btnBodyFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnBody.getFont());
        if (btnBodyFont != null) btnBody.setFont(btnBodyFont);
        btnBody.setForeground(new Color(-1));
        btnBody.setOpaque(true);
        btnBody.setText("(empty)");
        btnBody.setToolTipText("Body slot");
        pnlEquipments.add(btnBody, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        btnMisc = new JButton();
        btnMisc.setBackground(new Color(-11382962));
        btnMisc.setDoubleBuffered(false);
        Font btnMiscFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, btnMisc.getFont());
        if (btnMiscFont != null) btnMisc.setFont(btnMiscFont);
        btnMisc.setForeground(new Color(-1));
        btnMisc.setOpaque(true);
        btnMisc.setText("(empty)");
        btnMisc.setToolTipText("Misc. slot");
        pnlEquipments.add(btnMisc, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(96, 96), new Dimension(96, 96), new Dimension(96, 96), 0, false));
        pnlLoots = new JPanel();
        pnlLoots.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
        pnlLoots.setBackground(new Color(-11382962));
        pnlLoots.setDoubleBuffered(false);
        pnlLoots.setEnabled(false);
        Font pnlLootsFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlLoots.getFont());
        if (pnlLootsFont != null) pnlLoots.setFont(pnlLootsFont);
        pnlLoots.setForeground(new Color(-1));
        pnlLoots.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(pnlLoots, gbc);
        pnlMobs = new JPanel();
        pnlMobs.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pnlMobs.setBackground(new Color(-11382962));
        pnlMobs.setDoubleBuffered(false);
        pnlMobs.setEnabled(false);
        pnlMobs.setFocusable(false);
        Font pnlMobsFont = this.$$$getFont$$$("Courier New", Font.BOLD, 14, pnlMobs.getFont());
        if (pnlMobsFont != null) pnlMobs.setFont(pnlMobsFont);
        pnlMobs.setForeground(new Color(-1));
        pnlMobs.setOpaque(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        mainPanel.add(pnlMobs, gbc);
        pnlMobs.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
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
}