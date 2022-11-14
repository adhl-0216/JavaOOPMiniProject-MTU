package entities;

public class Consumables extends Item{
    private String type; //"hp" || "sanity"
    public Consumables(String name, String desc, int weight, String type) {
        super(name, desc, 1, weight);
        setType(type);
    }

    @Override
    public String toString() {
        return "Consumables{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", durability=" + durability +
                ", maxDurability=" + maxDurability +
                ", weight=" + weight +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
