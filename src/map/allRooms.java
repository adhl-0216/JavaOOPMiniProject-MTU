package map;

import GameObjects.Entity;
import GameObjects.Item;
import GameObjects.Loots.allLoots;
import GameObjects.Mobs.allMobs;

public class allRooms {
    public static Room tutRoom() {
        final Item[] loots = {allLoots.newAxe(), allLoots.newChoc(), allLoots.newWater(), allLoots.newCap()};
        final Entity[] mobs = {allMobs.newAlphaWolf()};
        return new Room("tutorial", loots, mobs);
    }
    public static Room newForest1() {
        final Item[] loots = {allLoots.newApple(), allLoots.newBerries(), allLoots.newApple(), allLoots.newBerries()};
        final Entity[] mobs = {allMobs.newFox(), allMobs.newFox()};
        return new Room("forest", loots, mobs);
    }
    public static Room newForest2() {
        final Item[] loots = {allLoots.newChoc(), allLoots.newChoc()};
        final Entity[] mobs = {allMobs.newBoar()};
        return new Room("forest", loots, mobs);
    }
    public static Room newCabin() {
        final Item[] loots = {
                allLoots.newMachete(),
                allLoots.newSandwich(),
                allLoots.newJerky(),
                allLoots.newJacket(),
                allLoots.newGloves(),
                allLoots.newHelmet(),
                allLoots.newWhiskey()
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
        final Item[] loots = {allLoots.newChoc(), allLoots.newChoc(), allLoots.newChoc()};
        final Entity[] mobs = {allMobs.newAlphaWolf(), allMobs.newWolf(), allMobs.newWolf()};
        return new Room("forest", loots, mobs);
    }

    public static Room newForest4() {
        final Item[] loots = {allLoots.newApple(), allLoots.newWater(), allLoots.newArmor(), allLoots.newMachete()};
        final Entity[] mobs = {allMobs.newFox(), allMobs.newBoar()};
        return new Room("forest", loots, mobs);
    }

}
