package entities;

public class Player extends Entity {
    Inventory inventory;

    public Player() {
        super("You", 100, 0, 0);
    }

    public String pickUp(Item newItem){
        for (int i = 0; i < inventory.size; i++) {
            if (inventory.items[i] == null) {
                inventory.items[i] = newItem;
                return "Picked up " + newItem.getName();
            }
        }
        return "Inventory is full!";
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
}
