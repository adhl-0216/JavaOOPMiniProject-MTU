package GameObjects.Mobs;

import GameObjects.Entity;

public class Tier3 extends Entity {
    public Tier3(String name, double hp, double baseAtk, double maxAtk) {
        super(name, hp, 0, 100);
        double atk = (int) Math.floor((Math.random() * (maxAtk - baseAtk))) + baseAtk;
        setAtk(atk);
    }
}
