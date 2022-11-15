package GameObjects;

public abstract class Entity {
    private String name;
    private double hp;
    private double atk;
    private double def;

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

    public String basicAtk(Entity target) {
        String output = this.getName() + " attacked " + target.getName() + ".";
        double dmg = atk * (100/(100 + target.getDef()));
        target.setHp((target.getHp()-dmg));
        if (target.getClass() == Player.class) {
            ((Player) target).setSanity(((Player) target).getSanity()-(atk*.1));
        }
        if (this.getClass() == Player.class){
            if (((Player) this).getMainWeapon() != null){
                output += "\n" + ((Player) this).getMainWeapon().durabilityLost(1);
            }
        }
        return output;
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
