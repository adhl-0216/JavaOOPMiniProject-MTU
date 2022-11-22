package GameObjects;

import GameObjects.Loots.Consumable;
import GameObjects.Loots.Equipment;
import GameObjects.Loots.Weapon;
import map.Room;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    private Inventory inventory;
    private double sanity;
    private Weapon mainWeapon;
    private Equipment head;
    private Equipment body;
    private Equipment misc;
    public Player() {
        super("You", 50, 5, 5);
        setSanity(50);
    }

    public String pickUp(String itemName, Room room){

        for (Item target : room.getLoots()) {
            if (target.getName().equalsIgnoreCase(itemName)) {
                if (!Objects.equals(inventory.addItem(target), "Inventory is full!")) {
                    room.getLoots().remove(target);
                    return "Picked up " + target.getName() +".";
                }else return "Inventory is full!";
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
                            case "HP" -> {
                                setHp(Math.min((getHp() + value),100));
                            }
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
                        double wpAtk = ((Weapon) item).getAtk();
                        if (getMainWeapon() != null) {
                            setAtk(getAtk() + wpAtk - getMainWeapon().getAtk());
                        }else {
                            setAtk(5 + wpAtk);
                        }
                        setMainWeapon(((Weapon) item));
                        inventory.dropItem(item);
                        output += String.format(" (+%.2f ATK)", wpAtk);
                    }
                    else if (item.getClass() == Equipment.class) {
                        double eqDef = ((Equipment) item).getDef();
                        Equipment eq = (Equipment) item;

                        if (eq.getSlot().equalsIgnoreCase("head")) {
                            if (getHead() != null) {
                                setDef(getDef() + eqDef - getHead().getDef());
                            } else {
                                setDef(5 + eqDef);
                            }
                            setHead(((Equipment) item));
                        }
                        else if (eq.getSlot().equalsIgnoreCase("body")) {
                            if (getBody() != null) {
                                setDef(getDef() + eqDef - getBody().getDef());
                            } else {
                                setDef(5 + eqDef);
                            }
                            setBody(((Equipment) item));
                        }
                        else if (eq.getSlot().equalsIgnoreCase("misc")) {
                            if (getMisc() != null) {
                                setDef(getDef() + eqDef - getMisc().getDef());
                            } else {
                                setDef(5 + eqDef);
                            }
                            setMisc(((Equipment) item));
                        }
                        inventory.dropItem(item);
                        output += String.format(" (+%.2f DEF)", eqDef);
                    }
                        return output;
                    }
                }
            }
        return "Item is not in inventory.";
    }

    public String basicAtk(String Target, Room room) {
        for (Entity target : room.getMobs()) {
            if (target.getHp() > 0) {
                if ((target.getName()).toLowerCase().contains(Target) || target.getName().equalsIgnoreCase(Target)){
                    String output = this.getName() + " attacked " + target.getName() + ".";
                    double dmg = this.getAtk() * (100 / (100 + target.getDef()));
                    target.setHp((target.getHp() - dmg));
                    if (this.getMainWeapon() != null) {
                        output += "\n" + this.getMainWeapon().durabilityLost(1);
                    }
                    return output;
                }
            } else {
                room.removeMob(target.getName());
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

    public String combatStats() {
        return "Player{\n\t" +
                super.toString() +
                String.format(", sanity=%.2f",getSanity()) +
                ",\n\tmainWeapon=" + mainWeapon +
                ",\n\thead=" + head +
                ",\n\tbody=" + body +
                ",\n\tmisc=" + misc +"\n}";
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String setInventory(Inventory inventory) {
        this.inventory = inventory;
        return "You can pick up " + inventory.vacantSpace() + " item(s).";
    }

    public double getSanity() {
        return sanity;
    }

    public void setSanity(double sanity) {
        this.sanity = sanity;
    }

    public Weapon getMainWeapon() {
        return mainWeapon;
    }

    public void setMainWeapon(Weapon mainWeapon) {
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
