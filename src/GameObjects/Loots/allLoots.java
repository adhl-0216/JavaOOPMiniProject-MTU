package GameObjects.Loots;

public class allLoots {

    public static Weapon newAxe() {return new Weapon("Axe", "An ordinary tree axe.", 15, 20);}
    public static Weapon newMachete() {return new Weapon("Machete", "An old and rusty machete.", 35, 20);}

    public static Equipment newCap() {return new Equipment("Cap", "A worn baseball cap.", 20, 15,"head" );}
    public static Equipment newJacket() {return new Equipment("Jacket", "An old rugged jacket.", 20, 55,"body" );}
    public static Equipment newGloves() {return new Equipment("Gloves", "A pair of hunting leather gloves.", 25, 20,"misc");}
    public static Equipment newHelmet() {return new Equipment("Helmet", "A common motorcycle helmet.", 20, 45,"head");}

    public static Consumable newApple() {return new Consumable("Apple", "A small apple.", 1, "HP & SANITY", 10);}
    public static Consumable newChoc() {return new Consumable("Chocolate", "A cheap milk chocolate.", 1, "HP", 25);}
    public static Consumable newBerries() {return new Consumable("Berries", "A handful of berries.", 1, "HP", 5);}
    public static Consumable newWater() {return new Consumable("Water", "A bottle of mineral water.", 1, "HP & SANITY", 10);}
    public static Consumable newSandwich() {return new Consumable("Sandwich", "A half-eaten BMT sandwich.", 1, "HP & SANITY", 100);}
    public static Consumable newJerky() {return new Consumable("Jerky", "A pack of beef jerky.", 1, "HP", 35);}
    public static Consumable newWhiskey() {return new Consumable("Whiskey", "A bottle of whiskey.", 1, "SANITY", -40);}
}
