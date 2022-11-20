package map;

import GameObjects.Entity;
import GameObjects.Item;
import GameObjects.Mobs.Tier1;
import GameObjects.Mobs.Tier2;
import GameObjects.Mobs.Tier3;
import GameObjects.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

public class Room {
    private String name;
    private final ArrayList<Item> loots;
    private final ArrayList<Entity> mobs;
    private Player player;
    private int turnCount;
    private String gameLog = "";
    public Room(String name) {
        this.name = name;
        this.loots = new ArrayList<Item>();
        this.mobs = new ArrayList<Entity>();
        setTurnCount(1);
    }
    public Room(String name, Item[] loots, Entity[] mobs) {
        this.name = name;
        this.loots = new ArrayList<Item>();
        this.mobs = new ArrayList<Entity>();
        Collections.addAll(this.loots,loots);
        Collections.addAll(this.mobs,mobs);
        setTurnCount(1);
    }

    private int rng(int max){
        return (int)Math.floor(Math.random()*max);
    }

    public String newTurn(Player player, String event, String target){
        gameLog += "Turn " + turnCount + "\n";
        if (event.equalsIgnoreCase("pickUp")){
            gameLog += player.pickUp(target, this) + "\n";
            setTurnCount(++turnCount);
            return "Successfully picked up.";
        }
        else if (event.equalsIgnoreCase("equip")) {
            gameLog += player.equip(target) + "\n";
            return "Successfully equipped.";
        }
        else if (event.equalsIgnoreCase("consume")) {
            gameLog += player.consume(target) + "\n";
            return "Successfully consumed.";
        }
        else if (event.equalsIgnoreCase("attack")) {
            StringBuilder gameLogBuilder = new StringBuilder(player.basicAtk(target, this) + "\n");
            for (Entity mob : mobs) {
                if (mob.getHp() > 0) {
                    if (mob.getClass().toString().equalsIgnoreCase("class GameObjects.Mobs.Tier1")) {
                        gameLogBuilder.append(((Tier1) mob).basicAtk(player)).append("\n");
                    }
                    else if (mob.getClass().toString().equalsIgnoreCase("class GameObjects.Mobs.Tier2")) {
                        if (rng(4)==0){
                            gameLogBuilder.append(((Tier2)mob).specialAtk(player, rng(((Tier2) mob).getSpecialAtks().size()))).append("\n");
                        }else {
                            gameLogBuilder.append(((Tier2)mob).basicAtk(player)).append("\n");
                        }
                    }
                    else if (mob.getClass().toString().equalsIgnoreCase("class GameObjects.Mobs.Tier3")) {
                        if (turnCount%5 == 0 && turnCount > 0) {
                            gameLogBuilder.append(((Tier3)mob).SSA(player)).append("\n");
                        } else {
                            if (rng(4)==0){
                                gameLogBuilder.append(((Tier3)mob).specialAtk(player, rng(((Tier3) mob).getSpecialAtks().size()))).append("\n");
                            }else {
                                gameLogBuilder.append(((Tier3)mob).basicAtk(player)).append("\n");
                            }
                        }
                    }
                }
                else {
                    this.removeMob(mob.getName());
                    gameLogBuilder.append(mob.getName()).append(" is dead.").append("\n");
                }
            }
            gameLog += gameLogBuilder + "\n";
//            gameLog += this.getMobs().toString() + "\n" + this.getPlayer() + "\n";
            setTurnCount(++turnCount);
            return "Successfully attacked.";
        }
        return null;
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
                "\n\tname=" + this.name +
                "\n\tloots={" + lootsStr + "\n\t\t}" +
                "\n\tmobs={" + mobsStr + "\n\t\t}" +
                "\n}";
    }

    public void addPlayer(Player player){
        this.player = player;
        gameLog += "You entered the " + this.name + ".\n";
    }

    public void removePlayer(Player player) {
        this.player = null;
        gameLog += "You left the " + this.name + ".\n";
    }

    public String getGameLog() {
        return gameLog;
    }

    public void addLoot(Item loot) {
        loots.add(loot);
    }

    public void addMob(Entity mob) {
        mobs.add(mob);
    }

    public void removeLoot(String target) {
        loots.removeIf(loot -> loot.getName().equalsIgnoreCase(target));
    }

    public void removeMob(String target) {
        loots.removeIf(loot -> loot.getName().equalsIgnoreCase(target));
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

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }
}


