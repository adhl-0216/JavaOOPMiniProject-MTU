package GameObjects.Mobs;

import GameObjects.Entity;
import GameObjects.Player;

public class Tier1 extends Entity {
    public Tier1(String name, double hp, double baseAtk, double maxAtk) {
        super(name, hp, 0, 10);
        double atk = (int) Math.floor((Math.random() * (maxAtk - baseAtk))) + baseAtk;
        setAtk(atk);
    }
    public String basicAtk(Player player) {
        double dmg = this.getAtk() * (100 / (100 + player.getDef()));
        player.setHp(player.getHp() - dmg);
        double sansDmg = this.getAtk() * .3;
        player.setSanity(player.getSanity() - sansDmg);
        return this.getName() + " attacked " + player.getName() + "." + String.format("(-%.2fHP, -%.2fSANITY)",dmg, sansDmg);
    }

    @Override
    public String toString() {
        return "Tier1{" + super.toString() + "}";
    }
}
