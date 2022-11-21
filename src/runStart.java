import GUIs.GameWindow;
import GUIs.MainMenu;
import map.Room;
import map.allRooms;

public class runStart {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu("A SECOND CHANCE");
        GameWindow gameWindow = new GameWindow("A SECOND CHANCE", menu);
        menu.setVisible(true);

        Room tutorial = allRooms.tutRoom();
        gameWindow.setRoom(tutorial);
        Room forest1 = allRooms.newForest1();
        Room forest2 = allRooms.newForest2();
        Room cabin = allRooms.newCabin();
        Room cave = allRooms.newCave();
        Room forest3 = allRooms.newForest3();

        menu.setGameWindow(gameWindow);
    }
}
