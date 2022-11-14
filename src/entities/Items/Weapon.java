package entities.Items;

import entities.Item;

public class Weapon extends Item {
    private int atk;

    public Weapon(String name, String desc, int atk, int durability, int weight) {
        super(name, desc, durability, weight);
        setAtk(atk);
    }


    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", desc='" + getDesc() + '\'' +
                ", atk=" + getAtk() +
                ", durability=" + getDurability() +
                ", maxDurability=" + getMaxDurability() +
                '}';
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }
}
