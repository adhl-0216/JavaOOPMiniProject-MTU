package GameObjects;

import GameObjects.Mobs.Tier1;
import GameObjects.Loots.*;

public class testGameObjects {
    public static void main(String[] args) {
        Tier1 blu = new Tier1("Blue Slime", 100, 5,5);

        Player you = new Player();
        you.setInventory(new Inventory(3));

        Weapon axe = allLoots.newAxe();
        Equipments jacket = allLoots.newJacket();
        Consumables apple = allLoots.newApple();
        Consumables water = allLoots.newWater();


        String gameLog = you.pickUp(axe) + "\n" +
                you.pickUp(jacket) + "\n" +
                you.pickUp(apple) + "\n" +
                you.pickUp(water) + "\n" +
                you.equip(jacket) + "\n" +
                you.equip(axe) + "\n" +
                you + "\n" +
                blu + "\n" +
                you.basicAtk(blu) + "\n" +
                blu + "\n" +
                blu.basicAtk(you) + "\n" +
                you.combatStats() + "\n" +
                you.consume(water) + "\n" +
                you + "\n";

        System.out.println(gameLog);
    }

}
