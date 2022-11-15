package GameObjects.Mobs;

import GameObjects.Entity;

public class Tier1 extends Entity {
    public Tier1(String name, double hp, double baseAtk, double maxAtk) {
        super(name, hp, 0, 10);
        double atk = (int) Math.floor((Math.random() * (maxAtk - baseAtk))) + baseAtk;
        setAtk(atk);
    }
}
