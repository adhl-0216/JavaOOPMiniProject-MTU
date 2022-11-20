package GameObjects;

import GameObjects.Loots.Consumable;
import GameObjects.Loots.Equipment;
import GameObjects.Loots.Weapon;
import map.Room;

import java.util.ArrayList;

public class Player extends Entity {
    private Inventory inventory;
    private double sanity;
    private Weapon mainWeapon;
    private Equipment head;
    private Equipment body;
    private Equipment misc;

    public Player() {
        super("You", 100, 4, 4);
        setSanity(100);
    }

    public String pickUp(String itemName, Room room){

        for (Item target : room.getLoots()) {
            if (target.getName().equalsIgnoreCase(itemName)) {
                inventory.addItem(target);
                room.getLoots().remove(target);
                return "Picked up " + target.getName() +".";
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
                        setAtk(getAtk() + wpAtk);
                        setMainWeapon(((Weapon) item));
                        inventory.dropItem(item);
                        output += String.format(" (+%.2f ATK)", wpAtk);
                    } else if (item.getClass() == Equipment.class) {
                        double eqDef = ((Equipment) item).getDef();
                        setDef(getDef() + eqDef);
                        switch (((Equipment) item).getSlot()) {
                            case "head":
                                setHead(((Equipment) item));
                            case "body":
                                setBody(((Equipment) item));
                            case "misc":
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
