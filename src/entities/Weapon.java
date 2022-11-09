package entities;

public class Weapon extends Item {

    int atk;

    public Weapon(String name, String desc, int atk, int durability) {
        super(name, desc, durability);
        setAtk(atk);
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", atk=" + atk +
                ", durability=" + durability +
                ", maxDurability=" + maxDurability +
                '}';
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }
}
