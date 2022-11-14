package entities;

public class Equipments extends Item{
    private String slot; // "head" || "body" || "misc"
    private int def;
    public Equipments(String name, String desc, int durability, String slot) {
        super(name, desc, durability, 0);
        setSlot(slot);
    }

    @Override
    public String toString() {
        return "Equipments{" +
                "slot='" + slot + '\'' +
                ", def=" + def +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", durability=" + durability +
                ", maxDurability=" + maxDurability +
                ", weight=" + weight +
                '}';
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
