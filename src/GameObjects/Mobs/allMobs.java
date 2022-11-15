package GameObjects.Mobs;

import GameObjects.Entity;

import java.util.ArrayList;

public abstract class allMobs {
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

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(newWolf());
            System.out.println(newFox());
            System.out.println("\n");
        }
    }
}
