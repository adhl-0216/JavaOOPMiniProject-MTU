package GameObjects.Loots;

import GameObjects.Item;

public class Consumable extends Item {
    private String type; //"hp" || "sans" || "hybrid"
    private double value;
    public Consumable(String name, String desc, int weight, String type, double value) {
        super(name, desc, 1, weight);
        setType(type);
        setValue(value);
    }

    @Override
    public String toString() {
        return "Consumable{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", desc='" + getDesc() + '\'' +
                ", durability=" + getDurability() +
                ", maxDurability=" + getMaxDurability() +
                ", weight=" + getWeight() +
                ", type='" + getType() + '\'' +
                ", value=" + getValue() +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
