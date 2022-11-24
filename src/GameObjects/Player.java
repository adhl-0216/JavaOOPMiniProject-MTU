package GameObjects;

import GameObjects.Loots.Consumable;
import GameObjects.Loots.Equipment;
import GameObjects.Loots.Weapon;
import map.Room;

import java.util.Objects;

public class Player extends Entity {
    private Inventory inventory;
    private double sanity;
    private Weapon mainWeapon;
    private Equipment head;
    private Equipment body;
    private Equipment misc;
    private final double baseATK;
    private final double baseDEF;
    public Player(double HP, double ATK, double DEF, int invGrade) {
        super("You", HP, ATK, DEF);
        this.baseATK = ATK;
        this.baseDEF = DEF;
        setInventory(new Inventory(invGrade));
        setSanity(100);
    }
    public String pickUp(String itemName, Room room){

        for (Item target : room.getLoots()) {
            if (target.getName().equalsIgnoreCase(itemName)) {
                if (!Objects.equals(inventory.addItem(target), "Inventory is full!")) {
                    room.getLoots().remove(target);
                    return "Picked up " + target.getName() +".";
                }else return "";
            }
        }
        return "No such thing exists.";
    }
    public String consume(String consumable) {
        for (Item item: inventory.getItems()) {
            if (item != null) {
                if (item.getName().equalsIgnoreCase(consumable)) {
                    if (item.getClass() == Consumable.class) {
                        Consumable c = (Consumable)item;
                        String type = c.getType();
                        double value = c.getValue();
                        switch (type) {
                            case "HP" -> setHp(Math.min((getHp() + value),100));
                            case "SANITY" -> {
                                if (consumable.equalsIgnoreCase("whiskey")) {
                                    setSanity(getSanity() + value);
                                } else {
                                    setSanity(Math.min((getSanity() + value),100));
                                }

                            }
                            case "HP & SANITY" -> {
                                setHp(Math.min((getHp() + value / 2),100));
                                setSanity(Math.min((getSanity() + value / 2),100));
                                inventory.dropItem(item);
                                return String.format("Consumed 1 %s. (+%.2f HP +%.2f SANITY)", c.getName(),value/2,value/2);
                            }
                        }
                        inventory.dropItem(item);
                        return String.format("Consumed 1 %s. (+%.2f %s)", c.getName(),value,type);
                    }
                }
            }
        }
        return "Item is not in inventory.";
    }
    public String equip(String equipment) {
        for (Item item: inventory.getItems()) {
            if (item != null) {
                if (item.getName().equalsIgnoreCase(equipment)) {
                    String output = "Equipped " + item.getName() + ".";
                    if (item.getClass() == Weapon.class) {
                        setMainWeapon(((Weapon) item));
                        inventory.dropItem(item);
                        output += String.format(" (+%d.00ATK)", ((Weapon) item).getAtk());
                    }
                    else if (item.getClass() == Equipment.class) {
                        double eqDef = ((Equipment) item).getDef();
                        Equipment eq = (Equipment) item;

                        if (eq.getSlot().equalsIgnoreCase("head")) {
                            if (getHead() != null) {
                                setDef(getDef() + eqDef - getHead().getDef());
                            } else {
                                setDef(baseDEF + eqDef);
                            }
                            setHead(((Equipment) item));
                        }
                        else if (eq.getSlot().equalsIgnoreCase("body")) {
                            if (getBody() != null) {
                                setDef(getDef() + eqDef - getBody().getDef());
                            } else {
                                setDef(getDef()  + eqDef);
                            }
                            setBody(((Equipment) item));
                        }
                        else if (eq.getSlot().equalsIgnoreCase("misc")) {
                            if (getMisc() != null) {
                                setDef(getDef() + eqDef - getMisc().getDef());
                            } else {
                                setDef(getDef()  + eqDef);
                            }
                            setMisc(((Equipment) item));
                        }
                        inventory.dropItem(item);
                        output += String.format(" (+%.2fDEF)", eqDef);
                    }
                        return output;
                    }
                }
            }
        return "Item is not in inventory.";
    }
    public String basicAtk(String id, Room room) {
        for (Entity target : room.getMobs()) {
            if (target.getId() == Integer.parseInt(id)){
                String output = getName() + " attacked " + target.getName() + ". ";
                double dmg = getAtk() * (100 / (100 + target.getDef()));
                if (getSanity() > 70) {
                    if (Math.random() < 0.40){
                        target.setHp(target.getHp() - (dmg*1.5));
                        output+= "\nCRITICAL HIT!!!" + String.format("(%s -%.2fHP)", target.getName(),dmg*1.5);
                    }else {
                        target.setHp(target.getHp() - dmg);
                        output += String.format("(%s -%.2fHP)", target.getName(),dmg);
                    }
                }
                else if (getSanity() < 30) {
                    target.setHp(target.getHp() - dmg);
                    output += String.format("(%s -%.2fHP)", target.getName(),dmg);
                }
                else {
                    if (Math.random() < 0.15){
                        target.setHp(target.getHp() - (dmg*1.25));
                        output+= "\nStrong Hit!" + String.format("(%s -%.2fHP)", target.getName(),dmg*1.25);
                    }else {
                        target.setHp(target.getHp() - dmg);
                        output += String.format("(%s -%.2fHP)", target.getName(),dmg);
                    }
                }

                if (getMainWeapon() != null) {
                    if (getMainWeapon().getDurability() >= 0) {
                        output += "\n" + getMainWeapon().durabilityLost(1);
                        if (this.getMainWeapon().getDurability() == 0) setMainWeapon(null);
                    }
                }
                return output;
            }
        }
        return "Target Not Found.";
    }
    @Override
    public String toString() {
        return "Player{\n\t" +
                super.toString() +
                String.format(", sanity=%.2f",getSanity()) +
                ",\n\tmainWeapon=" + mainWeapon +
                ",\n\thead=" + head +
                ",\n\tbody=" + body +
                ",\n\tmisc=" + misc +
                "\n\t" + getInventory() +
                "\n}";
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public double getSanity() {
        return Math.max(sanity, 0);
    }
    public void setSanity(double sanity) {
        this.sanity = sanity;
    }
    public Weapon getMainWeapon() {
        return mainWeapon;
    }
    public void setMainWeapon(Weapon mainWeapon) {
        if (mainWeapon != null) {
            double wpAtk = mainWeapon.getAtk();
            if (getMainWeapon() != null) {
                setAtk(getAtk() + wpAtk - getMainWeapon().getAtk());
            }else setAtk(getAtk() + wpAtk);
        }else {
            setAtk(baseATK);
        }
            this.mainWeapon = mainWeapon;
    }
    public Equipment getHead() {
        return head;
    }
    public void setHead(Equipment head) {
        this.head = head;
    }
    public Equipment getBody() {
        return body;
    }
    public void setBody(Equipment body) {
        this.body = body;
    }
    public Equipment getMisc() {
        return misc;
    }
    public void setMisc(Equipment misc) {
        this.misc = misc;
    }
}