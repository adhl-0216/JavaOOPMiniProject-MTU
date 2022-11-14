package entities;

public class TestEntities {
    public static void main(String[] args) {
        Slime blu = new Slime("Blue Slime", 100, 5, 200);

        Weapon axe = new Weapon("Tree Axe", "An ordinary tree axe.", 6, 40, 5);
        Weapon machete = new Weapon("Rusty Machete", "An old and rusty machete", 25, 30, 5);
        Equipments jacket = new Equipments("Jacket", "An old rugged jacket", 20, "body" );
        Equipments gloves = new Equipments("Gloves", "A pair of hunting leather gloves", 20, "misc");
        Consumables apple = new Consumables("Apple", "A small apple", 1, "hp");
        Consumables water = new Consumables("Water", "A bottle of half-full mineral water", 1, "sans");

        Player you = new Player();
        you.setInventory(new Inventory(3));
        System.out.println(you.pickUp(axe));
        System.out.println(blu);
        you.basicAtk(blu);
        System.out.println(axe.durabilityLost(5));
        System.out.println(blu);
    }

}
