package map;

import java.io.Serializable;

public class Map implements Serializable {
    static Room tutorial = allRooms.tutRoom();
    static Room forest1 = allRooms.newForest1();
    static Room forest5 = allRooms.newForest1();
    static Room forest2 = allRooms.newForest2();
    static Room cabin = allRooms.newCabin();
    static Room cave = allRooms.newCave();
    static Room forest3 = allRooms.newForest3();
    static Room forest4 = allRooms.newForest4();

    private static final Room[] map = {tutorial, forest1, forest2, cabin, cave, forest5, forest4, forest3};

    public static Room[] getMap(){
        return map;
    }
}
