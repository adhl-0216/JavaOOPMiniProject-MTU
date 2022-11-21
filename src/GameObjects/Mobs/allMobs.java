package GameObjects.Mobs;

import javax.swing.*;

public class allMobs {
    //tier 1
    public static Tier1 newWolf() {
        String[] var = {"Grey Wolf","Black Wolf", "White Wolf", "Brown Wolf"};
        Tier1 wolf = new Tier1("wolf", 100, 5,10);
        wolf.setName(var[(int) Math.floor(Math.random() * var.length)]);
        wolf.setSrc("assets/wolf.png");
        return wolf;
    }
    public static Tier1 newAlphaWolf() {
        Tier1 alphaWolf;
        alphaWolf = new Tier1("Alpha Wolf", 120, 6,12);
        alphaWolf.setSrc("assets/wolf.png");
        return alphaWolf;
    }

    public static Tier1 newFox() {
        String[] var = {"Orange Fox", "White Fox", "Red Fox", "Black Fox"};
        Tier1 fox = new Tier1("fox", 80, 8, 12);
        fox.setName(var[(int) Math.floor(Math.random() * var.length)]);
        return fox;
    }

    //tier 2
    public static Tier2 newBoar(){
        Tier2 boar = new Tier2("Wild Boar", 150, 10,20);
        boar.addSpecialAtk("Battle Cry",10,"SANITY");
        boar.addSpecialAtk("Charge",20,"HP");
        return boar;
    }

    //tier 3

    public static Tier3 newBear(){
        Tier3 bear;
        String[] var = {"Black Bear", "Brown Bear"};
        bear = new Tier3("bear", 200, 20, 30);
        bear.setName(var[(int) Math.floor(Math.random() * var.length)]);
        bear.addSpecialAtk("Battle Cry",10,"SANITY");
        bear.addSpecialAtk("Claw Attack",25,"HP");
        bear.addSSA("Bear Hug",50,"HP");
        bear.setSrc("assets/bear.png");
        return bear;
    }

    public static void main(String[] args) {
        Tier1 t1 = newFox();
        System.out.println(t1.getClass());
    }
}
