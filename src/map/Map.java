package map;

import java.io.Serializable;

public class Map implements Serializable {
    static Room tutorial = allRooms.tutRoom();
    static Room forest1 = allRooms.newForest1();
    static Room forest2 = allRooms.newForest2();
    static Room cabin = allRooms.newCabin();
    static Room cave = allRooms.newCave();
    static Room forest3 = allRooms.newForest3();

    public static final Room[] map = {tutorial, forest1, forest2, cabin, cave, forest3};
}
