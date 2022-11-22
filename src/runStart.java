import GUIs.GameWindow;
import GUIs.MainMenu;
import GameObjects.Inventory;
import GameObjects.Player;
import map.Room;
import map.allRooms;

import java.util.Arrays;

public class runStart {
    public static void main(String[] args) {


        Room tutorial = allRooms.tutRoom();
        Room forest1 = allRooms.newForest1();
        Room forest2 = allRooms.newForest2();
        Room cabin = allRooms.newCabin();
        Room cave = allRooms.newCave();
        Room forest3 = allRooms.newForest3();

        Room[] map = {tutorial, forest1, forest2, cabin, cave, forest3};
        Player you = new Player();
        you.setInventory(new Inventory(3));

        MainMenu menu = new MainMenu("A SECOND CHANCE");
        GameWindow gameWindow = new GameWindow("A SECOND CHANCE", menu, map, you);

        menu.setGameWindow(gameWindow);
        menu.setVisible(true);
    }
}
