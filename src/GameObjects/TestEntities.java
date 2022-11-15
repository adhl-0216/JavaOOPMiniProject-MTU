package GameObjects;

import GameObjects.Mobs.Tier1;
import GameObjects.Loots.*;

public class TestEntities {
    public static void main(String[] args) {
        Tier1 blu = new Tier1("Blue Slime", 100, 5,5);

        Player you = new Player();
        you.setInventory(new Inventory(3));

        Weapon axe = allLoots.newAxe();
        Equipments jacket = allLoots.newJacket();
        Consumables apple = allLoots.newApple();
        Consumables water = allLoots.newWater();


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
