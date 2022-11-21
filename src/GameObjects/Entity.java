package GameObjects;

import javax.swing.*;

public abstract class Entity {
    private String name;
    private double hp;
    private double atk;
    private double def;
    private String src;

    public Entity(String name, double hp, double atk, double def) {
        setName(name);
        setHp(hp);
        setAtk(atk);
        setDef(def);
    }

    @Override
    public String toString() {
        return String.format("name='%s', hp=%.2f, atk=%.2f, def=%.2f", getName(), getHp(), getAtk(), getDef());
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

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
        this.hp = Math.max(hp, 0);
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
