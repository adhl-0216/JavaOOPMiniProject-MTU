package entities;

public abstract class Item {
    private static int count = 0;
    private int id;
    private String name;
    private String desc;
    private int durability;
    private int maxDurability;
    private int weight;

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


    public String durabilityLost(int dmg) {
        String output;
        String checkDur;
        checkDur = setDurability(durability-dmg);
        output = name + " has lost " + dmg + " Durability... " + String.format("(%d/%d)",durability,maxDurability);
        if (checkDur != null) {
            output += checkDur;
        }
        return output;
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

    public String setDurability(int durability) {
        this.durability = durability;
        if (this.durability == 0) {
            return "\n" + name + " has reached 0 Durability, all is lost...";
        }
        return null;
    }


    public int getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
    }
}
