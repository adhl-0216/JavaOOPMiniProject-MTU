package map;

import GameObjects.Entity;
import GameObjects.Item;

public class Room {
    private Item[] loot;
    private Entity[] mobs;

    public Room(Item[] loot, Entity[] mobs) {
        setLoot(loot);
        setMobs(mobs);
    }

    public Item[] getLoot() {
        return loot;
    }

    public void setLoot(Item[] loot) {
        this.loot = loot;
    }

    public Entity[] getMobs() {
        return mobs;
    }

    public void setMobs(Entity[] mobs) {
        this.mobs = mobs;
    }
}


