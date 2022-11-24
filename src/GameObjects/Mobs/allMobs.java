package GameObjects.Mobs;

import javax.swing.*;

public class allMobs {
    //tier 1
    public static Tier1 newWolf() {
        String[] var = {"Grey Wolf", "White Wolf", "Brown Wolf"};
        Tier1 wolf = new Tier1("Wolf", 100, 5,10);
        wolf.setName(var[(int) Math.floor(Math.random() * var.length)]);
        wolf.setSrc("resources/wolf.png");
        return wolf;
    }
    public static Tier1 newAlphaWolf() {
        Tier1 alphaWolf;
        alphaWolf = new Tier1("Alpha Wolf", 120, 6,12);
        alphaWolf.setSrc("resources/alphaWolf.png");
        return alphaWolf;
    }

    public static Tier1 newFox() {
        String[] var = {"Red Fox", "White Fox", "Brown Fox"};
        Tier1 fox = new Tier1("Fox", 80, 8, 12);
        fox.setName(var[(int) Math.floor(Math.random() * var.length)]);
        fox.setSrc("resources/fox.png");
        return fox;
    }

    //tier 2
    public static Tier2 newBoar(){
        Tier2 boar = new Tier2("Wild Boar", 150, 10,20);
        boar.addSpecialAtk("Battle Cry",15,"SANITY");
        boar.addSpecialAtk("Charge",20,"HP");
        boar.setSrc("resources/boar.png");
        return boar;
    }

    //tier 3

    public static Tier3 newBear(){
        Tier3 bear;
        String[] var = {"Black Bear", "Brown Bear"};
        bear = new Tier3("Bear", 200, 20, 30);
        bear.setName(var[(int) Math.floor(Math.random() * var.length)]);
        bear.addSpecialAtk("Furious Roar",20,"SANITY");
        bear.addSpecialAtk("Claw Attack",25,"HP");
        bear.addSpecialAtk("Ravenous Tackle",25,"HP & SANITY");
        bear.addSSA("Bear Hug",35,"HP");
        bear.setSrc("resources/bear.png");
        return bear;
    }

}
