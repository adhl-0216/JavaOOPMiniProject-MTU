package entities;

public class TestEntities {
    public static void main(String[] args) {
        Slime blu = new Slime(
                "Blue Slime",
                100,
                5,
                0
        );

        Weapon axe = new Weapon("Tree Axe", "An ordinary tree axe.", 6, 40);
        Weapon machete = new Weapon("Rusty Machete", "An old and rusty machete", 25, 30);
//        System.out.println(axe.viewItem());
//        System.out.println(axe.durabilityLost(15));
//        System.out.println(axe.viewItem());
//        System.out.println(axe);
        Player you = new Player();
        you.setInventory(new Inventory(3));
        System.out.println(you.pickUp(axe));
        System.out.println(axe.durabilityLost(5));
        System.out.println(you.pickUp(machete));
        System.out.println(you);
//        System.out.println(blu);
    }

}
