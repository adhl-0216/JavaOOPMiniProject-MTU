package map;

import GameObjects.Entity;
import GameObjects.Item;
import GameObjects.Loots.allLoots;
import GameObjects.Mobs.allMobs;

import java.util.ArrayList;
import java.util.Arrays;

public class allRooms {
    public static Room tutRoom() {
        final Item[] loots = {allLoots.newAxe(), allLoots.newApple(), allLoots.newWater()};
        final Entity[] mobs = {allMobs.newWolf()};
        return new Room(loots, mobs);
    }
}
