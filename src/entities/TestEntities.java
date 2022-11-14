package entities;

import entities.Items.Consumables;
import entities.Items.Equipments;
import entities.Items.Weapon;
import entities.Mobs.Slime;

public class TestEntities {
    public static void main(String[] args) {
        Slime blu = new Slime("Blue Slime", 100, 5, 2);

        Weapon axe = new Weapon("Tree Axe", "An ordinary tree axe.", 6, 40, 5);
        Weapon machete = new Weapon("Rusty Machete", "An old and rusty machete", 25, 30, 5);
        Equipments jacket = new Equipments("Jacket", "An old rugged jacket", 20, 50,"body" );
        Equipments gloves = new Equipments("Gloves", "A pair of hunting leather gloves", 20, 20,"misc");
        Consumables apple = new Consumables("Apple", "A small apple", 1, "HP", 10);
        Consumables water = new Consumables("Water", "A bottle of half-full mineral water", 1, "HP & SANITY", 10);

        Player you = new Player();
        you.setInventory(new Inventory(3));

        StringBuilder gameLog = new StringBuilder();
        gameLog.append(you.pickUp(axe)).append("\n")
                .append(you.pickUp(jacket)).append("\n")
                .append(you.pickUp(apple)).append("\n")
                .append(you.pickUp(water)).append("\n")
                .append(you.equip(jacket)).append("\n")
                .append(you.equip(axe)).append("\n")
                .append(you).append("\n")
                .append(blu).append("\n")
                .append("You attacked blu.").append("\n")
                .append(axe.durabilityLost(5)).append("\n");
        you.basicAtk(blu);
        gameLog.append(blu).append("\n")
                .append("Blue attacked you.").append("\n");
        blu.basicAtk(you);
        gameLog.append(you.combatStats()).append("\n")
                .append(you.consume(water)).append("\n")
                .append(you).append("\n");

        System.out.println(gameLog);
    }

}
