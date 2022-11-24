package GameObjects.Mobs;

import GameObjects.Entity;
import GameObjects.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class Tier2 extends Entity {
    private ArrayList<specialAtk> specialAtks;
    public Tier2(String name, double hp, double baseAtk, double maxAtk) {
        super(name, hp, 0, 50);
        double atk = (int) Math.floor((Math.random() * (maxAtk - baseAtk))) + baseAtk;
        setAtk(atk);
    }

    public String basicAtk(Player player) {
        double dmg = this.getAtk() * (100 / (100 + player.getDef()));
        player.setHp(player.getHp() - dmg);
        double sansDmg = this.getAtk()*((Math.random()*0.60-0.30)+0.30);
        player.setSanity(player.getSanity() - sansDmg);
        return this.getName() + " attacked " + player.getName() + "." + String.format("(-%.2fHP, -%.2fSANITY)",dmg, sansDmg);
    }

    public void addSpecialAtk(String name, double val, String type){
        if (specialAtks != null) {
            specialAtks.add(new specialAtk(name, val, type));
        }
        else {
            specialAtks = new ArrayList<>();
            specialAtks.add(new specialAtk(name, val, type));
        }
    }

    public String specialAtk(Player player, int sid){
        specialAtk sAtk = specialAtks.get(sid);
        String output = this.getName()  + " used " + sAtk.getName() + " against " + player.getName() + "! ";
        if (sAtk.getType().equals("HP")){
            player.setHp(player.getHp()-sAtk.getVal());
            output += String.format("(-%.2fHP)",sAtk.getVal());
        } else if (sAtk.getType().equals("SANITY")) {
            player.setSanity(player.getSanity()-sAtk.getVal());
            output += String.format("(-%.2fSANITY)",sAtk.getVal());
        }
        return output;
    }

    public ArrayList<specialAtk> getSpecialAtks() {
        return specialAtks;
    }

    @Override
    public String toString() {
        return "Tier2{" + super.toString() +
                ", \n\tspecialAtks={" + specialAtks +
                '}';
    }

    private static class specialAtk implements Serializable {
        private String name;
        private final double val;
        private final String type;
        private static int count = 0;
        private final int sId;

        public specialAtk(String name, double val, String type) {
            this.name = name;
            this.val = val;
            this.type = type;
            this.sId = ++count;
        }

        @Override
        public String toString() {
            return "\n\t\t{sId=" + sId +
                    ", name='" + name + '\'' +
                    ", val=" + val +
                    ", type='" + type + '\'' +
                    "}";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getVal() {
            return val;
        }

        public String getType() {
            return type;
        }

    }
}

