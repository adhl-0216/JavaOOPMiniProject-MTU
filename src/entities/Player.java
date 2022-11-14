package entities;

import entities.Items.Consumables;
import entities.Items.Equipments;
import entities.Items.Weapon;

public class Player extends Entity {
    private Inventory inventory;
    private double sanity;
    private Weapon mainWeapon;
    private Equipments head;
    private Equipments body;
    private Equipments misc;

    public Player() {
        super("You", 100, 4, 4);
        setSanity(100);
    }

    public String pickUp(Item item){
        inventory.addItem(item);
        return "Picked up " + item.getName();
    }

    public String consume(Consumables consumable){
        String output;
        for (Item item : inventory.getItems()) {
            if (item == consumable) {
                output = consumable.use(this);
                inventory.dropItem(consumable);
                return output;
            }
        }
        //else
        output = "Consumable is not in inventory.";
        return output;
    }

    public String equip(Item item) {
        for (Item i: inventory.getItems()) {
            if (i == item) {
                String output = "Equipped " + item.getName() + ".";
                if (item.getClass() == Weapon.class) {
                    double wpAtk = ((Weapon) item).getAtk();
                    setAtk(getAtk()+ wpAtk);
                    setMainWeapon(((Weapon) item));
                    output += String.format(" (+%.2f ATK)",wpAtk);
                } else if (item.getClass() == Equipments.class) {
                    double eqDef = ((Equipments) item).getDef();
                    setDef(getDef()+ eqDef);
                    switch (((Equipments) item).getSlot()) {
                        case "head" :
                            setHead(((Equipments) item));
                        case "body" :
                            setBody(((Equipments) item));
                        case "misc" :
                            setMisc(((Equipments) item));
                    }
                    output += String.format(" (+%.2f DEF)", eqDef);
                }
                return output;
            }
        }
        return "Item is not in inventory.";
    }

    @Override
    public String toString() {
        return "Player{" +
                super.toString() +
                String.format(", sanity=%.2f }",getSanity()) +
                "\n\tinventory=" + getInventory() +
                '}';
    }

    public String combatStats() {
        return "Player{" +
                super.toString() +
                String.format(", sanity=%.2f }",getSanity());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public Equipments getHead() {
        return head;
    }

    public void setHead(Equipments head) {
        this.head = head;
    }

    public Equipments getBody() {
        return body;
    }

    public void setBody(Equipments body) {
        this.body = body;
    }

    public Equipments getMisc() {
        return misc;
    }

    public void setMisc(Equipments misc) {
        this.misc = misc;
    }
}
