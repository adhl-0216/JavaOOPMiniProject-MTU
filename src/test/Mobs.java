package test;

public abstract class Mobs {
    private String name;
    private double hp;
    private double atk;
    private double def;

    public Mobs(String name, double hp, double atk, double def) {
        setName(name);
        setHp(hp);
        setAtk(atk);
        setDef(def);
    }

    @Override
    public String toString() {
        return "Mobs{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                '}';
    }

    public void basicAtk(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public double getDef() {
        return def;
    }

    public void setDef(double def) {
        this.def = def;
    }
}
