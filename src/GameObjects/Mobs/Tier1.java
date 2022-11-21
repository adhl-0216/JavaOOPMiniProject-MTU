package GameObjects.Mobs;

import GameObjects.Entity;
import GameObjects.Player;
import map.Room;

import javax.swing.*;

public class Tier1 extends Entity {
    public Tier1(String name, double hp, double baseAtk, double maxAtk) {
        super(name, hp, 0, 10);
        double atk = (int) Math.floor((Math.random() * (maxAtk - baseAtk))) + baseAtk;
        setAtk(atk);
    }
    public String basicAtk(Player player) {
        double dmg = this.getAtk() * (100 / (100 + player.getDef()));
        player.setHp(player.getHp() - dmg);
        player.setSanity(player.getSanity() - (dmg*.05));
        return this.getName() + " attacked " + player.getName() + ".";
    }

    @Override
    public String toString() {
        return "Tier1{" + super.toString() + "}";
    }
}
