package GameObjects;

import GameObjects.Mobs.Tier1;
import GameObjects.Loots.*;
import GameObjects.Mobs.allMobs;

public class testGameObjects {
    public static void main(String[] args) {
        Tier1 wolf1 = allMobs.newWolf();

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
                wolf1 + "\n" +
                you.basicAtk(wolf1) + "\n" +
                wolf1 + "\n" +
                wolf1.basicAtk(you) + "\n" +
                you.combatStats() + "\n" +
                you.consume(water) + "\n" +
                you + "\n";

        System.out.println(gameLog);
    }

}
