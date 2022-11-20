package GameObjects.Loots;

import GameObjects.Item;
import GameObjects.Player;

public class Consumable extends Item {
    private String type; //"hp" || "sans" || "hybrid"
    private double value;
    public Consumable(String name, String desc, int weight, String type, double value) {
        super(name, desc, 1, weight);
        setType(type);
        setValue(value);
    }

//    public String use(Player p) {
//        switch (type) {
//            case "HP" -> {
//                p.setHp(Math.min((p.getHp() + value),100));
//            }
//            case "SANITY" -> {
//                p.setSanity(Math.min((p.getSanity() + value),100));
//
//            }
//            case "HP & SANITY" -> {
//                p.setHp(Math.min((p.getHp() + value / 2),100));
//                p.setSanity(Math.min((p.getSanity() + value / 2),100));
//                return String.format("%s used 1 %s. (+%.2f HP +%.2f SANITY)",p.getName(), this.getName(),value/2,value/2);
//            }
//        }
//        this.setDurability(0);
//        return String.format("%s used 1 %s. (+%.2f %s)",p.getName(), this.getName(),value,type);
//    }

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
