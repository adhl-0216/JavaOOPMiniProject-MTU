package GameObjects.Mobs;

import GameObjects.Entity;

import javax.swing.*;
import java.util.ArrayList;

public class allMobs {
    //tier 1
    public static Tier1 newWolf() {
        String[] var = {"Grey Wolf","Black Wolf", "White Wolf", "Brown Wolf"};
        return new Tier1(var[(int) Math.floor(Math.random() * var.length)], 100, 5,10) {
        };
    }

    public static Tier1 newFox() {
        String[] var = {"Orange Fox", "White Fox", "Red Fox", "Black Fox"};
        return new Tier1(var[(int) Math.floor(Math.random() * var.length)], 80, 8, 12) {
        };
    }

    //tier 2
    public static Tier2 newGhoul(){
        Tier2 ghoul = new Tier2("Ghoul", 150, 10,20);
        ghoul.addSpecialAtk("Jump-scare",10,"SANITY");
        ghoul.addSpecialAtk("Loud Cry",33.33,"HP");
        return ghoul;
    }

    public static void main(String[] args) {

        Tier1 wolf1 = newWolf();
        Tier2 ghoul1 = newGhoul();
        JOptionPane.showMessageDialog(null,wolf1+"\n"+ghoul1 ,"Test", 1);
        JOptionPane.showMessageDialog(null,ghoul1.specialAtk(wolf1,2),"Test", 1);
        JOptionPane.showMessageDialog(null,wolf1,"Test", 1);
        System.exit(0);
    }
}
