package GameObjects.Loots;

public abstract class allLoots {

    public static Weapon newAxe() {return new Weapon("Axe", "An ordinary tree axe.", 15, 20);}
    public static Weapon newMachete() {return new Weapon("Machete", "An old and rusty machete.", 25, 20);}
    public static Weapon newBat() {return new Weapon("Baseball Bat", "A baseball bat made of solid steel.", 10, 40);}
    public static Weapon newPan() {return new Weapon("Pan", "A solid cast iron pan with bullet marks.", 25, 20);}
    public static Weapon newTorch() {return new Weapon("Torch", "A military grade long torch.", 10, 20);}
    public static Weapon newCross() {return new Weapon("Cross", "A silver cross with intricate design.", 4, 20);}

    public static Equipments newJacket() {return new Equipments("Jacket", "An old rugged jacket", 20, 50,"body" );}
    public static Equipments newGloves() {return new Equipments("Gloves", "A pair of hunting leather gloves", 20, 20,"misc");}

    public static Consumables newApple() {return new Consumables("Apple", "A small apple", 1, "HP", 10);}
    public static Consumables newWater() {return new Consumables("Water", "A bottle of half-full mineral water", 1, "HP & SANITY", 10);}
}
