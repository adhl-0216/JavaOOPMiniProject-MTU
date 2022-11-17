package map;

import GameObjects.Inventory;
import GameObjects.Item;
import GameObjects.Player;

import java.util.ArrayList;
import java.util.Collections;

import static map.allRooms.tutRoom;

public class testRooms {
    public static void main(String[] args) {
        Room tutorial = tutRoom();
        ArrayList<Item> loots = tutorial.getLoots();

        Player you = tutorial.addPlayer(new Player());
//        System.out.println(tutorial);
        you.setInventory(new Inventory(3));
        you.pickUp("axe", tutorial);
        you.pickUp("apple", tutorial);
        System.out.println(you.getInventory());
//        System.out.println(you.equip("axe"));
//        System.out.println(you.getInventory());
        System.out.println(you.consume("apple"));
        System.out.println(you.getInventory());
    }
}
