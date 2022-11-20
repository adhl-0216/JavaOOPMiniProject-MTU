package map;

import GameObjects.Entity;
import GameObjects.Inventory;
import GameObjects.Item;
import GameObjects.Loots.allLoots;
import GameObjects.Mobs.allMobs;
import GameObjects.Player;

public class testRooms {
    public static void main(String[] args) {
        Player you = new Player();
        you.setInventory(new Inventory(3));
        Room cabin = allRooms.newCabin();
        Room cave = allRooms.newCave();
        cabin.addPlayer(you);
        System.out.println(cabin);
        cabin.newTurn(you, "pickup", "machete");
        cabin.newTurn(you, "pickup", "jacket");
        cabin.newTurn(you, "pickup", "gloves");
        cabin.newTurn(you, "pickup", "sandwich");
        cabin.newTurn(you, "pickup", "jerky");
        cabin.newTurn(you, "equip", "machete");
        cabin.newTurn(you, "equip", "jacket");
        cabin.newTurn(you, "equip", "gloves");
        cabin.removePlayer(you);
        System.out.println(cabin);
        System.out.println(cabin.getGameLog());

        cave.addPlayer(you);
        System.out.println(cave);
        for (int i = 0; i < 3; i++) {
            cave.newTurn(you, "attack", "bear");

        }
        cave.newTurn(you, "consume", "sandwich");
        cave.newTurn(you, "consume", "jerky");
        for (int i = 0; i < 5; i++) {
            if(cave.newTurn(you, "attack", "bear") == null) break;
        }
        System.out.println(cave.getGameLog());
        System.out.println(cave);
    }
}
