package GameObjects.Mobs;

import GameObjects.Entity;
import GameObjects.Player;

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
        player.setSanity(player.getSanity() - (dmg*.05));
        return this.getName() + " attacked " + player.getName() + ".";
    }

    public void addSpecialAtk(String name, double val, String type){
        if (specialAtks != null) {
            specialAtks.add(new specialAtk(name, val, type));
        }
        else {
            specialAtks = new ArrayList<specialAtk>();
            specialAtks.add(new specialAtk(name, val, type));
        }
    }

    public String specialAtk(Player player, int sid){
        specialAtk sAtk = specialAtks.get(sid);
        if (sAtk.getType().equals("HP")){
            player.setHp(player.getHp()-sAtk.getVal());
        } else if (sAtk.getType().equals("SANITY")) {
            player.setSanity(player.getSanity()-sAtk.getVal());
        }
        return this.getName()  + " used " + sAtk.getName() + " against " + player.getName() + "!";
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

    private static class specialAtk {
        private String name;
        private double val;
        private String type;
        private static int count = 0;
        private int sId;

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

        public void setVal(double val) {
            this.val = val;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getsId() {
            return sId;
        }

        public void setsId(int sId) {
            this.sId = sId;
        }

    }
}

