import GUIs.GameWindow;
import GUIs.MainMenu;
import map.Room;
import map.allRooms;

import java.util.Arrays;

public class runStart {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu("A SECOND CHANCE");
        menu.setVisible(true);

        Room tutorial = allRooms.tutRoom();
        Room forest1 = allRooms.newForest1();
        Room forest2 = allRooms.newForest2();
        Room cabin = allRooms.newCabin();
        Room cave = allRooms.newCave();
        Room forest3 = allRooms.newForest3();

        Room[] map = {tutorial, forest1, forest2, cabin, cave, forest3};

        GameWindow gameWindow = new GameWindow("A SECOND CHANCE", menu, map);

        menu.setGameWindow(gameWindow);
        System.out.println(Arrays.toString(gameWindow.getMap()));
    }
}
