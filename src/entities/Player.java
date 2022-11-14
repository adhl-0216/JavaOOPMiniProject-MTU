package entities;

public class Player extends Entity {
    private Inventory inventory;
    private int sanity;

    public Player() {
        super("You", 100, 200, 0);
        setSanity(100);
    }

    public String pickUp(Item item){
        inventory.addItem(item);
        return "Picked up " + item.getName();
    }

    public void consume(Consumables consumable){
        inventory.dropItem(consumable);
        System.out.printf("You have consumed %s%n", consumable.getName());
    }

    @Override
    public String toString() {
        return "Player{" +
                super.toString() +
                "\n\tinventory=" + inventory +
                '}';
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getSanity() {
        return sanity;
    }

    public void setSanity(int sanity) {
        this.sanity = sanity;
    }
}
