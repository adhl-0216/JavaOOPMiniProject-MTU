package map;

import GameObjects.Entity;
import GameObjects.Inventory;
import GameObjects.Item;
import GameObjects.Player;

import java.util.ArrayList;
import java.util.Collections;

import static map.allRooms.tutRoom;

public class testRooms {
    public static void main(String[] args) {
        Room tutorial = tutRoom();
        ArrayList<Entity> mobs = tutorial.getMobs();


        Player you = tutorial.addPlayer(new Player());
        System.out.println(tutorial);
        System.out.println(you.setInventory(new Inventory(3)));
        System.out.println(you.pickUp("axe", tutorial));
        System.out.println(you.pickUp("apple", tutorial));
        System.out.println(you.pickUp("apple", tutorial));
        System.out.println(you.pickUp("water", tutorial));
        System.out.println(tutorial);
        System.out.println(you.equip("axe"));
        System.out.println(you.basicAtk("Wolf", tutorial));
        System.out.println(you.basicAtk("Wolf", tutorial));
        System.out.println(you.basicAtk("Wolf", tutorial));
        System.out.println(mobs.get(0));
    }
}
