package entities;

public abstract class Item {
    static int count = 0;
    int id;
    String name;
    String desc;
    int durability;
    int maxDurability;
    int weight;

    public Item(String name, String desc,int durability, int weight) {
        setId();
        setName(name);
        setDesc(desc);
        setDurability(durability);
        this.maxDurability = durability;
        setWeight(weight);
    }

    public String viewItem() {
        if (durability == 0) return null;
        return name + String.format(" (%d/%d)",durability,maxDurability) + "," + desc;
    }

    public void deleteId(){
        this.id = -1;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", durability=" + durability +
                ", maxDurability=" + maxDurability +
                '}';
    }

    public String durabilityLost(int dmg) {
        String output;
        setDurability(durability-dmg);
        output = name + " has lost " + dmg + " Durability... " + String.format("(%d/%d)",durability,maxDurability);
        if (durability == 0) {
            output += "\n" + name + " has reached 0 Durability, all is lost...";
            deleteId();
        }
        return output;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = ++count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
