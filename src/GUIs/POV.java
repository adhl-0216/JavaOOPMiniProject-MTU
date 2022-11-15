package GUIs;

import GameObjects.Entity;
import GameObjects.Item;
import GameObjects.Loots.allLoots;
import map.Room;

import javax.swing.*;
import java.util.ArrayList;

public class POV {
    private JFrame frame;
    private JLabel lblLocation;
    private JLabel lblGameLog;
    private JButton btnInteract;

    public POV(JFrame frame) {
        frame = new JFrame("IN LE HEAD");
    }

/*    public static void main(String[] args) {
        ArrayList<Item> loots1 = new ArrayList<Item>();
        loots1.add(allLoots.newAxe());
        loots1.add(allLoots.newMachete());

        ArrayList<Entity> mobs1 = new ArrayList<Entity>();
        mobs1.add()


        Room room1 = new Room(loots1.toArray(),mobs1.toArray());
    }*/
}
