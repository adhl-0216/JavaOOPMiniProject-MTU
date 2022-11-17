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

    public Room pickUp(String itemName, Room room){

        for (Item target : room.getLoots()) {
            if (target.getName().equalsIgnoreCase(itemName)) {
                inventory.addItem(target);
                room.getLoots().remove(target);
                break;
            }
        }
        System.out.println("Picked up " + itemName);
        return room;
    }

//    public String consume(String consumable){
//        String output;
//        Item[] inv = inventory.getItems();
//        for (Item item : inv) {
//            if (item.getName().equalsIgnoreCase(consumable)) {
//                output = ((Consumable) item).use(this);
//                inventory.dropItem(item);
//                return output;
//            }
//        }
//        //else
//        output = "Consumable is not in inventory.";
//        return output;
//    }

    public String consume(String consumable) {
        for (Item item: inventory.getItems()) {
            System.out.println(item);
            if (item != null) {
                if (item.getName().equalsIgnoreCase(consumable)) {
                    if (item.getClass() == Consumable.class) {
                        String type = ((Consumable) item).getType();
                        double value = ((Consumable) item).getValue();
                        switch (type) {
                            case "HP" -> {
                                setHp(Math.min((getHp() + value), 100));
                            }
                            case "SANITY" -> {
                                setSanity(Math.min((getSanity() + value), 100));

                            }
                            case "HP & SANITY" -> {
                                setHp(Math.min((getHp() + value / 2), 100));
                                setSanity(Math.min((getSanity() + value / 2), 100));
                                return String.format("Consumed 1 %s. (+%.2f HP +%.2f SANITY)", item.getName(), value / 2, value / 2);
                            }
                        }
                        inventory.dropItem(item);
                    }
                }
            }else break;
        }
        return "Item is not in inventory.";
    }

    public String equip(String equipment) {
        for (Item item: inventory.getItems()) {
            if (item.getName().equalsIgnoreCase(equipment)) {
                String output = "Equipped " + item.getName() + ".";
                if (item.getClass() == Weapon.class) {
                    double wpAtk = ((Weapon) item).getAtk();
                    setAtk(getAtk()+ wpAtk);
                    setMainWeapon(((Weapon) item));
                    inventory.dropItem(item);
                    output += String.format(" (+%.2f ATK)",wpAtk);
                } else if (item.getClass() == Equipment.class) {
                    double eqDef = ((Equipment) item).getDef();
                    setDef(getDef()+ eqDef);
                    switch (((Equipment) item).getSlot()) {
                        case "head" :
                            setHead(((Equipment) item));
                        case "body" :
                            setBody(((Equipment) item));
                        case "misc" :
                            setMisc(((Equipment) item));
                    }
                    inventory.dropItem(item);
                    output += String.format(" (+%.2f DEF)", eqDef);
                }
                return output;
            }
        }
        return "Item is not in inventory.";
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
        return "You can still pick up " + inventory.vacantSpace() + " item(s).";
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
