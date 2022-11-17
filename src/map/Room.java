package map;

import GameObjects.Entity;
import GameObjects.Item;
import GameObjects.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Room {
    private final ArrayList<Item> loots;
    private final ArrayList<Entity> mobs;
    private Player player;
    public Room() {
        this.loots = new ArrayList<Item>();
        this.mobs = new ArrayList<Entity>();
    }
    public Room(Item[] loots, Entity[] mobs) {
        this.loots = new ArrayList<Item>();
        this.mobs = new ArrayList<Entity>();
        Collections.addAll(this.loots,loots);
        Collections.addAll(this.mobs,mobs);
    }

    @Override
    public String toString() {
        StringBuilder lootsStr = new StringBuilder();
        StringBuilder mobsStr = new StringBuilder();
        for (Item loot : loots) {
            lootsStr.append("\n\t\t").append(loot).append(",");
        }
        for (Entity mob : mobs) {
            mobsStr.append("\n\t\t").append(mob).append(",");
        }
        return "Room{" +
                "\n\tloots={" + lootsStr + "\n\t\t}" +
                "\n\tmobs={" + mobsStr + "\n\t\t}" +
                "\n}";
    }

    public Player addPlayer(Player player){
        this.player = player;
        return this.player;
    }

    public void addLoot(Item loot) {
        loots.add(loot);
    }

    public void addMob(Entity mob) {
        mobs.add(mob);
    }

    public void removeLoot(Item target) {
        for (Item loot : loots) {
            if (loot == target)
                loots.remove(target);
        }
    }

    public void removeMob(Entity target) {
        mobs.remove(target);
    }

    public ArrayList<Item> getLoots() {
        return loots;
    }

    public ArrayList<Entity> getMobs() {
        return mobs;
    }

    public Player getPlayer() {
        return player;
    }
}


