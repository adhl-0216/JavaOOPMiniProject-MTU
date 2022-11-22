package map;

import GameObjects.Entity;
import GameObjects.Item;
import GameObjects.Loots.allLoots;
import GameObjects.Mobs.Tier1;
import GameObjects.Mobs.allMobs;

import java.util.ArrayList;
import java.util.Arrays;

public class allRooms {
    public static Room tutRoom() {
        final Item[] loots = {allLoots.newAxe(), allLoots.newApple(), allLoots.newWater()};
        final Entity[] mobs = {allMobs.newAlphaWolf()};
        return new Room("Tutorial", loots, mobs);
    }
    public static Room newForest1() {
        final Item[] loots = {allLoots.newApple(), allLoots.newApple(), allLoots.newWater()};
        final Entity[] mobs = {allMobs.newFox(), allMobs.newFox()};
        return new Room("forest", loots, mobs);
    }
    public static Room newForest2() {
        final Item[] loots = {allLoots.newAxe(), allLoots.newApple(), allLoots.newWater()};
        final Entity[] mobs = {allMobs.newBoar()};
        return new Room("forest", loots, mobs);
    }
    public static Room newCabin() {
        final Item[] loots = {
                allLoots.newPan(),
                allLoots.newMachete(),
                allLoots.newSandwich(),
                allLoots.newJerky(),
                allLoots.newWhiskey(),
                allLoots.newJacket(),
                allLoots.newGloves()
        };
        final Entity[] mobs = {};
        return new Room("cabin", loots, mobs);
    }
    public static Room newCave() {
        final Item[] loots = {allLoots.newBerries(), allLoots.newBerries(), allLoots.newBerries()};
        final Entity[] mobs = {allMobs.newBear()};
        return new Room("cave", loots, mobs);
    }
    public static Room newForest3() {
        final Item[] loots = {allLoots.newAxe(), allLoots.newApple(), allLoots.newWater()};
        final Entity[] mobs = {allMobs.newAlphaWolf(), allMobs.newWolf(), allMobs.newWolf()};
        return new Room("forest", loots, mobs);
    }
}
